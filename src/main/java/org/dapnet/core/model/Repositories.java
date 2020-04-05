package org.dapnet.core.model;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class Repositories {

	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private UserRepository userRepo = new UserRepositoryImpl();
	private CallSignRepository callSignRepo = new CallSignRepositoryImpl();
	private TransmitterRepository transmitterRepo = new TransmitterRepositoryImpl();
	private NodeRepository nodeRepo = new NodeRepositoryImpl();
	private TransmitterGroupRepository groupRepo = new TransmitterGroupRepositoryImpl();
	private RubricRepository rubricRepo = new RubricRepositoryImpl();

	public ReadWriteLock getLock() {
		return lock;
	}

	public UserRepository getUsers() {
		return userRepo;
	}

	public CallSignRepository getCallsigns() {
		return callSignRepo;
	}

	public TransmitterRepository getTransmitters() {
		return transmitterRepo;
	}

	public NodeRepository getNodes() {
		return nodeRepo;
	}

	public TransmitterGroupRepository getTransmitterGroups() {
		return groupRepo;
	}

	public RubricRepository getRubrics() {
		return rubricRepo;
	}

//	EntityRepository<Call> getCalls();
//
//	EntityRepository<NewsList> getNewsLists();
//
//	EntityRepository<Pager> getPagers();

}
