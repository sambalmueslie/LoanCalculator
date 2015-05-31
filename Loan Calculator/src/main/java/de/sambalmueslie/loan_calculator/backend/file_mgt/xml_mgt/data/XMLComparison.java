/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.file_mgt.xml_mgt.data;

import java.util.Set;

import de.sambalmueslie.loan_calculator.backend.compare_mgt.Comparison;

/**
 * The xml {@link Comparison}.
 *
 * @author sambalmueslie 2015
 */
public class XMLComparison {

	/**
	 * @return the {@link #elementIds}
	 */
	public Set<Long> getElementIds() {
		return elementIds;
	}

	/**
	 * @return the {@link #id}
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the {@link #name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the {@link #type}
	 */
	public Class<?> getType() {
		return type;
	}

	/**
	 * @param elementIds
	 *            the elementIds to set
	 */
	public void setElementIds(final Set<Long> elementIds) {
		this.elementIds = elementIds;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final long id) {
		this.id = id;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(final Class<?> type) {
		this.type = type;
	}

	/** the loan ids. */
	private Set<Long> elementIds;
	/** the id. */
	private long id;
	/** the name. */
	private String name;
	/** the type. */
	private Class<?> type;

}
