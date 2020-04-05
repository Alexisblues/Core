package org.dapnet.core.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

/**
 * This class implements a transmitter repository backed by a hash map.
 * 
 * @author Philipp Thiel
 */
final class TransmitterRepositoryImpl implements TransmitterRepository {

	private final Map<String, Transmitter> transmitters = new HashMap<>();

	/**
	 * Creates a new empty transmitter repository.
	 */
	public TransmitterRepositoryImpl() {
	}

	/**
	 * Creates a new transmitter repository from the given collection of
	 * transmitters.
	 * 
	 * @param transmitters Transmitters to add to the repository
	 */
	public TransmitterRepositoryImpl(Collection<Transmitter> transmitters) {
		if (transmitters == null) {
			throw new NullPointerException("Transmitter collection must not be null.");
		}

		transmitters.forEach(t -> {
			if (t.getName() == null) {
				throw new NullPointerException("Name must not be null.");
			}

			this.transmitters.put(t.getName().toLowerCase(), t);
		});
	}

	@Override
	public Collection<Transmitter> getAll() {
		Collection<Transmitter> result = new LinkedList<>();

		transmitters.values().forEach(e -> result.add(e));

		return result;
	}

	@Override
	public void add(Transmitter entity) {
		if (entity == null) {
			throw new NullPointerException("Entity must not be null.");
		}

		if (entity.getName() == null) {
			throw new NullPointerException("Name must not be null.");
		}

		final Transmitter prev = transmitters.putIfAbsent(entity.getName().toLowerCase(), entity);
		if (prev != null) {
			throw new IllegalArgumentException("Name is already in use.");
		}
	}

	@Override
	public void remove(Transmitter entity) {
		if (entity == null) {
			throw new NullPointerException("Entity must not be null.");
		}

		if (entity.getName() == null) {
			throw new NullPointerException("Name must not be null.");
		}

		transmitters.remove(entity.getName().toLowerCase());
	}

	@Override
	public long getCount() {
		return transmitters.size();
	}

	@Override
	public Optional<Transmitter> getByName(String name) {
		if (name == null) {
			throw new NullPointerException("Name must not be null.");
		}

		return Optional.ofNullable(transmitters.get(name.toLowerCase()));
	}

	@Override
	public void removeByName(String name) {
		if (name == null) {
			throw new NullPointerException("Name must not be null.");
		}

		transmitters.remove(name.toLowerCase());
	}

}
