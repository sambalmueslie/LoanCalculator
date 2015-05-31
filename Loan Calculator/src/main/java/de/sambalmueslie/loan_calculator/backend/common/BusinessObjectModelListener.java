/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.common;

/**
 * The listener for a {@link BusinessObjectModel}.
 *
 * @author sambalmueslie 2015
 */
public interface BusinessObjectModelListener<T extends BusinessObject> {
	/**
	 * A {@link BusinessObject} was added.
	 *
	 * @param model
	 *            the {@link BusinessObjectModel}
	 * @param entry
	 *            the entry
	 */
	void entryAdded(BusinessObjectModel<T> model, T entry);

	/**
	 * A {@link BusinessObject} was removed.
	 *
	 * @param model
	 *            the {@link BusinessObjectModel}
	 * @param entry
	 *            the entry
	 */
	void entryRemoved(BusinessObjectModel<T> model, T entry);
}
