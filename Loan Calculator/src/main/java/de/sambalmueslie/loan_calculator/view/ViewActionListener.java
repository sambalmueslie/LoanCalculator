/**
 *
 */
package de.sambalmueslie.loan_calculator.view;

/**
 * The view action listener.
 *
 * @author sambalmueslie 2015
 */
public interface ViewActionListener {
	void requestAddLoan(final String name, final double amount, final double paymentRate, final double fixedDebitInterest, final int fixedInterestPeriod,
			final double estimatedDebitInterest);

	void requestRemoveLoan(long loanId);

	void requestUpdateLoan(long loanId, final String name, final double amount, final double paymentRate, final double fixedDebitInterest,
			final int fixedInterestPeriod, final double estimatedDebitInterest);
}
