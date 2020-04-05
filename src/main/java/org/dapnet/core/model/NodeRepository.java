package org.dapnet.core.model;

import java.util.Optional;

/**
 * This interface defines the node repository.
 * 
 * @author Philipp Thiel
 */
public interface NodeRepository extends EntityRepository<Node> {

	/**
	 * Gets a node by name.
	 * 
	 * @param name Unique name to look for
	 * @return Node object or empty optional
	 */
	Optional<Node> getByName(String name);

	/**
	 * Removes a node by name.
	 * 
	 * @param name Unique name to look for
	 */
	void removeByName(String name);

}
