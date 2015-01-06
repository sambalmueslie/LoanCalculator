/**
 *
 */
package de.sambalmueslie.loan_calculator.view;

import java.util.List;

import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.loan_mgr.LoanManager;
import de.sambalmueslie.loan_calculator.view.loan_mgr.LoanManagerChangeListener;

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
	 * @see de.sambalmueslie.loan_calculator.view.loan_mgr.LoanManagerChangeListener#requestAddAnnuityLoan(de.sambalmueslie.loan_calculator.view.loan_mgr.LoanManager,
	 *      java.lang.String, double, double, double, int, double)
	 */
	@Override
	public void requestAddAnnuityLoan(final LoanManager manager, final String name, final double amount, final double paymentRate,
			final double fixedDebitInterest, final int fixedInterestPeriod, final double estimatedDebitInterest) {
		view.notifyRequestAddAnnuityLoan(manager, name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.loan_mgr.LoanManagerChangeListener#requestCompareLoans(de.sambalmueslie.loan_calculator.view.loan_mgr.LoanManager,
	 *      java.util.List)
	 */
	@Override
	public void requestCompareLoans(final LoanManager manager, final List<Loan> loans) {
		view.requestCompareLoans(manager, loans);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.loan_mgr.LoanManagerChangeListener#requestRemoveLoan(de.sambalmueslie.loan_calculator.view.loan_mgr.LoanManager,
	 *      long)
	 */
	@Override
	public void requestRemoveLoan(final LoanManager manager, final long loanId) {
		view.notifyRequestRemoveLoan(manager, loanId);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.loan_mgr.LoanManagerChangeListener#requestUpdateAnnuityLoan(de.sambalmueslie.loan_calculator.view.loan_mgr.LoanManager,
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
