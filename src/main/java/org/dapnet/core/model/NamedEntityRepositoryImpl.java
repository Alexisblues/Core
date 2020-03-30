package org.dapnet.core.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

/**
 * This class implements a named entity repository.
 * 
 * @author Philipp Thiel
 *
 * @param <T> Entity type
 */
class NamedEntityRepositoryImpl<T extends NamedEntity> implements NamedEntityRepository<T> {

	private final Map<String, T> entities = new HashMap<>();

	@Override
	public Optional<T> getByName(String name) {
		if (name == null) {
			throw new NullPointerException("Name must not be null.");
		}

		name = name.toLowerCase();

		return Optional.ofNullable(entities.get(name));
	}

	@Override
	public Collection<T> getAll() {
		Collection<T> result = new LinkedList<>();

		entities.values().stream().forEach(e -> result.add(e));

		return result;
	}

	@Override
	public void add(T entity) {
		if (entity == null) {
			throw new NullPointerException("Entity must not be null.");
		}

		final String name = entity.getNormalizedNamed();
		if (name == null) {
			throw new NullPointerException("Entity name must not be null.");
		}

		T prev = entities.putIfAbsent(name, entity);
		if (prev != null) {
			throw new IllegalArgumentException("Entity name is already in use.");
		}
	}

	@Override
	public void remove(T entity) {
		if (entity == null) {
			throw new NullPointerException("Entity must not be null.");
		}

		final String name = entity.getNormalizedNamed();
		if (name == null) {
			throw new NullPointerException("Entity name must not be null.");
		}

		entities.remove(name);
	}

}
