/**
 *
 */
package de.sambalmueslie.loan_calculator.view.component;

import de.sambalmueslie.loan_calculator.model.Loan;

/**
 * The listener for the {@link LoanManager}.
 *
 * @author sambalmueslie 2015
 */
public interface LoanManagerChangeListener {

	/**
	 * Request to add a new annuity loan.
	 *
	 * @param manager
	 *            the {@link LoanManager}
	 * @param name
	 *            a unique name
	 * @param amount
	 *            the total amount (Finanzmittel)
	 * @param paymentRate
	 *            the payment rate (Tilgung in Prozent)
	 * @param fixedDebitInterest
	 *            the fixed debit interest (Gebundener Sollzins)
	 * @param fixedInterestPeriod
	 *            the fixed debit interest period (Sollzinsbindung)
	 * @param estimatedDebitInterest
	 *            the estimated debit interest (geschätzter Sollzins nach Bindungsende)
	 */
	void requestAddAnnuityLoan(LoanManager manager, final String name, final double amount, final double paymentRate, final double fixedDebitInterest,
			final int fixedInterestPeriod, final double estimatedDebitInterest);

	/**
	 * Request to remove a existing {@link Loan}.
	 *
	 * @param manager
	 *            the {@link LoanManager}
	 * @param loanId
	 *            the loan id
	 */
	void requestRemoveLoan(LoanManager manager, long loanId);

	/**
	 * Request to update a existing annuity loan.
	 *
	 * @param manager
	 *            the {@link LoanManager}
	 * @param loanId
	 *            the loan id
	 * @param name
	 *            a unique name
	 * @param amount
	 *            the total amount (Finanzmittel)
	 * @param paymentRate
	 *            the payment rate (Tilgung in Prozent)
	 * @param fixedDebitInterest
	 *            the fixed debit interest (Gebundener Sollzins)
	 * @param fixedInterestPeriod
	 *            the fixed debit interest period (Sollzinsbindung)
	 * @param estimatedDebitInterest
	 *            the estimated debit interest (geschätzter Sollzins nach Bindungsende)
	 */
	void requestUpdateAnnuityLoan(LoanManager manager, long loanId, final String name, final double amount, final double paymentRate,
			final double fixedDebitInterest, final int fixedInterestPeriod, final double estimatedDebitInterest);

}
