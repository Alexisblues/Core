package org.dapnet.core.model;

import java.io.Serializable;
import java.time.Instant;

/**
 * Abstract base class for all entities.
 * 
 * @author Philipp Thiel
 */
public abstract class Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	private Instant lastUpdate;

	/**
	 * Gets the last update timestamp.
	 * 
	 * @return Last update
	 */
	public Instant getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * Sets the last update timestamp.
	 * 
	 * @param lastUpdate Last update
	 */
	public void setLastUpdate(Instant lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
