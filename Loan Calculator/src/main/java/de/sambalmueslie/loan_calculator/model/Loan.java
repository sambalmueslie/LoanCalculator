/**
 *
 */
package de.sambalmueslie.loan_calculator.model;

import java.util.List;

/**
 * A loan.
 *
 * @author sambalmueslie 2015
 */
public interface Loan {
	/**
	 * @return the amount of the loan (kredit).
	 */
	double getAmount();

	/**
	 * @return the monthly payments.
	 */
	List<Double> getMonthlyPayment();

	/**
	 * @return the term in months.
	 */
	int getTerm();

	/**
	 * @return the title.
	 */
	String getTitle();

	/**
	 * @return the total interest to pay.
	 */
	double getTotalInterest();

	/**
	 * @return the total payment (interest and amount).
	 */
	double getTotalPayment();
}