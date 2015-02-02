/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.loan_mgt;

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
	 * @param amount
	 *            {@link #amount}
	 */
	protected BaseLoan(final long id, final String name, final double amount) {
		super(id, name);
		this.amount = amount;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.Loan#getAmount()
	 */
	@Override
	public final double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	protected void setAmount(final double amount) {
		this.amount = amount;
	}

	/** the amount. */
	private double amount;

}
