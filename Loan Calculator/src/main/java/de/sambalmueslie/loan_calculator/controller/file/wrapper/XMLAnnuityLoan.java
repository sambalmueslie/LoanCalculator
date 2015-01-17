/**
 *
 */
package de.sambalmueslie.loan_calculator.controller.file.wrapper;


/**
 * @author sambalmueslie 2015
 */
public class XMLAnnuityLoan {

	/**
	 * @return the {@link #amount}
	 */
	public double getAmount() {
		return amount;
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
	 * @return the {@link #id}
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the {@link #name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the {@link #paymentRate}
	 */
	public double getPaymentRate() {
		return paymentRate;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(final double amount) {
		this.amount = amount;
	}

	/**
	 * @param estimatedDebitInterest
	 *            the estimatedDebitInterest to set
	 */
	public void setEstimatedDebitInterest(final double estimatedDebitInterest) {
		this.estimatedDebitInterest = estimatedDebitInterest;
	}

	/**
	 * @param fixedDebitInterest
	 *            the fixedDebitInterest to set
	 */
	public void setFixedDebitInterest(final double fixedDebitInterest) {
		this.fixedDebitInterest = fixedDebitInterest;
	}

	/**
	 * @param fixedInterestPeriod
	 *            the fixedInterestPeriod to set
	 */
	public void setFixedInterestPeriod(final int fixedInterestPeriod) {
		this.fixedInterestPeriod = fixedInterestPeriod;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final long id) {
		this.id = id;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @param paymentRate
	 *            the paymentRate to set
	 */
	public void setPaymentRate(final double paymentRate) {
		this.paymentRate = paymentRate;
	}

	/** the amount. */
	private double amount;
	/** the estimated debit interest (geschätzter Sollzins nach Bindungsende). */
	private double estimatedDebitInterest;
	/** the fixed debit interest (Gebundener Sollzins). */
	private double fixedDebitInterest;
	/** the fixed debit interest period (Sollzinsbindung). */
	private int fixedInterestPeriod;
	/** the id. */
	private long id;
	/** the title. */
	private String name;
	/** the payment rate (Tilgung in Prozent). */
	private double paymentRate;
}
