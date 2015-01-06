/**
 *
 */
package de.sambalmueslie.loan_calculator.model.loan;

/**
 * The {@link Loan} change listener.
 *
 * @author sambalmueslie 2015
 */
@FunctionalInterface
public interface LoanChangeListener {
	/**
	 * The {@link Loan} has changed.
	 *
	 * @param loan
	 *            the affected lan.
	 */
	void loanChanged(Loan loan);
}
