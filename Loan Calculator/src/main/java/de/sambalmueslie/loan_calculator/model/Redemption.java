/**
 *
 */
package de.sambalmueslie.loan_calculator.model;

/**
 * The redemption.
 *
 * @author sambalmueslie 2015
 */
public class Redemption {

	/**
	 * Constructor.
	 *
	 * @param residualDebt
	 *            {@link #residualDebt}
	 */
	Redemption(final double residualDebt) {
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
	Redemption(final double residualDebt, final double interest, final double redemption) {
		this.residualDebt = residualDebt;
		this.interest = interest;
		this.redemption = redemption;
	}

	/**
	 * @return the {@link #interest}
	 */
	public double getInterest() {
		return interest;
	}

	/**
	 * @return the {@link #redemption}
	 */
	public double getRedemption() {
		return redemption;
	}

	/**
	 * @return the {@link #residualDebt}
	 */
	public double getResidualDebt() {
		return residualDebt;
	}

	/** the interest. */
	private final double interest;

	/** the redemption. */
	private final double redemption;

	/** the residual debt. */
	private final double residualDebt;
}
