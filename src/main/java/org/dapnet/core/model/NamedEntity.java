package org.dapnet.core.model;

/**
 * Abstract base class for a named entity.
 * 
 * @author Philipp Thiel
 */
public abstract class NamedEntity extends Entity {

	private static final long serialVersionUID = 1L;

	/**
	 * Gets the unique name.
	 * 
	 * @return Unique name
	 */
	public abstract String getName();

	/**
	 * Sets the unique name.
	 * 
	 * @param name Unique name
	 */
	public abstract void setName(String name);

	/**
	 * Gets the normalized name. This can be used for map lookups for example.
	 * 
	 * @return Normalized name or {@code null} if no name is set
	 */
	public String getNormalizedNamed() {
		String name = getName();
		if (name != null) {
			name = name.toLowerCase();
		}

		// TODO Throw exception if name is null?
		return name;
	}

}
