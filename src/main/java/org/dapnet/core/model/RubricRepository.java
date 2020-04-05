package org.dapnet.core.model;

import java.util.Optional;

/**
 * This interface defines a rubric repository.
 * 
 * @author Philipp Thiel
 */
public interface RubricRepository extends EntityRepository<Rubric> {

	/**
	 * Gets a rubric by name.
	 * 
	 * @param name Name to look for
	 * @return Rubric or empty optional if not found
	 */
	Optional<Rubric> getByName(String name);

	/**
	 * Removes a rubric by name.
	 * 
	 * @param name Name to look for
	 */
	void removeByName(String name);

}
