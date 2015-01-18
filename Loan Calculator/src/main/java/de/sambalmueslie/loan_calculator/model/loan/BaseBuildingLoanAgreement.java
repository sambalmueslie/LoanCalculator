/**
 *
 */
package de.sambalmueslie.loan_calculator.model.loan;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
	public BaseBuildingLoanAgreement(final long id, final String name, final double amount, final double creditInterest, final double regularSavingAmount,
			final double minimumSavings, final int savingDuration, final double debitInterest, final double contribution, final double aquisitonFee) {
		super(id, name, amount);
		update(name, amount, creditInterest, regularSavingAmount, minimumSavings, savingDuration, debitInterest, contribution, aquisitonFee);
	}

	/**
	 * Constructor.
	 *
	 * @param name
	 *            {@link BaseLoan#getName()}
	 * @param amount
	 *            {@link BaseLoan#getAmount()}
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
	public BaseBuildingLoanAgreement(final String name, final double amount, final double creditInterest, final double regularSavingAmount,
			final double minimumSavings, final int savingDuration, final double debitInterest, final double contribution, final double aquisitonFee) {
		super(name, amount);
		update(name, amount, creditInterest, regularSavingAmount, minimumSavings, savingDuration, debitInterest, contribution, aquisitonFee);
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
	 * @see de.sambalmueslie.loan_calculator.model.loan.Loan#getRedemptionPlan()
	 */
	@Override
	public List<RedemptionPlanEntry> getRedemptionPlan() {
		return Collections.unmodifiableList(redemptionPlan);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.BuildingLoanAgreement#getRegularSavingAmount()
	 */
	@Override
	public double getRegularSavingAmount() {
		return regularSavingAmount;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.Loan#getRiskCapital()
	 */
	@Override
	public double getRiskCapital() {
		return 0;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.BuildingLoanAgreement#getSavingDuration()
	 */
	@Override
	public int getSavingDuration() {
		return savingDuration;
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
	 * Update.
	 *
	 * @param name
	 *            {@link BaseLoan#getName()}
	 * @param amount
	 *            {@link BaseLoan#getAmount()}
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
	public void update(final String name, final double amount, final double creditInterest, final double regularSavingAmount, final double minimumSavings,
			final int savingDuration, final double debitInterest, final double contribution, final double aquisitonFee) {
		super.update(name, amount);
		if (creditInterest <= 0 || creditInterest >= 100) throw new IllegalArgumentException("Credit interest '" + creditInterest + "' must 0 < X < 100.");
		this.creditInterest = creditInterest;
		if (regularSavingAmount <= 0 || regularSavingAmount >= 100) throw new IllegalArgumentException("Regular saving amount '" + regularSavingAmount
				+ "' must 0 < X < 100.");
		this.regularSavingAmount = regularSavingAmount;
		if (minimumSavings <= 0 || minimumSavings >= 100) throw new IllegalArgumentException("Minimum savings '" + minimumSavings + "' must 0 < X < 100.");
		this.minimumSavings = minimumSavings;
		if (savingDuration < 0) throw new IllegalArgumentException("Saving duration '" + savingDuration + "' must >= 0.");
		this.savingDuration = savingDuration;
		if (debitInterest <= 0 || debitInterest >= 100) throw new IllegalArgumentException("Debit interest '" + debitInterest + "' must 0 < X < 100.");
		this.debitInterest = debitInterest;
		if (contribution <= 0 || contribution >= 100) throw new IllegalArgumentException("Contribution '" + contribution + "' must 0 < X < 100.");
		this.contribution = contribution;
		if (aquisitonFee <= 0 || aquisitonFee >= 100) throw new IllegalArgumentException("Aquisition fee '" + aquisitonFee + "' must 0 < X < 100.");
		this.aquisitonFee = aquisitonFee;
		calculateValues();
	}

	/**
	 * Calculate the values.
	 */
	private void calculateValues() {
		redemptionPlan = new LinkedList<>();
		totalInterest = 0;

		// sparphase
		double savedAmount = 0;
		final double regularSaving = getAmount() * regularSavingAmount / 1000 * 12;
		final double minimumSavedAmount = getAmount() * minimumSavings / 100;
		redemptionPlan.add(new BaseRedemptionPlanEntry(0, 0, -regularSaving));
		for (int i = 0; i < savingDuration || savedAmount < minimumSavedAmount; i++) {
			final double interest = (savedAmount + regularSaving) * creditInterest / 100;
			totalInterest -= interest;
			savedAmount += regularSaving + interest;
			redemptionPlan.add(new BaseRedemptionPlanEntry(-savedAmount, -interest, -regularSaving));
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
		}
		term = redemptionPlan.size() - 1;
		totalPayment = totalInterest + getAmount();
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
	/** the redemption plan. */
	private List<RedemptionPlanEntry> redemptionPlan;
	/** the regular saving amount. (monatlicher regelsparbetrag in promille) */
	private double regularSavingAmount;
	/** the saving duration (spardauer). */
	private int savingDuration;
	/** the term (Laufzeit). */
	private int term;
	/** the total interest (Zins). */
	private double totalInterest;
	/** the total payment (Zins + Finanzmittel). */
	private double totalPayment;

}
