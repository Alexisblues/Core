package org.dapnet.core.cluster;

import java.io.Serializable;
import java.util.Collection;

import org.dapnet.core.model.Call;
import org.dapnet.core.model.CallSign;
import org.dapnet.core.model.NewsList;
import org.dapnet.core.model.Node;
import org.dapnet.core.model.Rubric;
import org.dapnet.core.model.Transmitter;
import org.dapnet.core.model.TransmitterGroup;
import org.dapnet.core.model.User;

/**
 * This object contains the full object state and is used to perform state
 * transfers between nodes.
 * 
 * @author Philipp Thiel
 */
public final class StateTransferObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private Collection<User> users;
	private Collection<Node> nodes;
	private Collection<CallSign> callSigns;
	private Collection<Transmitter> transmitters;
	private Collection<TransmitterGroup> transmitterGroups;
	private Collection<Rubric> rubrics;
	private Collection<Call> calls;
	private Collection<NewsList> news;

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	public Collection<Node> getNodes() {
		return nodes;
	}

	public void setNodes(Collection<Node> nodes) {
		this.nodes = nodes;
	}

	public Collection<CallSign> getCallSigns() {
		return callSigns;
	}

	public void setCallSigns(Collection<CallSign> callSigns) {
		this.callSigns = callSigns;
	}

	public Collection<Transmitter> getTransmitters() {
		return transmitters;
	}

	public void setTransmitters(Collection<Transmitter> transmitters) {
		this.transmitters = transmitters;
	}

	public Collection<TransmitterGroup> getTransmitterGroups() {
		return transmitterGroups;
	}

	public void setTransmitterGroups(Collection<TransmitterGroup> transmitterGroups) {
		this.transmitterGroups = transmitterGroups;
	}

	public Collection<Rubric> getRubrics() {
		return rubrics;
	}

	public void setRubrics(Collection<Rubric> rubrics) {
		this.rubrics = rubrics;
	}

	public Collection<Call> getCalls() {
		return calls;
	}

	public void setCalls(Collection<Call> calls) {
		this.calls = calls;
	}

	public Collection<NewsList> getNews() {
		return news;
	}

	public void setNews(Collection<NewsList> news) {
		this.news = news;
	}

}
