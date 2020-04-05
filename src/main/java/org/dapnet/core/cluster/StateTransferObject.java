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
 * This object contains the full database state and is used to perform state
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

	/**
	 * Gets the user collection.
	 * 
	 * @return Collection of users
	 */
	public Collection<User> getUsers() {
		return users;
	}

	/**
	 * Sets the user collection.
	 * 
	 * @param users Collection of users
	 */
	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	/**
	 * Gets the node collection.
	 * 
	 * @return Collection of nodes
	 */
	public Collection<Node> getNodes() {
		return nodes;
	}

	/**
	 * Sets the node collection.
	 * 
	 * @param nodes Collection of nodes
	 */
	public void setNodes(Collection<Node> nodes) {
		this.nodes = nodes;
	}

	/**
	 * Sets the call sign collection.
	 * 
	 * @return Collection of call signs
	 */
	public Collection<CallSign> getCallSigns() {
		return callSigns;
	}

	/**
	 * Sets the call sign collection.
	 * 
	 * @param callSigns Collection of call signs
	 */
	public void setCallSigns(Collection<CallSign> callSigns) {
		this.callSigns = callSigns;
	}

	/**
	 * Gets the transmitter collection.
	 * 
	 * @return Collection of transmitters
	 */
	public Collection<Transmitter> getTransmitters() {
		return transmitters;
	}

	/**
	 * Sets the transmitter collection.
	 * 
	 * @param transmitters Collection of transmitters
	 */
	public void setTransmitters(Collection<Transmitter> transmitters) {
		this.transmitters = transmitters;
	}

	/**
	 * Gets the transmitter group collection.
	 * 
	 * @return Collection of transmitter groups
	 */
	public Collection<TransmitterGroup> getTransmitterGroups() {
		return transmitterGroups;
	}

	/**
	 * Sets the transmitter group collection.
	 * 
	 * @param transmitterGroups Collection of transmitter groups
	 */
	public void setTransmitterGroups(Collection<TransmitterGroup> transmitterGroups) {
		this.transmitterGroups = transmitterGroups;
	}

	/**
	 * Gets the rubric collection.
	 * 
	 * @return Collection of rubrics
	 */
	public Collection<Rubric> getRubrics() {
		return rubrics;
	}

	/**
	 * Sets the rubric collection.
	 * 
	 * @param rubrics Collection of rubrics
	 */
	public void setRubrics(Collection<Rubric> rubrics) {
		this.rubrics = rubrics;
	}

	/**
	 * Gets the call collection.
	 * 
	 * @return Collection of calls
	 */
	public Collection<Call> getCalls() {
		return calls;
	}

	/**
	 * Sets the call collection.
	 * 
	 * @param calls Collection of calls
	 */
	public void setCalls(Collection<Call> calls) {
		this.calls = calls;
	}

	/**
	 * Gets the news list collection.
	 * 
	 * @return Collection of news lists.
	 */
	public Collection<NewsList> getNews() {
		return news;
	}

	/**
	 * Sets the news list collection.
	 * 
	 * @param news Collection of news list
	 */
	public void setNews(Collection<NewsList> news) {
		this.news = news;
	}

}
