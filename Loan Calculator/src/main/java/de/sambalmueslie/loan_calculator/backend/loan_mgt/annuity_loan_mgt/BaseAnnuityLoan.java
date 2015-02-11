/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import de.sambalmueslie.loan_calculator.backend.loan_mgt.BaseLoan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.BaseRedemptionPlanEntry;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.RedemptionPlanEntry;

/**
 * A annuity loan.
 *
 * @author sambalmueslie 2015
 */
public class BaseAnnuityLoan extends BaseLoan implements AnnuityLoan {

	/**
	 * Constructor.
	 *
	 * @param id
	 *            {@link BaseLoan#getId()}
	 * @param name
	 *            {@link BaseLoan#getName()}
	 * @param amount
	 *            {@link BaseLoan#getAmount()}
	 * @param localDate
	 * @param paymentRate
	 *            {@link #paymentRate}
	 * @param fixedDebitInterest
	 *            {@link #fixedDebitInterest}
	 * @param fixedInterestPeriod
	 *            {@link #fixedInterestPeriod}
	 * @param estimatedDebitInterest
	 *            {@link #estimatedDebitInterest}
	 */
	public BaseAnnuityLoan(final long id, final String name, final double amount, final LocalDate localDate, final double paymentRate,
			final double fixedDebitInterest, final int fixedInterestPeriod, final double estimatedDebitInterest) {
		super(id, name, amount, localDate);
		update(name, amount, localDate, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.AnnuityLoan#getEstimatedDebitInterest()
	 */
	@Override
	public double getEstimatedDebitInterest() {
		return estimatedDebitInterest;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.AnnuityLoan#getFixedDebitInterest()
	 */
	@Override
	public double getFixedDebitInterest() {
		return fixedDebitInterest;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.AnnuityLoan#getFixedInterestPeriod()
	 */
	@Override
	public int getFixedInterestPeriod() {
		return fixedInterestPeriod;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.AnnuityLoan#getPaymentRate()
	 */
	@Override
	public double getPaymentRate() {
		return paymentRate;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan#getRedemptionPlan()
	 */
	@Override
	public List<RedemptionPlanEntry> getRedemptionPlan() {
		return Collections.unmodifiableList(redemptionPlan);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.Loan#getRiskCapital()
	 */
	@Override
	public double getRiskCapital() {
		return riskCapital;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.Loan#getTerm()
	 */
	@Override
	public int getTerm() {
		return term;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.Loan#getTotalInterest()
	 */
	@Override
	public double getTotalInterest() {
		return totalInterest;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.Loan#getTotalPayment()
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
	 * Update.
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
	void update(final String name, final double amount, final LocalDate startDate, final double paymentRate, final double fixedDebitInterest,
			final int fixedInterestPeriod, final double estimatedDebitInterest) {
		setName(name);
		setAmount(amount);
		setStartDate(startDate);
		this.paymentRate = paymentRate;
		this.fixedDebitInterest = fixedDebitInterest;
		this.fixedInterestPeriod = fixedInterestPeriod;
		this.estimatedDebitInterest = estimatedDebitInterest;
		calculateValues();
	}

	/**
	 * Calculate the values.
	 */
	private void calculateValues() {
		redemptionPlan = new LinkedList<>();

		double residualDebt = getAmount();
		totalInterest = 0;
		riskCapital = 0;
		final double annuity = getAmount() * (paymentRate + fixedDebitInterest) / 100;
		redemptionPlan.add(new BaseRedemptionPlanEntry(residualDebt));

		for (int i = 0; residualDebt > 0; i++) {
			final boolean noRisk = i < fixedInterestPeriod;
			final double debitInterest = (noRisk ? fixedDebitInterest : estimatedDebitInterest) / 100;
			final double interest = residualDebt * debitInterest;
			totalInterest += interest;

			final double redemption = annuity - interest;
			if (redemption >= residualDebt) {
				riskCapital += (noRisk) ? 0 : residualDebt;
				residualDebt = 0;
			} else {
				residualDebt -= redemption;
				riskCapital += (noRisk) ? 0 : redemption;
			}

			redemptionPlan.add(new BaseRedemptionPlanEntry(residualDebt, interest, redemption));

		}

		term = redemptionPlan.size() - 1;
		totalPayment = totalInterest + getAmount();

		final LocalDate startDate = getStartDate();
		final LocalDate endDate = startDate.plus(getTerm(), ChronoUnit.YEARS);
		setEndDate(endDate);

		notifyChanged();
	}

	/** the estimated debit interest (geschaetzter Sollzins nach Bindungsende). */
	private double estimatedDebitInterest;
	/** the fixed debit interest (Gebundener Sollzins). */
	private double fixedDebitInterest;
	/** the fixed debit interest period (Sollzinsbindung). */
	private int fixedInterestPeriod;
	/** the payment rate (Tilgung in Prozent). */
	private double paymentRate;
	/** the redemption plan. */
	private List<RedemptionPlanEntry> redemptionPlan;
	/** the risk capital.. */
	private double riskCapital;
	/** the term (Laufzeit). */
	private int term;
	/** the total interest (Zins). */
	private double totalInterest;
	/** the total payment (Zins + Finanzmittel). */
	private double totalPayment;

}
