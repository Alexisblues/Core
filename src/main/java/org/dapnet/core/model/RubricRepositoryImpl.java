package org.dapnet.core.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

/**
 * This class implements a rubric repository backed by a hash map.
 * 
 * @author Philipp Thiel
 */
final class RubricRepositoryImpl implements RubricRepository {

	private final Map<String, Rubric> rubrics = new HashMap<>();

	/**
	 * Creates a new empty rubric repository.
	 */
	public RubricRepositoryImpl() {
	}

	/**
	 * Creates a new rubric repository from the given collection of rubrics.
	 * 
	 * @param rubrics Rubrics to add to the repository
	 */
	public RubricRepositoryImpl(Collection<Rubric> rubrics) {
		if (rubrics == null) {
			throw new NullPointerException("Rubric collection must not be null.");
		}

		rubrics.forEach(r -> {
			if (r.getName() == null) {
				throw new NullPointerException("Name must not be null.");
			}

			this.rubrics.put(r.getName().toLowerCase(), r);
		});
	}

	@Override
	public Collection<Rubric> getAll() {
		Collection<Rubric> result = new LinkedList<>();

		rubrics.values().forEach(r -> result.add(r));

		return result;
	}

	@Override
	public void add(Rubric entity) {
		if (entity == null) {
			throw new NullPointerException("Entity must not be null.");
		}

		if (entity.getName() == null) {
			throw new NullPointerException("Name must not be null.");
		}

		final Rubric prev = rubrics.putIfAbsent(entity.getName().toLowerCase(), entity);
		if (prev != null) {
			throw new IllegalArgumentException("Name is already in use.");
		}
	}

	@Override
	public void remove(Rubric entity) {
		if (entity == null) {
			throw new NullPointerException("Entity must not be null.");
		}

		if (entity.getName() == null) {
			throw new NullPointerException("Name must not be null.");
		}

		rubrics.remove(entity.getName().toLowerCase());
	}

	@Override
	public long getCount() {
		return rubrics.size();
	}

	@Override
	public Optional<Rubric> getByName(String name) {
		if (name == null) {
			throw new NullPointerException("Name must not be null.");
		}

		return Optional.ofNullable(rubrics.get(name.toLowerCase()));
	}

	@Override
	public void removeByName(String name) {
		if (name == null) {
			throw new NullPointerException("Name must not be null.");
		}

		rubrics.remove(name.toLowerCase());
	}

}
