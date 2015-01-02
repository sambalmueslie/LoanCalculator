/**
 *
 */
package de.sambalmueslie.loan_calculator.view;

import de.sambalmueslie.loan_calculator.view.component.LoanManager;
import de.sambalmueslie.loan_calculator.view.component.LoanManagerChangeListener;

/**
 * The handler for the {@link LoanManagerChangeListener}.
 *
 * @author sambalmueslie 2015
 */
public class LoanManagerChangeHandler implements LoanManagerChangeListener {

	/**
	 * Constructor.
	 *
	 * @param view
	 *            {@link #view}
	 */
	LoanManagerChangeHandler(final View view) {
		this.view = view;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.component.LoanManagerChangeListener#requestAddAnnuityLoan(de.sambalmueslie.loan_calculator.view.component.LoanManager,
	 *      java.lang.String, double, double, double, int, double)
	 */
	@Override
	public void requestAddAnnuityLoan(final LoanManager manager, final String name, final double amount, final double paymentRate,
			final double fixedDebitInterest, final int fixedInterestPeriod, final double estimatedDebitInterest) {
		view.notifyRequestAddAnnuityLoan(manager, name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.component.LoanManagerChangeListener#requestRemoveLoan(de.sambalmueslie.loan_calculator.view.component.LoanManager,
	 *      long)
	 */
	@Override
	public void requestRemoveLoan(final LoanManager manager, final long loanId) {
		view.notifyRequestRemoveLoan(manager, loanId);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.component.LoanManagerChangeListener#requestUpdateAnnuityLoan(de.sambalmueslie.loan_calculator.view.component.LoanManager,
	 *      long, java.lang.String, double, double, double, int, double)
	 */
	@Override
	public void requestUpdateAnnuityLoan(final LoanManager manager, final long loanId, final String name, final double amount, final double paymentRate,
			final double fixedDebitInterest, final int fixedInterestPeriod, final double estimatedDebitInterest) {
		view.notifyRequestUpdateAnnuityLoan(manager, loanId, name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest);
	}

	/** the {@link View}. */
	private final View view;
}
