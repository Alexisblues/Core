package org.dapnet.core.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

/**
 * This class implements a node repository backed by a hash map.
 * 
 * @author Philipp Thiel
 */
public final class NodeRepositoryImpl implements NodeRepository {

	private final Map<String, Node> nodes = new HashMap<>();

	/**
	 * Creates a new empty node repository.
	 */
	public NodeRepositoryImpl() {
	}

	/**
	 * Creates a node repository from the given collection of nodes.
	 * 
	 * @param nodes Nodes to add to the repository
	 */
	public NodeRepositoryImpl(Collection<Node> nodes) {
		if (nodes == null) {
			throw new NullPointerException("Node collection must not be null.");
		}

		nodes.forEach(n -> {
			if (n.getName() == null) {
				throw new NullPointerException("Name must not be null.");
			}

			this.nodes.put(n.getName().toLowerCase(), n);
		});
	}

	@Override
	public Collection<Node> getAll() {
		Collection<Node> result = new LinkedList<>();

		nodes.values().forEach(g -> result.add(g));

		return result;
	}

	@Override
	public void add(Node entity) {
		if (entity == null) {
			throw new NullPointerException("Entity must not be null.");
		}

		if (entity.getName() == null) {
			throw new NullPointerException("Name must not be null.");
		}

		final Node prev = nodes.putIfAbsent(entity.getName().toLowerCase(), entity);
		if (prev != null) {
			throw new IllegalArgumentException("Name is already in use.");
		}
	}

	@Override
	public void remove(Node entity) {
		if (entity == null) {
			throw new NullPointerException("Entity must not be null.");
		}

		if (entity.getName() == null) {
			throw new NullPointerException("Name must not be null.");
		}

		nodes.remove(entity.getName().toLowerCase());
	}

	@Override
	public long getCount() {
		return nodes.size();
	}

	@Override
	public Optional<Node> getByName(String name) {
		if (name == null) {
			throw new NullPointerException("Name must not be null.");
		}

		return Optional.ofNullable(nodes.get(name.toLowerCase()));
	}

	@Override
	public void removeByName(String name) {
		if (name == null) {
			throw new NullPointerException("Name must not be null.");
		}

		nodes.remove(name.toLowerCase());
	}

}
