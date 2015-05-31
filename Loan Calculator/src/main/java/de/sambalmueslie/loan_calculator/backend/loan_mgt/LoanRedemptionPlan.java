package de.sambalmueslie.loan_calculator.backend.loan_mgt;

import de.sambalmueslie.loan_calculator.backend.redemption_plan.BaseRedemptionPlan;
import de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlanEntry;

/**
 * The redemption plan for a {@link Loan}.
 */
public class LoanRedemptionPlan extends BaseRedemptionPlan {

	public void add(final RedemptionPlanEntry entry) {
		super.addRedemptionPlanEntry(entry);
	}

	/**
	 * @param riskCapital
	 *            the riskCapital to set
	 */
	@Override
	public void setRiskCapital(final double riskCapital) {
		super.setRiskCapital(riskCapital);
	}

	/**
	 * @param totalInterest
	 *            the totalInterest to set
	 */
	@Override
	public void setTotalInterest(final double totalInterest) {
		super.setTotalInterest(totalInterest);
	}

	/**
	 * @param totalPayment
	 *            the totalPayment to set
	 */
	@Override
	public void setTotalPayment(final double totalPayment) {
		super.setTotalPayment(totalPayment);
	}
}
