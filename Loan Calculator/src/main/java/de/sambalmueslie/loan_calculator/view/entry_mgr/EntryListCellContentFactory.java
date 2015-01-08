/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr;

import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;

/**
 * The {@link EntryListCellContent} factory.
 *
 * @author sambalmueslie 2015
 */
public class EntryListCellContentFactory<T extends GenericModelEntry<T>> {

	/**
	 * Constructor.
	 */
	public EntryListCellContentFactory(final Class<T> type, final Class<? extends EntryListCellContent<T>> contentType) {
		this.type = type;
		this.contentType = contentType;
	}

	/**
	 * @return create a new {@link EntryListCellContent}.
	 */
	EntryListCellContent<T> create() {
		try {
			final EntryListCellContent<T> content = contentType.newInstance();
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

	/** the {@link EntryListCellContent} type.. */
	private final Class<? extends EntryListCellContent<T>> contentType;
	/** the {@link ViewActionListener}. */
	private ViewActionListener listener;

	/** the type. */
	private final Class<T> type;
}
