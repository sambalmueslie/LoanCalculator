/**
 *
 */
package de.sambalmueslie.loan_calculator.model;

import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.loan.Loan;

/**
 * The change listener for the model.
 *
 * @author sambalmueslie 2015
 */
public interface ModelChangeListener {
	/**
	 * A {@link Founding} was added.
	 * 
	 * @param founding
	 *            the founding
	 */
	void foundingAdded(Founding founding);

	/**
	 * A {@link Founding} was added.
	 * 
	 * @param founding
	 *            the founding
	 */
	void foundingRemoved(Founding founding);

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
