/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.file_mgt.xml_mgt.data;

/**
 * The xml building loan agreement.
 *
 * @author sambalmueslie 2015
 */
public class XMLBuildingLoanAgreement {

	/**
	 * @return the {@link #amount}
	 */
	public double getAmount() {
		return amount;
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
	 * @return the {@link #id}
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the {@link #minimumSavings}
	 */
	public double getMinimumSavings() {
		return minimumSavings;
	}

	/**
	 * @return the {@link #name}
	 */
	public String getName() {
		return name;
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
	 * @return the {@link #startDate}
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(final double amount) {
		this.amount = amount;
	}

	/**
	 * @param aquisitonFee
	 *            the aquisitonFee to set
	 */
	public void setAquisitonFee(final double aquisitonFee) {
		this.aquisitonFee = aquisitonFee;
	}

	/**
	 * @param contribution
	 *            the contribution to set
	 */
	public void setContribution(final double contribution) {
		this.contribution = contribution;
	}

	/**
	 * @param creditInterest
	 *            the creditInterest to set
	 */
	public void setCreditInterest(final double creditInterest) {
		this.creditInterest = creditInterest;
	}

	/**
	 * @param debitInterest
	 *            the debitInterest to set
	 */
	public void setDebitInterest(final double debitInterest) {
		this.debitInterest = debitInterest;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final long id) {
		this.id = id;
	}

	/**
	 * @param minimumSavings
	 *            the minimumSavings to set
	 */
	public void setMinimumSavings(final double minimumSavings) {
		this.minimumSavings = minimumSavings;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @param regularSavingAmount
	 *            the regularSavingAmount to set
	 */
	public void setRegularSavingAmount(final double regularSavingAmount) {
		this.regularSavingAmount = regularSavingAmount;
	}

	/**
	 * @param savingDuration
	 *            the savingDuration to set
	 */
	public void setSavingDuration(final int savingDuration) {
		this.savingDuration = savingDuration;
	}

	/**
	 * @param savingPhaseInterest
	 *            the savingPhaseInterest to set
	 */
	public void setSavingPhaseInterest(final double savingPhaseInterest) {
		this.savingPhaseInterest = savingPhaseInterest;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(final String startDate) {
		this.startDate = startDate;
	}

	/** the amount. */
	private double amount;
	/** the aquisition fee (abschlussgebuehr in prozent) */
	private double aquisitonFee;
	/** the contribution. (zins und tilgungsbeitrag mtl in promille) */
	private double contribution;
	/** the credit interest (guthabenszins) */
	private double creditInterest;
	/** the debit interest (sollzins ab zuteilung) */
	private double debitInterest;
	/** the id. */
	private long id;
	/** the minimum savings (mindestsparguthaben in prozent) */
	private double minimumSavings;
	/** the title. */
	private String name;
	/** the regular saving amount. (monatlicher regelsparbetrag in promille) */
	private double regularSavingAmount;
	/** the saving duration (spardauer). */
	private int savingDuration;
	/** the interest to pay for getting the money, while beeing in saving phase (zins für uebergangsdarlehen). */
	private double savingPhaseInterest;
	/** the start date. */
	private String startDate;

}
