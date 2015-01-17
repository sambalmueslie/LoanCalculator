/**
 *
 */
package de.sambalmueslie.loan_calculator.model.generic;

/**
 * The change listener for the {@link GenericModel}.
 *
 * @author sambalmueslie 2015
 */
public interface GenericModelListener<T extends GenericModelEntry> {
	/**
	 * A {@link GenericModelEntry} was added.
	 *
	 * @param model
	 *            the {@link GenericModel}
	 * @param entry
	 *            the entry
	 */
	void entryAdded(GenericModel<T> model, T entry);

	/**
	 * A {@link GenericModelEntry} was removed.
	 *
	 * @param model
	 *            the {@link GenericModel}
	 * @param entry
	 *            the entry
	 */
	void entryRemoved(GenericModel<T> model, T entry);

}
