/**
 *
 */
package de.sambalmueslie.loan_calculator.model;

/**
 * The change listener for the model.
 * 
 * @author sambalmueslie 2015
 */
public interface ModelChangeListener {
	/**
	 * A {@link Loan} was added.
	 *
	 * @param loan
	 *            the added loan.
	 */
	void loanAdded(Loan loan);

	/**
	 * A {@link Loan} was removed.
	 *
	 * @param loan
	 *            the removed loan.
	 */
	void loanRemoved(Loan loan);
}
