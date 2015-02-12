/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.loan_mgt;

import java.time.LocalDate;

import de.sambalmueslie.loan_calculator.backend.common.BaseBusinessObject;

/**
 * A base {@link Loan}.
 *
 * @author sambalmueslie 2015
 */
public abstract class BaseLoan extends BaseBusinessObject implements Loan {

	/**
	 * Constructor.
	 *
	 * @param id
	 *            {@link #id}
	 * @param name
	 *            {@link #name}
	 */
	protected BaseLoan(final long id, final String name) {
		super(id, name);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan#getAmount()
	 */
	@Override
	public double getAmount() {
		return settings.getAmount();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan#getEndDate()
	 */
	@Override
	public final LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan#getSettings()
	 */
	@Override
	public LoanSettings getSettings() {
		return settings;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan#getStartDate()
	 */
	@Override
	public LocalDate getStartDate() {
		return settings.getStartDate();
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	protected void setEndDate(final LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * @param settings
	 *            the settings to set
	 */
	protected void setSettings(final LoanSettings settings) {
		this.settings = settings;
	}

	/** the end {@link LocalDate}. */
	private LocalDate endDate;
	/** the {@link LoanSettings}. */
	private LoanSettings settings;

}
