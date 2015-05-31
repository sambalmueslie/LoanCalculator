package de.sambalmueslie.loan_calculator.backend.calculation;

import de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlan;

/**
 * The repayment.
 */
public interface Repayment {

	/**
	 * @return the amount.
	 */
	double getAmount();

	/**
	 * @return the {@link RedemptionPlan}.
	 */
	RedemptionPlan getRedemptionPlan();

}
