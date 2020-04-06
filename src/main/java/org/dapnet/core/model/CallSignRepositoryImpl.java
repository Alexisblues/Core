package org.dapnet.core.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

/**
 * This class implements a call sign repository backed by a hash map.
 * 
 * @author Philipp Thiel
 */
public final class CallSignRepositoryImpl implements CallSignRepository {

	private final Map<String, CallSign> callSigns = new HashMap<>();

	/**
	 * Creates a new empty call sign repository.
	 */
	public CallSignRepositoryImpl() {
	}

	/**
	 * Creates a new call sign repository from the given collection of call signs.
	 * 
	 * @param callSigns Call signs to add to the repository
	 */
	public CallSignRepositoryImpl(Collection<CallSign> callSigns) {
		if (callSigns == null) {
			throw new NullPointerException("Call sign collection must not be null.");
		}

		callSigns.forEach(u -> {
			if (u.getName() == null) {
				throw new NullPointerException("Name must not be null.");
			}

			this.callSigns.put(u.getName().toLowerCase(), u);
		});
	}

	@Override
	public Optional<CallSign> getByName(String name) {
		if (name == null) {
			throw new NullPointerException("Name must not be null.");
		}

		return Optional.ofNullable(callSigns.get(name.toLowerCase()));
	}

	@Override
	public void removeByName(String name) {
		if (name == null) {
			throw new NullPointerException("Name must not be null.");
		}

		callSigns.remove(name.toLowerCase());
	}

	@Override
	public Collection<CallSign> getAll() {
		Collection<CallSign> result = new LinkedList<>();

		callSigns.values().forEach(e -> result.add(e));

		return result;
	}

	@Override
	public void add(CallSign entity) {
		if (entity == null) {
			throw new NullPointerException("Entity must not be null.");
		}

		if (entity.getName() == null) {
			throw new NullPointerException("Name must not be null.");
		}

		final CallSign prev = callSigns.putIfAbsent(entity.getName().toLowerCase(), entity);
		if (prev != null) {
			throw new IllegalArgumentException("Name is already in use.");
		}
	}

	@Override
	public void remove(CallSign entity) {
		if (entity == null) {
			throw new NullPointerException("Entity must not be null.");
		}

		if (entity.getName() == null) {
			throw new NullPointerException("Name must not be null.");
		}

		callSigns.remove(entity.getName().toLowerCase());
	}

	@Override
	public long getCount() {
		return callSigns.size();
	}

}
