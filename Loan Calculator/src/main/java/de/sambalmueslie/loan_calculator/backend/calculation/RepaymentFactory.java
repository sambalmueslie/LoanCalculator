package de.sambalmueslie.loan_calculator.backend.calculation;

/**
 * The repayment factory.
 */
public final class RepaymentFactory {

	/**
	 * Create a new {@link AnnuityRepayment}.
	 *
	 * @param amount
	 *            the amount
	 * @param debitInterest
	 *            the debit interest
	 * @param paymentRate
	 *            the payment rate
	 * @return the {@link AnnuityRepayment}
	 */
	public static AnnuityRepayment createAnnuityRepayment(final double amount, final double debitInterest, final double paymentRate) {
		return new BaseAnnuityRepayment(amount, debitInterest, paymentRate, 0);
	}

	/**
	 * Create a new {@link AnnuityRepayment}.
	 *
	 * @param amount
	 *            the amount
	 * @param debitInterest
	 *            the debit interest
	 * @param paymentRate
	 *            the payment rate
	 * @param periods
	 *            the periods to calculate
	 * @return the {@link AnnuityRepayment}
	 */
	public static AnnuityRepayment createAnnuityRepayment(final double amount, final double debitInterest, final double paymentRate, final int periods) {
		return new BaseAnnuityRepayment(amount, debitInterest, paymentRate, periods);
	}

	/**
	 * Constructor.
	 */
	private RepaymentFactory() {
		// intentionally left empty
	}
}
