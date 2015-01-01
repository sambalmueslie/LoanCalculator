/**
 *
 */
package de.sambalmueslie.loan_calculator.model;


/**
 * @author sambalmueslie 2015
 */
abstract class BaseLoan implements Loan {

	/**
	 * Constructor.
	 *
	 * @param title
	 *            {@link #title}
	 * @param amount
	 *            {@link #amount}
	 */
	BaseLoan(final String title, final double amount) {
		this.title = title;
		this.amount = amount;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.Loan#getAmount()
	 */
	@Override
	public final double getAmount() {
		return amount;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.Loan#getTitle()
	 */
	@Override
	public final String getTitle() {
		return title;
	}

	/** the amount. */
	private final double amount;
	/** the title. */
	private final String title;

}
