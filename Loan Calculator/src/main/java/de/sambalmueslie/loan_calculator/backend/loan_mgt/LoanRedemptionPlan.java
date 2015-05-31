package de.sambalmueslie.loan_calculator.backend.loan_mgt;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import de.sambalmueslie.loan_calculator.backend.redemption_plan.BaseRedemptionPlan;
import de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlan;
import de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlanEntry;

/**
 * The redemption plan for a {@link Loan}.
 */
public class LoanRedemptionPlan extends BaseRedemptionPlan {

	@Override
	@Deprecated
	public void add(final RedemptionPlanEntry entry) {
		super.add(entry);
	}

	public void addResult(final RedemptionPlan plan, final boolean isRisk, final int startPeriod) {
		final List<RedemptionPlanEntry> entries = plan.getEntries();

		double potentialRisk = 0;
		final int start = (startPeriod > 0) ? 1 : 0;
		final int offset = (startPeriod > 0) ? startPeriod - 1 : 0;
		for (int i = start; i < entries.size(); i++) {
			final RedemptionPlanEntry entry = entries.get(i);
			final int index = i + offset;
			add(entry, index);
			potentialRisk = entry.getResidualDebt();
		}
		if (isRisk) {
			addValue(this::setRiskCapital, this::getRiskCapital, potentialRisk);
		}

		addValue(this::setTotalInterest, this::getTotalInterest, plan.getTotalInterest());
		addValue(this::setTotalPayment, this::getTotalPayment, plan.getTotalPayment());
	}

	/**
	 * @param riskCapital
	 *            the riskCapital to set
	 */
	@Override
	@Deprecated
	public void setRiskCapital(final double riskCapital) {
		super.setRiskCapital(riskCapital);
	}

	/**
	 * @param totalInterest
	 *            the totalInterest to set
	 */
	@Override
	@Deprecated
	public void setTotalInterest(final double totalInterest) {
		super.setTotalInterest(totalInterest);
	}

	/**
	 * @param totalPayment
	 *            the totalPayment to set
	 */
	@Override
	@Deprecated
	public void setTotalPayment(final double totalPayment) {
		super.setTotalPayment(totalPayment);
	}

	private void addValue(final Consumer<Double> setter, final Supplier<Double> getter, final Double value) {
		setter.accept(getter.get() + value);
	}
}
