/**
 *
 */
package de.sambalmueslie.loan_calculator.model.generic;

/**
 * The change listener for a {@link GenericModelEntry}.
 *
 * @author sambalmueslie 2015
 */
@FunctionalInterface
public interface GenericModelEntryChangeListener {

	/**
	 * The {@link GenericModelEntry} has changed.
	 *
	 * @param entry
	 *            the affected entry.
	 */
	void entryChanged(GenericModelEntry entry);

}
