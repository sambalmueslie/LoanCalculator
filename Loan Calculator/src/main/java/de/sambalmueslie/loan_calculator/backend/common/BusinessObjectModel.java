/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.common;

import java.util.Collection;

/**
 * The {@link BusinessObject} model.
 *
 * @author sambalmueslie 2015
 */
public interface BusinessObjectModel<T extends BusinessObject> {

	/**
	 * Get a {@link BusinessObject} by id.
	 *
	 * @param id
	 *            the id
	 * @return the {@link BusinessObject} or <code>null</code> if not found
	 */
	T get(long id);

	/**
	 * @return a {@link Collection} of all {@link BusinessObject}.
	 */
	Collection<T> getAll();

	/**
	 * @return <code>true</code> if empty, otherwise <code>false</code>.
	 */
	boolean isEmpty();

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
