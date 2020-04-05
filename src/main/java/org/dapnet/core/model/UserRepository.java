package org.dapnet.core.model;

import java.util.Optional;

/**
 * This interface defines the user repository.
 * 
 * @author Philipp Thiel
 */
public interface UserRepository extends EntityRepository<User> {

	/**
	 * Gets a user by name.
	 * 
	 * @param name Unique user name
	 * @return User or empty optional if not found
	 */
	Optional<User> getByName(String name);

	/**
	 * Remove a user by its unique name.
	 * 
	 * @param name Name of the user to remove
	 */
	void removeByName(String name);

}
