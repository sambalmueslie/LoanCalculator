package de.sambalmueslie.loan_calculator.backend.calculation;

import de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlan;

/**
 * The implementation of the {@link AnnuityRepayment}.
 */
public class BaseAnnuityRepayment implements AnnuityRepayment {

	/** the max iterations. */
	private static final int MAX_ITERATIONS = 150;

	/**
	 * Constructor.
	 *
	 * @param amount
	 *            {@link #amount}
	 * @param debitInterest
	 *            {@link #debitInterest}
	 * @param paymentRate
	 *            {@link #paymentRate}
	 * @param periods
	 *            {@link #periods}
	 */
	BaseAnnuityRepayment(final double amount, final double debitInterest, final double paymentRate, final int periods) {
		this.amount = amount;
		this.debitInterest = debitInterest;
		this.paymentRate = paymentRate;
		this.periods = (periods > 0) ? periods : MAX_ITERATIONS;
		calculate();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.calculation.Repayment#getAmount()
	 */
	@Override
	public double getAmount() {
		return amount;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.calculation.AnnuityRepayment#getDebitInterest()
	 */
	@Override
	public double getDebitInterest() {
		return debitInterest;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.calculation.AnnuityRepayment#getPaymentRate()
	 */
	@Override
	public double getPaymentRate() {
		return paymentRate;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.calculation.AnnuityRepayment#getPeriods()
	 */
	@Override
	public int getPeriods() {
		return periods;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.calculation.Repayment#getRedemptionPlan()
	 */
	@Override
	public RedemptionPlan getRedemptionPlan() {
		return repaymentPlan;
	}

	/**
	 * Calculate the repayment.
	 */
	private void calculate() {
		repaymentPlan = new RepaymentPlan();

		double residualDebt = amount;
		double totalInterest = 0;
		final double annuity = amount * (paymentRate + debitInterest) / 100;
		repaymentPlan.add(residualDebt);

		for (int i = 0; residualDebt > 0 && i < MAX_ITERATIONS && i < periods; i++) {
			final double interest = residualDebt * debitInterest;
			totalInterest += interest;

			final double redemption = annuity - interest;
			residualDebt -= (redemption >= residualDebt) ? residualDebt : redemption;

			repaymentPlan.add(residualDebt, interest, redemption);
		}
		final double totalPayment = totalInterest + getAmount();
		repaymentPlan.setResult(totalInterest, totalPayment);

	}

	/** the amount */
	private final double amount;
	/** the debit interest. */
	private final double debitInterest;
	/** the payment rate. */
	private final double paymentRate;
	/** the periods to calculate. */
	private final int periods;
	/** the {@link RepaymentPlan}. */
	private RepaymentPlan repaymentPlan;
}