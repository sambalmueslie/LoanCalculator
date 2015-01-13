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
public interface Loan extends GenericModelEntry<Loan> {

	/**
	 * @return the amount of the loan (kredit).
	 */
	double getAmount();

	/**
	 * @return the name.
	 */
	@Override
	String getName();

	/**
	 * @return the redemption plan.
	 */
	List<RedemptionPlanEntry> getRedemptionPlan();

	/**
	 * @return the risk capital of not fixed interest.
	 */
	double getRiskCapital();

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
}