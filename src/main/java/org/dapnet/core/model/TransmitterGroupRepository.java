package org.dapnet.core.model;

import java.util.Optional;

/**
 * This interface defines the transmitter group repository.
 * 
 * @author Philipp Thiel
 */
public interface TransmitterGroupRepository extends EntityRepository<TransmitterGroup> {

	/**
	 * Gets a transmitter group by name.
	 * 
	 * @param name Name to look for
	 * @return Transmitter group or empty optional if not found
	 */
	Optional<TransmitterGroup> getByName(String name);

	/**
	 * Removes a transmitter group by name.
	 * 
	 * @param name Name to look for
	 */
	void removeByName(String name);

}
