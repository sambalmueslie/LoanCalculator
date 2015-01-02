/**
 *
 */
package de.sambalmueslie.loan_calculator.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * A annuity loan.
 *
 * @author sambalmueslie 2015
 */
public class AnnuityLoan extends BaseLoan {

	/**
	 * Constructor.
	 *
	 * @param name
	 *            {@link BaseLoan#getName()}
	 * @param amount
	 *            {@link BaseLoan#getAmount()}
	 * @param paymentRate
	 *            {@link #paymentRate}
	 * @param fixedDebitInterest
	 *            {@link #fixedDebitInterest}
	 * @param fixedInterestPeriod
	 *            {@link #fixedInterestPeriod}
	 * @param estimatedDebitInterest
	 *            {@link #estimatedDebitInterest}
	 */
	AnnuityLoan(final String name, final double amount, final double paymentRate, final double fixedDebitInterest, final int fixedInterestPeriod,
			final double estimatedDebitInterest) throws IllegalArgumentException {
		super(name, amount);
		if (paymentRate <= 0 || paymentRate >= 100) throw new IllegalArgumentException("Payment rate '" + paymentRate + "' must 0 < X < 100.");
		this.paymentRate = paymentRate;
		if (fixedDebitInterest < 0 || fixedDebitInterest >= 100) throw new IllegalArgumentException("fixed debit interest '" + fixedDebitInterest
				+ "' must 0 < X < 100.");
		this.fixedDebitInterest = fixedDebitInterest;
		if (fixedInterestPeriod < 0) throw new IllegalArgumentException("fixed debit period '" + fixedDebitInterest + "' must greater equals 0.");
		this.fixedInterestPeriod = fixedInterestPeriod;
		if (estimatedDebitInterest < 0 || estimatedDebitInterest >= 100) throw new IllegalArgumentException("estimated debit interest '"
				+ estimatedDebitInterest + "' must 0 < X < 100.");
		this.estimatedDebitInterest = estimatedDebitInterest;
		update();
	}

	/**
	 * @return the {@link #estimatedDebitInterest}
	 */
	public double getEstimatedDebitInterest() {
		return estimatedDebitInterest;
	}

	/**
	 * @return the {@link #fixedDebitInterest}
	 */
	public double getFixedDebitInterest() {
		return fixedDebitInterest;
	}

	/**
	 * @return the {@link #fixedInterestPeriod}
	 */
	public int getFixedInterestPeriod() {
		return fixedInterestPeriod;
	}

	/**
	 * @return the {@link #paymentRate}
	 */
	public double getPaymentRate() {
		return paymentRate;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.Loan#getRedemptionPlan()
	 */
	@Override
	public List<Double> getRedemptionPlan() {
		return Collections.unmodifiableList(redemptionPlan);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.Loan#getTerm()
	 */
	@Override
	public int getTerm() {
		return term;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.Loan#getTotalInterest()
	 */
	@Override
	public double getTotalInterest() {
		return totalInterest;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.Loan#getTotalPayment()
	 */
	@Override
	public double getTotalPayment() {
		return totalPayment;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("AnnuityLoan [getId()=");
		builder.append(getId());
		builder.append(", getTitle()=");
		builder.append(getName());
		builder.append(", getAmount()=");
		builder.append(getAmount());
		builder.append(", estimatedDebitInterest=");
		builder.append(estimatedDebitInterest);
		builder.append(", fixedDebitInterest=");
		builder.append(fixedDebitInterest);
		builder.append(", fixedInterestPeriod=");
		builder.append(fixedInterestPeriod);
		builder.append(", monthlyPayment=");
		builder.append(redemptionPlan);
		builder.append(", paymentRate=");
		builder.append(paymentRate);
		builder.append(", term=");
		builder.append(term);
		builder.append(", totalInterest=");
		builder.append(totalInterest);
		builder.append(", totalPayment=");
		builder.append(totalPayment);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Update the values.
	 */
	private void update() {
		redemptionPlan = new LinkedList<>();

		// restschuld
		double residualDebt = getAmount();
		// bezahlte zinsen
		double totalInterest = 0;
		// anuität
		final double annuity = getAmount() * (paymentRate + fixedDebitInterest) / 100;
		redemptionPlan.add(residualDebt);

		for (int i = 0; residualDebt > 0; i++) {
			// zins
			final double debitInterest = (i < fixedInterestPeriod ? fixedDebitInterest : estimatedDebitInterest) / 100;
			final double interest = residualDebt * debitInterest;
			totalInterest += interest;
			// tilgung
			final double redemption = annuity - interest;
			if (redemption >= residualDebt) {
				residualDebt = 0;
			} else {
				residualDebt -= redemption;
			}
			redemptionPlan.add(residualDebt);

		}

		totalPayment = totalInterest + getAmount();
	}

	/** the estimated debit interest (geschätzter Sollzins nach Bindungsende). */
	private final double estimatedDebitInterest;
	/** the fixed debit interest (Gebundener Sollzins). */
	private final double fixedDebitInterest;
	/** the fixed debit interest period (Sollzinsbindung). */
	private final int fixedInterestPeriod;
	/** the payment rate (Tilgung in Prozent). */
	private final double paymentRate;
	/** the redemption plan. */
	private List<Double> redemptionPlan;
	/** the term (Laufzeit). */
	private int term;
	/** the total interest (Zins). */
	private double totalInterest;
	/** the total payment (Zins + Finanzmittel). */
	private double totalPayment;

}
