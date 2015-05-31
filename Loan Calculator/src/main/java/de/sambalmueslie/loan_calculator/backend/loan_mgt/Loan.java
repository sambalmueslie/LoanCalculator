/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.loan_mgt;

import java.time.LocalDate;

import de.sambalmueslie.loan_calculator.backend.common.BusinessObject;
import de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlan;

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
	 * @return the {@link RedemptionPlan}.
	 */
	RedemptionPlan getRedemptionPlan();

	/**
	 * @return the {@link LoanSettings}.
	 */
	LoanSettings getSettings();

	/**
	 * @return the start {@link LocalDate}.
	 */
	LocalDate getStartDate();

}