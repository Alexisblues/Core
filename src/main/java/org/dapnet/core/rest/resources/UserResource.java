/*
 * DAPNET CORE PROJECT
 * Copyright (C) 2016
 *
 * Daniel Sialkowski
 *
 * daniel.sialkowski@rwth-aachen.de
 *
 * Institute of High Frequency Technology
 * RWTH AACHEN UNIVERSITY
 * Melatener Str. 25
 * 52074 Aachen
 */

package org.dapnet.core.rest.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.dapnet.core.HashUtil;
import org.dapnet.core.model.User;
import org.dapnet.core.rest.RestSecurity;
import org.dapnet.core.rest.exceptionHandling.EmptyBodyException;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource extends AbstractResource {
	@GET
	public Response getUsers() throws Exception {
		RestSecurity.SecurityStatus status = checkAuthorization(RestSecurity.SecurityLevel.USER_ONLY);
		return getObject(restListener.getState().getUsers().values(), status);
	}

	@GET
	@Path("{user}")
	public Response getUser(@PathParam("user") String userName) throws Exception {
		if (userName != null) {
			userName = userName.toLowerCase();
		}

		User user = restListener.getState().getUsers().get(userName);
		RestSecurity.SecurityStatus status = checkAuthorization(RestSecurity.SecurityLevel.USER_ONLY, user);
		return getObject(user, status);
	}

	@PUT
	@Path("{user}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putUser(@PathParam("user") String userName, String userJSON) throws Exception {
		// Start request processing only if at least USER
		checkAuthorization(RestSecurity.SecurityLevel.USER_ONLY);

		if (userName != null) {
			userName = userName.toLowerCase();
		}

		final User oldUser = restListener.getState().getUsers().get(userName);

		// Create User from received data
		final User user = gson.fromJson(userJSON, User.class);
		if (user != null) {
			String hash = user.getHash();
			if ((hash == null || hash.isEmpty()) && oldUser != null) {
				user.setHash(oldUser.getHash());
			} else {
				user.setHash(HashUtil.createHash(user.getHash()));
			}

			user.setName(userName);
		} else {
			throw new EmptyBodyException();
		}

		if (user.isAdmin()) {
			checkAuthorization(RestSecurity.SecurityLevel.ADMIN_ONLY);
		} else {
			if (oldUser != null) {
				checkAuthorization(RestSecurity.SecurityLevel.OWNER_ONLY, oldUser);
			} else {
				checkAuthorization(RestSecurity.SecurityLevel.ADMIN_ONLY);
			}
		}

		return handleObject(user, "putUser", oldUser == null, true);
	}

	@DELETE
	@Path("{user}")
	public Response deleteUser(@PathParam("user") String user) throws Exception {
		if (user != null) {
			user = user.toLowerCase();
		}

		final User oldUser = restListener.getState().getUsers().get(user);
		if (oldUser != null) {
			// only owner can delete object
			checkAuthorization(RestSecurity.SecurityLevel.OWNER_ONLY, oldUser);
		} else {
			// only user will get message that object does not exist
			checkAuthorization(RestSecurity.SecurityLevel.ADMIN_ONLY);
		}

		return deleteObject(oldUser.getNormalizedNamed(), "deleteUser", true);
	}
}
