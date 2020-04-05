package org.dapnet.core.model;

import java.util.Optional;

/**
 * This interface defines the transmitter repository.
 * 
 * @author Philipp Thiel
 */
public interface TransmitterRepository extends EntityRepository<Transmitter> {

	/**
	 * Gets a transmitter by name.
	 * 
	 * @param name Transmitter name to look for
	 * @return Transmitter object or empty optional if not found
	 */
	Optional<Transmitter> getByName(String name);

	/**
	 * Removes a transmitter by name.
	 * 
	 * @param name Transmitter name to look for
	 */
	void removeByName(String name);

}
