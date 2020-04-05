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
	 * Default constructor.
	 */
	protected Entity() {
	}

	/**
	 * Constructs a new entity by copying an existing entity.
	 * 
	 * @param other Entity to copy from
	 */
	protected Entity(Entity other) {
		if (other == null) {
			throw new NullPointerException("Other entity must not be null.");
		}

		lastUpdate = other.lastUpdate;
	}

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
