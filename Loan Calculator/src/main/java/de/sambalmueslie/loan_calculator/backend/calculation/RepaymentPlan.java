package de.sambalmueslie.loan_calculator.backend.calculation;

import de.sambalmueslie.loan_calculator.backend.redemption_plan.BaseRedemptionPlan;
import de.sambalmueslie.loan_calculator.backend.redemption_plan.BaseRedemptionPlanEntry;

/**
 * The repayment plan.
 */
public class RepaymentPlan extends BaseRedemptionPlan {

	/**
	 * Add a entry.
	 *
	 * @param residualDebt
	 *            the residual debt
	 */
	void add(final double residualDebt) {
		add(new BaseRedemptionPlanEntry(residualDebt));
	}

	/**
	 * Add a entry.
	 *
	 * @param residualDebt
	 *            the residual debt
	 * @param interest
	 *            the interest
	 * @param redemption
	 *            the redemption
	 */
	void add(final double residualDebt, final double interest, final double redemption) {
		setTotalInterest(interest + getTotalInterest());
		add(new BaseRedemptionPlanEntry(residualDebt, interest, redemption));
	}

	/**
	 * Set the calculation result.
	 *
	 * @param riskCaptial
	 *            the residual debt
	 * @param totalPayment
	 *            the total payment
	 */
	void setResult(final double riskCaptial, final double totalPayment) {
		setRiskCapital(riskCaptial);
		setTotalPayment(totalPayment);
	}

}
