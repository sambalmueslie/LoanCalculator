package de.sambalmueslie.loan_calculator.backend.calculation;

/**
 * The annuity {@link Repayment} (annuitaetentilgung).
 */
public interface AnnuityRepayment extends Repayment {
	/**
	 * @return the debit interest.
	 */
	double getDebitInterest();

	/**
	 * @return the payment rate.
	 */
	double getPaymentRate();

	/**
	 * @return the periods the repayment is calculated.
	 */
	int getPeriods();
}
