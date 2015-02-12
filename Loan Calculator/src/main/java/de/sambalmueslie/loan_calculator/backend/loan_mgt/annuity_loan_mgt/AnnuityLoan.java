/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt;

import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;

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

	/**
	 * @return the unscheduled repayment.
	 */
	double getUnscheduledRepayment();

}
