/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.loan_mgt;

import java.time.LocalDate;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author sambalmueslie 2015
 */
public class LoanSettings {

	/**
	 * Constructor.
	 *
	 * @param name
	 *            {@link #name}
	 * @param amount
	 *            {@link #amount}
	 * @param startDate
	 *            {@link #startDate}
	 */
	public LoanSettings(final String name, final double amount, final LocalDate startDate) {
		this.name = name;
		this.amount = amount;
		this.startDate = startDate;
	}

	/**
	 * @return the {@link #amount}
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @return the {@link #name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the {@link #startDate}
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/** the amount. */
	private final double amount;
	/** the name. */
	private final String name;
	/** the start {@link LocalDate}. */
	private final LocalDate startDate;

}
