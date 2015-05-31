package de.sambalmueslie.loan_calculator.backend.redemption_plan;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * The base {@link RedemptionPlan}.
 *
 * @author Sambalmueslie
 */
public abstract class BaseRedemptionPlan implements RedemptionPlan {

	/**
	 * Constructor.
	 */
	protected BaseRedemptionPlan() {
		// empty by contract
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlan#getEntries()
	 */
	@Override
	public final List<RedemptionPlanEntry> getEntries() {
		return Collections.unmodifiableList(entries);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlan#getRiskCapital()
	 */
	@Override
	public final double getRiskCapital() {
		return riskCapital;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlan#getTerm()
	 */
	@Override
	public final int getTerm() {
		return entries.size() - 1;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlan#getTotalInterest()
	 */
	@Override
	public final double getTotalInterest() {
		return totalInterest;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlan#getTotalPayment()
	 */
	@Override
	public final double getTotalPayment() {
		return totalPayment;
	}

	/**
	 * Add a {@link RedemptionPlanEntry}.
	 *
	 * @param entry
	 *            the entry
	 */
	protected void add(final RedemptionPlanEntry entry) {
		entries.add(entry);
	}

	/**
	 * Add a {@link RedemptionPlanEntry} at a specified position.
	 *
	 * @param entry
	 *            the entry
	 * @param index
	 *            the index
	 */
	protected void add(final RedemptionPlanEntry entry, final int index) {
		if (index == entries.size()) {
			entries.add(entry);
		} else if (index > entries.size()) {
			// TODO fill the gap
			throw new IllegalArgumentException("cannot handle index gaps.");
		} else {
			final RedemptionPlanEntry current = entries.get(index);
			final RedemptionPlanEntry result =
					new BaseRedemptionPlanEntry(current.getResidualDebt() + entry.getResidualDebt(), current.getInterest() + entry.getInterest(),
							current.getRedemption() + entry.getRedemption());
			entries.remove(index);
			entries.add(index, result);
		}
	}

	/**
	 * @param riskCapital
	 *            the riskCapital to set
	 */
	protected void setRiskCapital(final double riskCapital) {
		this.riskCapital = riskCapital;
	}

	/**
	 * @param totalInterest
	 *            the totalInterest to set
	 */
	protected void setTotalInterest(final double totalInterest) {
		this.totalInterest = totalInterest;
	}

	/**
	 * @param totalPayment
	 *            the totalPayment to set
	 */
	protected void setTotalPayment(final double totalPayment) {
		this.totalPayment = totalPayment;
	}

	/** the redemption plan entries. */
	private final List<RedemptionPlanEntry> entries = new LinkedList<>();
	/** the risk capital.. */
	private double riskCapital;
	/** the total interest (Zins). */
	private double totalInterest;
	/** the total payment (Zins + Finanzmittel). */
	private double totalPayment;

}
