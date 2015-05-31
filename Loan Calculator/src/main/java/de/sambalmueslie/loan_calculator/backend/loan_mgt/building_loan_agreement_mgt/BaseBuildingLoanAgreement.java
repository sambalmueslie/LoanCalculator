/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import de.sambalmueslie.loan_calculator.backend.loan_mgt.BaseLoan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.LoanRedemptionPlan;
import de.sambalmueslie.loan_calculator.backend.redemption_plan.BaseRedemptionPlanEntry;
import de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlan;

/**
 * @author sambalmueslie 2015
 */
public class BaseBuildingLoanAgreement extends BaseLoan implements BuildingLoanAgreement {

	/**
	 * Constructor.
	 *
	 * @param id
	 *            the id
	 * @param name
	 *            {@link BaseLoan#getName()}
	 * @param amount
	 *            {@link BaseLoan#getAmount()}
	 * @param startDate
	 *            {@link BaseLoan#getStartDate()}
	 * @param creditInterest
	 *            {@link #creditInterest}
	 * @param regularSavingAmount
	 *            {@link #regularSavingAmount}
	 * @param minimumSavings
	 *            {@link #minimumSavings}
	 * @param savingDuration
	 *            {@link #savingDuration}
	 * @param savingPhaseInterest
	 *            {@link #savingPhaseInterest}
	 * @param debitInterest
	 *            {@link #debitInterest}
	 * @param contribution
	 *            {@link #contribution}
	 * @param aquisitonFee
	 *            {@link #aquisitonFee}
	 */
	public BaseBuildingLoanAgreement(final long id, final BuildingLoanAgreementSettings settings) {
		super(id, settings.getName());
		update(settings);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.BuildingLoanAgreement#getAquisitonFee()
	 */
	@Override
	public double getAquisitonFee() {
		return aquisitonFee;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.BuildingLoanAgreement#getContribution()
	 */
	@Override
	public double getContribution() {
		return contribution;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.BuildingLoanAgreement#getCreditInterest()
	 */
	@Override
	public double getCreditInterest() {
		return creditInterest;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.BuildingLoanAgreement#getDebitInterest()
	 */
	@Override
	public double getDebitInterest() {
		return debitInterest;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.BuildingLoanAgreement#getMinimumSavings()
	 */
	@Override
	public double getMinimumSavings() {
		return minimumSavings;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan#getRedemptionPlan()
	 */
	@Override
	public RedemptionPlan getRedemptionPlan() {
		return redemptionPlan;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.BuildingLoanAgreement#getRegularSavingAmount()
	 */
	@Override
	public double getRegularSavingAmount() {
		return regularSavingAmount;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.BuildingLoanAgreement#getSavingDuration()
	 */
	@Override
	public int getSavingDuration() {
		return savingDuration;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.BuildingLoanAgreement#getSavingPhaseInterest()
	 */
	@Override
	public double getSavingPhaseInterest() {
		return savingPhaseInterest;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreement#getSavingRedemptionPlan()
	 */
	@Override
	public RedemptionPlan getSavingRedemptionPlan() {
		return savingPhasePlan;
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
	 * @param name
	 *            {@link BaseLoan#getName()}
	 * @param amount
	 *            {@link BaseLoan#getAmount()}
	 * @param startDate
	 * @param creditInterest
	 *            {@link #creditInterest}
	 * @param regularSavingAmount
	 *            {@link #regularSavingAmount}
	 * @param minimumSavings
	 *            {@link #minimumSavings}
	 * @param savingDuration
	 *            {@link #savingDuration}
	 * @param debitInterest
	 *            {@link #debitInterest}
	 * @param contribution
	 *            {@link #contribution}
	 * @param aquisitonFee
	 *            {@link #aquisitonFee}
	 */
	void update(final BuildingLoanAgreementSettings settings) {
		setName(settings.getName());
		setSettings(settings);
		this.creditInterest = settings.getCreditInterest();
		this.regularSavingAmount = settings.getRegularSavingAmount();
		this.minimumSavings = settings.getMinimumSavings();
		this.savingDuration = settings.getSavingDuration();
		this.savingPhaseInterest = settings.getSavingPhaseInterest();
		this.debitInterest = settings.getDebitInterest();
		this.contribution = settings.getContribution();
		this.aquisitonFee = settings.getAquisitonFee();
		calculateValues();
	}

	/**
	 * Calculate the values.
	 */
	private void calculateValues() {
		redemptionPlan = new LoanRedemptionPlan();
		savingPhasePlan = new LoanRedemptionPlan();
		double totalInterest = 0;

		// sparphase (saving phase)
		double savedAmount = 0;
		final double regularSaving = getAmount() * regularSavingAmount / 1000 * 12;
		final double minimumSavedAmount = getAmount() * minimumSavings / 100;
		final double transitionalLoanInterest = getAmount() * savingPhaseInterest / 100;
		redemptionPlan.add(new BaseRedemptionPlanEntry(getAmount()));
		savingPhasePlan.add(new BaseRedemptionPlanEntry(savedAmount));
		for (int i = 0; i < savingDuration || savedAmount < minimumSavedAmount; i++) {
			final double currentCreditInterest = (savedAmount + regularSaving) * creditInterest / 100;
			final double interest = transitionalLoanInterest - currentCreditInterest;
			totalInterest += interest;
			savedAmount += regularSaving + currentCreditInterest;
			redemptionPlan.add(new BaseRedemptionPlanEntry(getAmount(), interest, regularSaving));
			savingPhasePlan.add(new BaseRedemptionPlanEntry(savedAmount));
		}
		// tilgungsphase
		final double interestAndPrincipalContribution = getAmount() * contribution / 1000 * 12;
		double residualDebt = getAmount() - savedAmount;
		while (residualDebt > 0) {
			final double interest = residualDebt * debitInterest / 100;
			final double principal = interestAndPrincipalContribution - interest;
			totalInterest += interest;
			if (principal >= residualDebt) {
				residualDebt = 0;
			} else {
				residualDebt -= principal;
			}
			redemptionPlan.add(new BaseRedemptionPlanEntry(residualDebt, interest, principal));
			savingPhasePlan.add(new BaseRedemptionPlanEntry(0));
		}

		totalInterest += getAmount() * aquisitonFee / 100;
		final double totalPayment = totalInterest + getAmount();
		redemptionPlan.setTotalPayment(totalPayment);
		redemptionPlan.setTotalInterest(totalInterest);

		final LocalDate startDate = getStartDate();
		final LocalDate endDate = startDate.plus(redemptionPlan.getTerm(), ChronoUnit.YEARS);
		setEndDate(endDate);

		notifyChanged();
	}

	/** the aquisition fee (abschlussgebuehr in prozent) */
	private double aquisitonFee;
	/** the contribution. (zins und tilgungsbeitrag mtl in promille) */
	private double contribution;
	/** the credit interest (guthabenszins) */
	private double creditInterest;
	/** the debit interest (sollzins ab zuteilung) */
	private double debitInterest;
	/** the minimum savings (mindestsparguthaben in prozent) */
	private double minimumSavings;
	/** the {@link LoanRedemptionPlan}. */
	private LoanRedemptionPlan redemptionPlan;
	/** the regular saving amount. (monatlicher regelsparbetrag in promille) */
	private double regularSavingAmount;
	/** the saving duration (spardauer). */
	private int savingDuration;
	/** the interest to pay for getting the money, while beeing in saving phase (zins für uebergangsdarlehen). */
	private double savingPhaseInterest;
	/** the saving {@link LoanRedemptionPlan}. */
	private LoanRedemptionPlan savingPhasePlan;

}
