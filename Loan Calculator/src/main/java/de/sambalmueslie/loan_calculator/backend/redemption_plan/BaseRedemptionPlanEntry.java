/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.redemption_plan;

/**
 * The redemption.
 *
 * @author sambalmueslie 2015
 */
public class BaseRedemptionPlanEntry implements RedemptionPlanEntry {

	/**
	 * Constructor.
	 *
	 * @param residualDebt
	 *            {@link #residualDebt}
	 */
	public BaseRedemptionPlanEntry(final double residualDebt) {
		this(residualDebt, 0, 0);
	}

	/**
	 * Constructor.
	 *
	 * @param residualDebt
	 *            {@link #residualDebt}
	 * @param interest
	 *            {@link #interest}
	 * @param redemption
	 *            {@link #redemption}
	 */
	public BaseRedemptionPlanEntry(final double residualDebt, final double interest, final double redemption) {
		this.residualDebt = residualDebt;
		this.interest = interest;
		this.redemption = redemption;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlanEntry#getInterest()
	 */
	@Override
	public double getInterest() {
		return interest;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlanEntry#getRedemption()
	 */
	@Override
	public double getRedemption() {
		return redemption;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlanEntry#getResidualDebt()
	 */
	@Override
	public double getResidualDebt() {
		return residualDebt;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("BaseRedemptionPlanEntry [interest=");
		builder.append(interest);
		builder.append(", redemption=");
		builder.append(redemption);
		builder.append(", residualDebt=");
		builder.append(residualDebt);
		builder.append("]");
		return builder.toString();
	}

	/** the interest. */
	private final double interest;
	/** the redemption. */
	private final double redemption;
	/** the residual debt. */
	private final double residualDebt;
}
