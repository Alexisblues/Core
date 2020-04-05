package org.dapnet.core.model;

import java.util.Collection;

/**
 * This class implements an entity repository backed by a set.
 * 
 * @author Philipp Thiel
 *
 * @param <T> Entity type
 */
public interface EntityRepository<T extends Entity> {

	/**
	 * Gets a collection of all entities managed by the repository.
	 * 
	 * @return Collection of entities
	 */
	Collection<T> getAll();

	/**
	 * Adds a new entity to the repository.
	 * 
	 * @param entity Entity to add
	 */
	void add(T entity);

	/**
	 * Removes an entity from the repository.
	 * 
	 * @param entity Entity to remove
	 */
	void remove(T entity);

	/**
	 * Gets the number of entities managed by this repository.
	 * 
	 * @return Entity count
	 */
	long getCount();

}
