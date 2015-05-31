package de.sambalmueslie.loan_calculator.backend.redemption_plan;

import java.util.List;

/**
 * The redemption plan.
 *
 * @author Sambalmueslie
 */
public interface RedemptionPlan {

	/**
	 * @return the redemption plan.
	 */
	List<RedemptionPlanEntry> getEntries();

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
