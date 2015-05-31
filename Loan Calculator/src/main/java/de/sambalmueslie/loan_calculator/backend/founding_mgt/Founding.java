/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.founding_mgt;

import java.time.LocalDate;
import java.util.List;

import de.sambalmueslie.loan_calculator.backend.common.BusinessObject;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;
import de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlanEntry;

/**
 * A founding, group several loans together.
 *
 * @author sambalmueslie 2015
 */
public interface Founding extends BusinessObject {
	/**
	 * @return the amount of the loan.
	 */
	double getAmount();

	/**
	 * @return the bank name.
	 */
	String getBankName();

	/**
	 * @return the end {@link LocalDate}.
	 */
	LocalDate getEndDate();

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
	 * @return the start {@link LocalDate}.
	 */
	LocalDate getStartDate();

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
