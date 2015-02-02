/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.tree;

import de.sambalmueslie.loan_calculator.backend.common.BusinessObject;

/**
 * A entry tree content.
 *
 * @author sambalmueslie 2015
 */
public class EntryTreeContent {
	/**
	 * Constructor.
	 *
	 * @param entry
	 *            {@link #entry}
	 * @param type
	 *            {@link #type}
	 */
	public EntryTreeContent(final BusinessObject entry, final EntryTreeContentType type) {
		this.entry = entry;
		this.type = type;
	}

	/**
	 * @return the {@link #entry}
	 */
	public BusinessObject getContent() {
		return entry;
	}

	/**
	 * @return the {@link #type}
	 */
	public EntryTreeContentType getType() {
		return type;
	}

	/** the {@link BusinessObject}. */
	private final BusinessObject entry;
	/** the {@link EntryTreeContentType}. */
	private final EntryTreeContentType type;
}
