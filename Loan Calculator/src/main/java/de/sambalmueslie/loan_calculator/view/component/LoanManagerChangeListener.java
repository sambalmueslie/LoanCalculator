/**
 *
 */
package de.sambalmueslie.loan_calculator.view.component;

/**
 * The listener for the {@link LoanManager}.
 *
 * @author sambalmueslie 2015
 */
public interface LoanManagerChangeListener {

	void requestAddAnnuityLoan(LoanManager manager, final String name, final double amount, final double paymentRate, final double fixedDebitInterest,
			final int fixedInterestPeriod, final double estimatedDebitInterest);

	void requestRemoveLoan(LoanManager manager, long loanId);

	void requestUpdateAnnuityLoan(LoanManager manager, long loanId, final String name, final double amount, final double paymentRate,
			final double fixedDebitInterest, final int fixedInterestPeriod, final double estimatedDebitInterest);

}
