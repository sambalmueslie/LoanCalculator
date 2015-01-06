/**
 *
 */
package de.sambalmueslie.loan_calculator.model.loan;

/**
 * A annuity {@link Loan}.
 *
 * @author sambalmueslie 2015
 */
public interface AnnuityLoan extends Loan {

	/**
	 * @return the estimated debit interest.
	 */
	double getEstimatedDebitInterest();

	/**
	 * @return the fixed debit interest.
	 */
	double getFixedDebitInterest();

	/**
	 * @return the fixed interest period.
	 */
	int getFixedInterestPeriod();

	/**
	 * @return the payment rate.
	 */
	double getPaymentRate();

}
