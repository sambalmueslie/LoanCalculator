/**
 *
 */
package de.sambalmueslie.loan_calculator.model.loan;

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

	@Override
	public double getInterest() {
		return interest;
	}

	@Override
	public double getRedemption() {
		return redemption;
	}

	@Override
	public double getResidualDebt() {
		return residualDebt;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Redemption [interest=" + interest + ", redemption=" + redemption + ", residualDebt=" + residualDebt + "]";
	}

	/** the interest. */
	private final double interest;
	/** the redemption. */
	private final double redemption;
	/** the residual debt. */
	private final double residualDebt;
}
