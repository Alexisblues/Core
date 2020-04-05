package org.dapnet.core.model;

import java.util.concurrent.locks.ReadWriteLock;

public interface Repositories {

	ReadWriteLock getLock();

	UserRepository getUsers();

	CallSignRepository getCallsigns();

	TransmitterRepository getTransmitters();

	NodeRepository getNodes();

	TransmitterGroupRepository getTransmitterGroups();

	RubricRepository getRubrics();

//	EntityRepository<Call> getCalls();
//
//	EntityRepository<NewsList> getNewsLists();
//
//	EntityRepository<Pager> getPagers();

}
