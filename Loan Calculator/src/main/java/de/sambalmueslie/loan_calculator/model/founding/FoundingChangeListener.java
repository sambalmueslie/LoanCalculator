/**
 *
 */
package de.sambalmueslie.loan_calculator.model.founding;

/**
 * The {@link Founding} change listener.
 *
 * @author sambalmueslie 2015
 */
@FunctionalInterface
public interface FoundingChangeListener {

	/**
	 * The {@link Founding} has changed.
	 * 
	 * @param founding
	 *            the founding
	 */
	void foundingChanged(Founding founding);

}
