/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr.tree;

import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;

/**
 * The factory for the {@link EntryTreeItemContent}.
 *
 * @author sambalmueslie 2015
 */
public class EntryTreeItemContentFactory<T extends GenericModelEntry<T>> {
	/**
	 * Constructor.
	 */
	public EntryTreeItemContentFactory(final Class<T> type, final Class<? extends EntryTreeItemContent<T>> contentType) {
		this.type = type;
		this.contentType = contentType;
	}

	/**
	 * @return create a new {@link EntryTreeItemContent}.
	 */
	EntryTreeItemContent<T> create() {
		try {
			final EntryTreeItemContent<T> content = contentType.newInstance();
			content.set(listener);
			return content;
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
	}

	/**
	 * @return the type.
	 */
	Class<T> getType() {
		return type;
	}

	/**
	 * @param listener
	 *            the listener to set
	 */
	void setListener(final ViewActionListener listener) {
		this.listener = listener;
	}

	/** the {@link EntryTreeItemContent} type.. */
	private final Class<? extends EntryTreeItemContent<T>> contentType;
	/** the {@link ViewActionListener}. */
	private ViewActionListener listener;
	/** the type. */
	private final Class<T> type;
}
