package org.dapnet.core;

import java.util.concurrent.locks.ReadWriteLock;

import org.dapnet.core.cluster.StateTransferObject;
import org.dapnet.core.model.CallSignRepository;
import org.dapnet.core.model.NodeRepository;
import org.dapnet.core.model.RubricRepository;
import org.dapnet.core.model.TransmitterGroupRepository;
import org.dapnet.core.model.TransmitterRepository;
import org.dapnet.core.model.UserRepository;

public interface StateManager {

	ReadWriteLock getLock();

	UserRepository getUsers();

	CallSignRepository getCallsigns();

	TransmitterRepository getTransmitters();

	NodeRepository getNodes();

	TransmitterGroupRepository getTransmitterGroups();

	RubricRepository getRubrics();

	void applyState(StateTransferObject stateTransfer);

	StateTransferObject getState();

}
