package org.dapnet.core.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

/**
 * This class implements a user repository backed by a hash map.
 * 
 * @author Philipp Thiel
 */
public final class UserRepositoryImpl implements UserRepository {

	private final Map<String, User> users = new HashMap<>();

	/**
	 * Creates a new empty user repository.
	 */
	public UserRepositoryImpl() {
	}

	/**
	 * Creates a new user repositoy from the given collection of users.
	 * 
	 * @param users Users to add to the repository
	 */
	public UserRepositoryImpl(Collection<User> users) {
		if (users == null) {
			throw new NullPointerException("User collection must not be null.");
		}

		users.forEach(u -> {
			if (u.getName() == null) {
				throw new NullPointerException("Name must not be null.");
			}

			this.users.put(u.getName().toLowerCase(), u);
		});
	}

	@Override
	public Optional<User> getByName(String name) {
		if (name == null) {
			throw new NullPointerException("Name must not be null.");
		}

		return Optional.ofNullable(users.get(name.toLowerCase()));
	}

	@Override
	public void removeByName(String name) {
		if (name == null) {
			throw new NullPointerException("Name must not be null.");
		}

		users.remove(name.toLowerCase());
	}

	@Override
	public Collection<User> getAll() {
		Collection<User> result = new LinkedList<>();

		users.values().forEach(e -> result.add(e));

		return result;
	}

	@Override
	public void add(User entity) {
		if (entity == null) {
			throw new NullPointerException("Entity must not be null.");
		}

		if (entity.getName() == null) {
			throw new NullPointerException("User name must not be null.");
		}

		final User prev = users.putIfAbsent(entity.getName().toLowerCase(), entity);
		if (prev != null) {
			throw new IllegalArgumentException("User name is already in use.");
		}
	}

	@Override
	public void remove(User entity) {
		if (entity == null) {
			throw new NullPointerException("Entity must not be null.");
		}

		if (entity.getName() == null) {
			throw new NullPointerException("User name must not be null.");
		}

		users.remove(entity.getName().toLowerCase());
	}

	@Override
	public long getCount() {
		return users.size();
	}

}
