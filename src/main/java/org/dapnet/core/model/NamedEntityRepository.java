package org.dapnet.core.model;

import java.util.Collection;
import java.util.Optional;

/**
 * Interface for accessing a named entity repository.
 * 
 * @author Philipp Thiel
 *
 * @param <T> Entity type
 */
public interface NamedEntityRepository<T extends NamedEntity> {

	/**
	 * Gets an entity by its unique name.
	 * 
	 * @param name Unique entity name
	 * @return Entity or empty optional if not found
	 */
	Optional<T> getByName(String name);

	/**
	 * Gets a collection of all entities managed by the repository.
	 * 
	 * @return Entity collection
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

}
