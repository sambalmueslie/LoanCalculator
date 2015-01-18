/**
 *
 */
package de.sambalmueslie.loan_calculator.view.tree;

import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;

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
	public EntryTreeContent(final GenericModelEntry entry, final EntryTreeContentType type) {
		this.entry = entry;
		this.type = type;
	}

	/**
	 * @return the {@link #entry}
	 */
	public GenericModelEntry getContent() {
		return entry;
	}

	/**
	 * @return the {@link #type}
	 */
	public EntryTreeContentType getType() {
		return type;
	}

	/** the {@link GenericModelEntry}. */
	private final GenericModelEntry entry;
	/** the {@link EntryTreeContentType}. */
	private final EntryTreeContentType type;
}
