/**
 *
 */
package de.sambalmueslie.loan_calculator.model.generic;

/**
 * A model entry.
 *
 * @author sambalmueslie 2015
 */
public interface GenericModelEntry {
	/**
	 * @return the id.
	 */
	long getId();

	/**
	 * @return the name.
	 */
	String getName();

	/**
	 * Register the {@link L}.
	 *
	 * @param listener
	 *            the listener
	 */
	void register(GenericModelEntryChangeListener listener);

	/**
	 * Unregister the {@link L}.
	 *
	 * @param listener
	 *            the listener
	 */
	void unregister(GenericModelEntryChangeListener listener);
}
