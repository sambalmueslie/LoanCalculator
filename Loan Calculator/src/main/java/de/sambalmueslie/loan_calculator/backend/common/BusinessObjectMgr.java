/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.common;

/**
 * A {@link BusinessObject} manager.
 *
 * @author sambalmueslie 2015
 * @param <T>
 *            the {@link BusinessObject} type
 */
public interface BusinessObjectMgr<T extends BusinessObject> {
	/**
	 * Register.
	 * 
	 * @param listener
	 *            the {@link BusinessObjectModelListener}.
	 */
	void register(BusinessObjectModelListener<T> listener);

	/**
	 * Unregister.
	 * 
	 * @param listener
	 *            the {@link BusinessObjectModelListener}.
	 */
	void unregister(BusinessObjectModelListener<T> listener);

}
