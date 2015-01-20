/**
 *
 */
package de.sambalmueslie.loan_calculator.model.founding;

import java.util.List;

import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.model.loan.RedemptionPlanEntry;

/**
 * A founding, group several loans together.
 *
 * @author sambalmueslie 2015
 */
public interface Founding extends GenericModelEntry {
	/**
	 * @return the amount of the loan.
	 */
	double getAmount();

	/**
	 * @return the bank name.
	 */
	String getBankName();

	/**
	 * @return the {@link Loan}s of the founding.
	 */
	List<Loan> getLoans();

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
