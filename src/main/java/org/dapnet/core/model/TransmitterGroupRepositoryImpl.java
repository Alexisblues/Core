package org.dapnet.core.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

/**
 * This class implements a transmitter group repository backed by a hash map.
 * 
 * @author Philipp Thiel
 */
public final class TransmitterGroupRepositoryImpl implements TransmitterGroupRepository {

	private final Map<String, TransmitterGroup> groups = new HashMap<>();

	/**
	 * Creates a new empty transmitter group repository.
	 */
	public TransmitterGroupRepositoryImpl() {
	}

	/**
	 * Creates a new transmitter group repository from the given collection of
	 * groups.
	 * 
	 * @param groups Transmitter groups to add to the repository
	 */
	public TransmitterGroupRepositoryImpl(Collection<TransmitterGroup> groups) {
		if (groups == null) {
			throw new NullPointerException("Transmitter group collection must not be null.");
		}

		groups.forEach(t -> {
			if (t.getName() == null) {
				throw new NullPointerException("Name must not be null.");
			}

			this.groups.put(t.getName().toLowerCase(), t);
		});
	}

	@Override
	public Collection<TransmitterGroup> getAll() {
		Collection<TransmitterGroup> result = new LinkedList<>();

		groups.values().forEach(g -> result.add(g));

		return result;
	}

	@Override
	public void add(TransmitterGroup entity) {
		if (entity == null) {
			throw new NullPointerException("Entity must not be null.");
		}

		if (entity.getName() == null) {
			throw new NullPointerException("Name must not be null.");
		}

		final TransmitterGroup prev = groups.putIfAbsent(entity.getName().toLowerCase(), entity);
		if (prev != null) {
			throw new IllegalArgumentException("Name is already in use.");
		}
	}

	@Override
	public void remove(TransmitterGroup entity) {
		if (entity == null) {
			throw new NullPointerException("Entity must not be null.");
		}

		if (entity.getName() == null) {
			throw new NullPointerException("Name must not be null.");
		}

		groups.remove(entity.getName().toLowerCase());
	}

	@Override
	public long getCount() {
		return groups.size();
	}

	@Override
	public Optional<TransmitterGroup> getByName(String name) {
		if (name == null) {
			throw new NullPointerException("Name must not be null.");
		}

		return Optional.ofNullable(groups.get(name.toLowerCase()));
	}

	@Override
	public void removeByName(String name) {
		if (name == null) {
			throw new NullPointerException("Name must not be null.");
		}

		groups.remove(name.toLowerCase());
	}

}
