/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.loan_mgt;

import java.time.LocalDate;
import java.time.Month;

import de.sambalmueslie.loan_calculator.backend.common.BaseBusinessObject;

/**
 * A base {@link Loan}.
 *
 * @author sambalmueslie 2015
 */
public abstract class BaseLoan extends BaseBusinessObject implements Loan {

	/**
	 * @return the {@link LocalDate} of the next month.
	 */
	private static LocalDate getNextMonthDate() {
		final LocalDate now = LocalDate.now();
		final int year = now.getYear();
		final int month = (now.getMonth() == Month.DECEMBER) ? Month.JANUARY.getValue() : now.getMonthValue() + 1;
		final LocalDate nextMonth = LocalDate.of(year, month, 1);
		return nextMonth;
	}

	/**
	 * Constructor.
	 *
	 * @param id
	 *            {@link #id}
	 * @param name
	 *            {@link #name}
	 * @param amount
	 *            {@link #amount}
	 */
	protected BaseLoan(final long id, final String name, final double amount) {
		this(id, name, amount, getNextMonthDate());
	}

	/**
	 * Constructor.
	 *
	 * @param id
	 *            {@link #id}
	 * @param name
	 *            {@link #name}
	 * @param amount
	 *            {@link #amount}
	 * @param startDate
	 *            {@link #startDate}
	 */
	protected BaseLoan(final long id, final String name, final double amount, final LocalDate startDate) {
		super(id, name);
		this.amount = amount;
		this.startDate = startDate;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.Loan#getAmount()
	 */
	@Override
	public final double getAmount() {
		return amount;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan#getEndDate()
	 */
	@Override
	public final LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan#getStartDate()
	 */
	@Override
	public final LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	protected void setAmount(final double amount) {
		this.amount = amount;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	protected void setEndDate(final LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	protected void setStartDate(final LocalDate startDate) {
		this.startDate = (startDate == null) ? getNextMonthDate() : startDate;
	}

	/** the amount. */
	private double amount;
	/** the end {@link LocalDate}. */
	private LocalDate endDate;
	/** the start {@link LocalDate}. */
	private LocalDate startDate;

}
