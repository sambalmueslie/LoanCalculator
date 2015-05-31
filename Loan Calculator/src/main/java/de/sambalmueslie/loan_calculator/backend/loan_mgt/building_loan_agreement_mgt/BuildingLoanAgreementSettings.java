/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt;

import java.time.LocalDate;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import de.sambalmueslie.loan_calculator.backend.loan_mgt.LoanSettings;

/**
 * The building loan agreement settings.
 *
 * @author sambalmueslie 2015
 */
public class BuildingLoanAgreementSettings extends LoanSettings {

	/**
	 * Constructor.
	 *
	 * @param name
	 *            {@link #name}
	 * @param amount
	 *            {@link #amount}
	 * @param startDate
	 *            {@link #startDate}
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
	public BuildingLoanAgreementSettings(final String name, final double amount, final LocalDate startDate, final double creditInterest,
			final double regularSavingAmount, final double minimumSavings, final int savingDuration, final double savingPhaseInterest,
			final double debitInterest, final double contribution, final double aquisitonFee) {
		super(name, amount, startDate);
		this.creditInterest = creditInterest;
		this.regularSavingAmount = regularSavingAmount;
		this.minimumSavings = minimumSavings;
		this.savingDuration = savingDuration;
		this.savingPhaseInterest = savingPhaseInterest;
		this.debitInterest = debitInterest;
		this.contribution = contribution;
		this.aquisitonFee = aquisitonFee;
	}

	/**
	 * @return the {@link #aquisitonFee}
	 */
	public double getAquisitonFee() {
		return aquisitonFee;
	}

	/**
	 * @return the {@link #contribution}
	 */
	public double getContribution() {
		return contribution;
	}

	/**
	 * @return the {@link #creditInterest}
	 */
	public double getCreditInterest() {
		return creditInterest;
	}

	/**
	 * @return the {@link #debitInterest}
	 */
	public double getDebitInterest() {
		return debitInterest;
	}

	/**
	 * @return the {@link #minimumSavings}
	 */
	public double getMinimumSavings() {
		return minimumSavings;
	}

	/**
	 * @return the {@link #regularSavingAmount}
	 */
	public double getRegularSavingAmount() {
		return regularSavingAmount;
	}

	/**
	 * @return the {@link #savingDuration}
	 */
	public int getSavingDuration() {
		return savingDuration;
	}

	/**
	 * @return the {@link #savingPhaseInterest}
	 */
	public double getSavingPhaseInterest() {
		return savingPhaseInterest;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/** the aquisition fee (abschlussgebuehr in prozent) */
	private final double aquisitonFee;
	/** the contribution. (zins und tilgungsbeitrag mtl in promille) */
	private final double contribution;
	/** the credit interest (guthabenszins) */
	private final double creditInterest;
	/** the debit interest (sollzins ab zuteilung) */
	private final double debitInterest;
	/** the minimum savings (mindestsparguthaben in prozent) */
	private final double minimumSavings;
	/** the regular saving amount. (monatlicher regelsparbetrag in promille) */
	private final double regularSavingAmount;
	/** the saving duration (spardauer). */
	private final int savingDuration;
	/** the interest to pay for getting the money, while beeing in saving phase (zins fuer uebergangsdarlehen). */
	private final double savingPhaseInterest;

}
