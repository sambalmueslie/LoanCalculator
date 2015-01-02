/**
 *
 */
package de.sambalmueslie.loan_calculator.model;

import java.util.UUID;

/**
 * @author sambalmueslie 2015
 */
abstract class BaseLoan implements Loan {

	/**
	 * Constructor.
	 *
	 * @param name
	 *            {@link #name}
	 * @param amount
	 *            {@link #amount}
	 */
	BaseLoan(final String name, final double amount) {
		this.name = name;
		this.amount = amount;
		id = UUID.randomUUID().getLeastSignificantBits();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.Loan#getAmount()
	 */
	@Override
	public final double getAmount() {
		return amount;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.Loan#getId()
	 */
	@Override
	public long getId() {
		return id;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.Loan#getName()
	 */
	@Override
	public final String getName() {
		return name;
	}

	/** the amount. */
	private final double amount;
	/** the id. */
	private final long id;
	/** the title. */
	private final String name;

}
