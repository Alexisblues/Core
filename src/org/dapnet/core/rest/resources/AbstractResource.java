/*
 * DAPNET CORE PROJECT
 * Copyright (C) 2015
 *
 * Daniel Sialkowski
 *
 * daniel.sialkowski@rwth-aachen.de
 *
 * Institut für Hochfrequenztechnik
 * RWTH AACHEN UNIVERSITY
 * Melatener Str. 25
 * 52074 Aachen
 */

package org.dapnet.core.rest.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.dapnet.core.model.list.Searchable;
import org.dapnet.core.rest.RestAuthorizable;
import org.dapnet.core.rest.RestListener;
import org.dapnet.core.rest.RestSecurity;
import org.dapnet.core.rest.UserExclusionStrategy;
import org.dapnet.core.rest.exceptionHandling.EmptyBodyException;
import org.dapnet.core.rest.exceptionHandling.NoQuorumException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Set;

public abstract class AbstractResource {
    @Context
    UriInfo uriInfo;
    @Context
    HttpHeaders httpHeaders;

    //Gson Helper
    protected static final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    protected static final Gson userGson = new GsonBuilder().setPrettyPrinting()
            .setExclusionStrategies(new UserExclusionStrategy()).create();

    protected Gson getExclusionGson(RestSecurity.SecurityStatus status) {
        //Add here other Exclusion Strategies
        switch (status) {
            case ADMIN:
                return gson;
            case OWNER:
                return gson;
            case USER:
                return userGson;
            case ANYBODY:
                return gson;
            default:
                return gson;
        }
    }

    //Resources are created with Jersey, cannot pass parameters, so using instead static attributes
    protected static RestListener restListener;
    protected static RestSecurity restSecurity;

    public static void setRestListener(RestListener restListenerPar) {
        restListener = restListenerPar;
    }

    public static void setRestSecurity(RestSecurity restSecurityPar) {
        restSecurity = restSecurityPar;
    }

    //Authorization Helper
    protected RestSecurity.SecurityStatus checkAuthorization(RestSecurity.SecurityLevel level, RestAuthorizable restAuthorizable) throws Exception {
        RestSecurity.SecurityStatus status = null;
        if (level != RestSecurity.SecurityLevel.OWNER_ONLY) {
            status = restSecurity.getStatus(httpHeaders, level);
        } else {
            status = restSecurity.getStatus(httpHeaders, level, restAuthorizable);
        }

        switch(status) {
            case INTERNAL_ERROR:
                throw new InternalServerErrorException();
            case UNAUTHORIZED:
                throw new NotAuthorizedException(Response.status(Response.Status.UNAUTHORIZED).build());
            case FORBIDDEN:
                throw new ForbiddenException();
        }

        return status;
    }

    protected RestSecurity.SecurityStatus  checkAuthorization(RestSecurity.SecurityLevel level) throws Exception {
        return checkAuthorization(level, null);
    }

    //Validation Helper
    protected static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    protected void validateObject(Object object)
    {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
        if (constraintViolations.size() != 0) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    //Operation Helper
    protected Response getObject(Object object, RestSecurity.SecurityStatus status) throws Exception {
        if (object == null)
            throw new NotFoundException();

        return Response.status(Response.Status.OK).entity(getExclusionGson(status).toJson(object)).build();
    }

    public Response handleObject(Object object, String methodName, boolean creation, boolean quorumNeeded) throws Exception {
        //Check Quorum
        if (quorumNeeded && !restListener.isQuorum())
            throw new NoQuorumException();

        //Validation
        if (object == null)
            throw new EmptyBodyException();
        validateObject(object);

        //Send to Cluster
        if (restListener.handleStateOperation(null, methodName,
                new Object[]{object}, new Class[]{object.getClass()})) {
            if (creation) {
                return Response.created(uriInfo.getAbsolutePath()).entity(gson.toJson(object)).build();
            } else {
                return Response.ok(gson.toJson(object)).build();
            }
        } else
            throw new InternalServerErrorException();
    }

    protected Response deleteObject(Searchable object, String methodName, boolean quorumNeeded) throws Exception {
        //Check Quorum
        if (quorumNeeded && !restListener.isQuorum()) {
            throw new NoQuorumException();
        }

        //Validation
        if (object == null) {
            throw new NotFoundException();
        }

        //Send to Cluster
        if (restListener.handleStateOperation(null, methodName,
                new Object[]{object.getName()}, new Class[]{String.class}))
            return Response.status(Response.Status.OK).entity(gson.toJson(object)).build();
        else
            throw new InternalServerErrorException();
    }
}