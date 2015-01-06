/**
 *
 */
package de.sambalmueslie.loan_calculator.model.loan;

import java.util.List;

import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;

/**
 * A loan.
 *
 * @author sambalmueslie 2015
 */
public interface Loan extends GenericModelEntry {

	/**
	 * @return the amount of the loan (kredit).
	 */
	double getAmount();

	/**
	 * @return the name.
	 */
	String getName();

	/**
	 * @return the redemption plan.
	 */
	List<RedemptionPlanEntry> getRedemptionPlan();

	/**
	 * @return the term in months.
	 */
	int getTerm();

	/**
	 * @return the total interest to pay.
	 */
	double getTotalInterest();

	/**
	 * @return the total payment (interest and amount).
	 */
	double getTotalPayment();

	/**
	 * Register the {@link LoanChangeListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	void register(LoanChangeListener listener);

	/**
	 * Unregister the {@link LoanChangeListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	void unregister(LoanChangeListener listener);
}