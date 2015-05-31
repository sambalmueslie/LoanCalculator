/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import de.sambalmueslie.loan_calculator.backend.calculation.AnnuityRepayment;
import de.sambalmueslie.loan_calculator.backend.calculation.RepaymentFactory;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.BaseLoan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.LoanRedemptionPlan;
import de.sambalmueslie.loan_calculator.backend.redemption_plan.BaseRedemptionPlanEntry;
import de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlan;

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
	 * @param settings
	 *            the {@link AnnuityLoanSettings}
	 */
	public BaseAnnuityLoan(final long id, final AnnuityLoanSettings settings) {
		super(id, settings.getName());
		update(settings);
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
	public RedemptionPlan getRedemptionPlan() {
		return redemptionPlan;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoan#getUnscheduledRepayment()
	 */
	@Override
	public double getUnscheduledRepayment() {
		return unscheduledRepayment;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * Update.
	 *
	 * @param settings
	 *            the {@link AnnuityLoanSettings}
	 */
	void update(final AnnuityLoanSettings settings) {
		setName(settings.getName());
		setSettings(settings);
		paymentRate = settings.getPaymentRate();
		fixedDebitInterest = settings.getFixedDebitInterest();
		fixedInterestPeriod = settings.getFixedInterestPeriod();
		estimatedDebitInterest = settings.getEstimatedDebitInterest();
		unscheduledRepayment = settings.getUnscheduledRepayment();
		calculateRepayment();
	}

	/**
	 * Calcualte the repayment.
	 */
	private void calculateRepayment() {
		// first the fixed annuity plan
		final AnnuityRepayment fixed = RepaymentFactory.createAnnuityRepayment(getAmount(), fixedDebitInterest, paymentRate, fixedInterestPeriod);
		final RedemptionPlan fixedPlan = fixed.getRedemptionPlan();
		// now the risk annuity plan
		final double remainingAmount = fixedPlan.getRiskCapital();
		final AnnuityRepayment risk = RepaymentFactory.createAnnuityRepayment(remainingAmount, estimatedDebitInterest, paymentRate);
		final RedemptionPlan riskPlan = risk.getRedemptionPlan();

		redemptionPlan = new LoanRedemptionPlan();
		redemptionPlan.addResult(fixedPlan, false, 0);
		redemptionPlan.addResult(riskPlan, true, fixedInterestPeriod + 1);

		final LocalDate startDate = getStartDate();
		final LocalDate endDate = startDate.plus(redemptionPlan.getTerm(), ChronoUnit.YEARS);
		setEndDate(endDate);

		notifyChanged();
	}

	/**
	 * Calculate the values.
	 */
	private void calculateValues() {
		redemptionPlan = new LoanRedemptionPlan();

		double residualDebt = getAmount();
		double totalInterest = 0;
		double riskCapital = 0;
		final double repayment = getAmount() * unscheduledRepayment / 100;
		double annuity = getAmount() * (paymentRate + fixedDebitInterest) / 100;
		redemptionPlan.add(new BaseRedemptionPlanEntry(residualDebt));

		for (int i = 0; residualDebt > 0; i++) {
			if (i == fixedInterestPeriod) {
				annuity = residualDebt * (paymentRate + estimatedDebitInterest) / 100;
			}
			final boolean noRisk = i < fixedInterestPeriod;
			final double debitInterest = (noRisk ? fixedDebitInterest : estimatedDebitInterest) / 100;
			final double interest = residualDebt * debitInterest;
			totalInterest += interest;

			final double redemption = annuity - interest + repayment;
			if (redemption >= residualDebt) {
				riskCapital += (noRisk) ? 0 : residualDebt;
				residualDebt = 0;
			} else {
				residualDebt -= redemption;
				riskCapital += (noRisk) ? 0 : redemption;
			}

			if (redemption <= 0) {
				throw new IllegalStateException("Redemption is getting below zero for " + i);
			}

			redemptionPlan.add(new BaseRedemptionPlanEntry(residualDebt, interest, redemption));

		}

		final double totalPayment = totalInterest + getAmount();

		redemptionPlan.setTotalInterest(totalInterest);
		redemptionPlan.setRiskCapital(riskCapital);
		redemptionPlan.setTotalPayment(totalPayment);

		final LocalDate startDate = getStartDate();
		final LocalDate endDate = startDate.plus(redemptionPlan.getTerm(), ChronoUnit.YEARS);
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
	/** the {@link LoanRedemptionPlan}. */
	private LoanRedemptionPlan redemptionPlan;
	/** the unscheduled repayment (sondertilgung jaehrlich). */
	private double unscheduledRepayment;

}
