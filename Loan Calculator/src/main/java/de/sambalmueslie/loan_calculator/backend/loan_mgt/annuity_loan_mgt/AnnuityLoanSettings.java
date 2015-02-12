/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt;

import java.time.LocalDate;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import de.sambalmueslie.loan_calculator.backend.loan_mgt.LoanSettings;

/**
 * The settings for an annuity loan.
 *
 * @author sambalmueslie 2015
 */
public class AnnuityLoanSettings extends LoanSettings {

	/**
	 * Constructor.
	 *
	 * @param name
	 *            {@link #name}
	 * @param amount
	 *            {@link #amount}
	 * @param startDate
	 *            {@link #startDate}
	 * @param paymentRate
	 *            {@link #paymentRate}
	 * @param fixedDebitInterest
	 *            {@link #fixedDebitInterest}
	 * @param fixedInterestPeriod
	 *            {@link #fixedInterestPeriod}
	 * @param estimatedDebitInterest
	 *            {@link #estimatedDebitInterest}
	 * @param unscheduledRepayment
	 *            {@link #unscheduledRepayment}
	 */
	public AnnuityLoanSettings(final String name, final double amount, final LocalDate startDate, final double paymentRate, final double fixedDebitInterest,
			final int fixedInterestPeriod, final double estimatedDebitInterest, final double unscheduledRepayment) {
		super(name, amount, startDate);
		this.estimatedDebitInterest = estimatedDebitInterest;
		this.fixedDebitInterest = fixedDebitInterest;
		this.fixedInterestPeriod = fixedInterestPeriod;
		this.paymentRate = paymentRate;
		this.unscheduledRepayment = unscheduledRepayment;
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
	 * @return the {@link #unscheduledRepayment}
	 */
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

	/** the estimated debit interest (geschaetzter Sollzins nach Bindungsende). */
	private final double estimatedDebitInterest;
	/** the fixed debit interest (Gebundener Sollzins). */
	private final double fixedDebitInterest;
	/** the fixed debit interest period (Sollzinsbindung). */
	private final int fixedInterestPeriod;
	/** the payment rate (Tilgung in Prozent). */
	private final double paymentRate;
	/** the unscheduled repayment per year (jährliche sondertilgung). */
	private final double unscheduledRepayment;
}
