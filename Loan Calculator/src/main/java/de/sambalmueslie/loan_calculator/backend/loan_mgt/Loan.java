/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.loan_mgt;

import java.time.LocalDate;
import java.util.List;

import de.sambalmueslie.loan_calculator.backend.common.BusinessObject;

/**
 * A loan.
 *
 * @author sambalmueslie 2015
 */
public interface Loan extends BusinessObject {

	/**
	 * @return the amount of the loan (kredit).
	 */
	double getAmount();

	/**
	 * @return the end {@link LocalDate}.
	 */
	LocalDate getEndDate();

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