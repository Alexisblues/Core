package org.dapnet.core.model;

import java.util.Optional;

/**
 * This interface defines the call sign repository.
 * 
 * @author Philipp Thiel
 */
public interface CallSignRepository extends EntityRepository<CallSign> {

	/**
	 * Gets a call sign by name.
	 * 
	 * @param name Name to look for
	 * @return Call sign or empty optional if not found
	 */
	Optional<CallSign> getByName(String name);

	/**
	 * Removes a call sign object by its call sign.
	 * 
	 * @param name Name to look for
	 */
	void removeByName(String name);

}
