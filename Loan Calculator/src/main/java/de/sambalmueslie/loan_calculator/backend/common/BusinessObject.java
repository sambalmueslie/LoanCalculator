/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.common;

/**
 * A business object.
 *
 * @author sambalmueslie 2015
 */
public interface BusinessObject {
	/**
	 * @return the id.
	 */
	long getId();

	/**
	 * @return the name.
	 */
	String getName();

	/**
	 * Register the {@link BusinessObjectChangeListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	void register(BusinessObjectChangeListener listener);

	/**
	 * Unregister the {@link BusinessObjectChangeListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	void unregister(BusinessObjectChangeListener listener);
}
