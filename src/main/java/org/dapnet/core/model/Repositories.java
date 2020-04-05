package org.dapnet.core.model;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class Repositories {

	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private final UserRepository userRepo;
	private final CallSignRepository callSignRepo;
	private final TransmitterRepository transmitterRepo;
	private final NodeRepository nodeRepo;
	private final TransmitterGroupRepository groupRepo;
	private final RubricRepository rubricRepo;

	/**
	 * Creates a new repositories object.
	 */
	public Repositories() {
		userRepo = new UserRepositoryImpl();
		callSignRepo = new CallSignRepositoryImpl();
		transmitterRepo = new TransmitterRepositoryImpl();
		nodeRepo = new NodeRepositoryImpl();
		groupRepo = new TransmitterGroupRepositoryImpl();
		rubricRepo = new RubricRepositoryImpl();
	}

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
