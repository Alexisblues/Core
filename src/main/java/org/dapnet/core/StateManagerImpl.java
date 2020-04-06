package org.dapnet.core;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.dapnet.core.cluster.StateTransferObject;
import org.dapnet.core.model.CallSignRepository;
import org.dapnet.core.model.CallSignRepositoryImpl;
import org.dapnet.core.model.NodeRepository;
import org.dapnet.core.model.NodeRepositoryImpl;
import org.dapnet.core.model.RubricRepository;
import org.dapnet.core.model.RubricRepositoryImpl;
import org.dapnet.core.model.TransmitterGroupRepository;
import org.dapnet.core.model.TransmitterGroupRepositoryImpl;
import org.dapnet.core.model.TransmitterRepository;
import org.dapnet.core.model.TransmitterRepositoryImpl;
import org.dapnet.core.model.UserRepository;
import org.dapnet.core.model.UserRepositoryImpl;

final class StateManagerImpl implements StateManager {

	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private RepoHolder repos = new RepoHolder();

	@Override
	public ReadWriteLock getLock() {
		return lock;
	}

	@Override
	public UserRepository getUsers() {
		return repos.userRepo;
	}

	@Override
	public CallSignRepository getCallsigns() {
		return repos.callSignRepo;
	}

	@Override
	public TransmitterRepository getTransmitters() {
		return repos.transmitterRepo;
	}

	@Override
	public NodeRepository getNodes() {
		return repos.nodeRepo;
	}

	@Override
	public TransmitterGroupRepository getTransmitterGroups() {
		return repos.groupRepo;
	}

	@Override
	public RubricRepository getRubrics() {
		return repos.rubricRepo;
	}

	@Override
	public void applyState(StateTransferObject stateTransfer) {
		RepoHolder newRepos = new RepoHolder(stateTransfer);

		lock.writeLock().lock();
		try {
			repos = newRepos;
		} finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public StateTransferObject getState() {
		StateTransferObject state = new StateTransferObject();

		lock.readLock().lock();

		try {
			state.setUsers(repos.userRepo.getAll());
			state.setCallSigns(repos.callSignRepo.getAll());
			state.setNodes(repos.nodeRepo.getAll());
			state.setTransmitters(repos.transmitterRepo.getAll());
			state.setTransmitterGroups(repos.groupRepo.getAll());
			state.setRubrics(repos.rubricRepo.getAll());
		} finally {
			lock.readLock().unlock();
		}

		return state;
	}

	private static class RepoHolder {
		private final UserRepository userRepo;
		private final CallSignRepository callSignRepo;
		private final TransmitterRepository transmitterRepo;
		private final NodeRepository nodeRepo;
		private final TransmitterGroupRepository groupRepo;
		private final RubricRepository rubricRepo;

		public RepoHolder() {
			userRepo = new UserRepositoryImpl();
			callSignRepo = new CallSignRepositoryImpl();
			transmitterRepo = new TransmitterRepositoryImpl();
			nodeRepo = new NodeRepositoryImpl();
			groupRepo = new TransmitterGroupRepositoryImpl();
			rubricRepo = new RubricRepositoryImpl();
		}

		public RepoHolder(StateTransferObject stateObject) {
			userRepo = new UserRepositoryImpl(stateObject.getUsers());
			callSignRepo = new CallSignRepositoryImpl(stateObject.getCallSigns());
			transmitterRepo = new TransmitterRepositoryImpl(stateObject.getTransmitters());
			nodeRepo = new NodeRepositoryImpl(stateObject.getNodes());
			groupRepo = new TransmitterGroupRepositoryImpl(stateObject.getTransmitterGroups());
			rubricRepo = new RubricRepositoryImpl(stateObject.getRubrics());
		}
	}

}
