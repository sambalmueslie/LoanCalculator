/**
 *
 */
package de.sambalmueslie.loan_calculator.model.loan;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntryChangeListener;

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
	BaseLoan(final String name, final double amount) throws IllegalArgumentException {
		id = UUID.randomUUID().getLeastSignificantBits();
		update(name, amount);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.Loan#getAmount()
	 */
	@Override
	public final double getAmount() {
		return amount;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.Loan#getId()
	 */
	@Override
	public long getId() {
		return id;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.loan.Loan#getName()
	 */
	@Override
	public final String getName() {
		return name;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry#register(de.sambalmueslie.loan_calculator.model.generic.GenericModelEntryChangeListener)
	 */
	@Override
	public final void register(final GenericModelEntryChangeListener<Loan> listener) {
		if (listener == null || listeners.contains(listener)) return;
		listeners.add(listener);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry#unregister(de.sambalmueslie.loan_calculator.model.generic.GenericModelEntryChangeListener)
	 */
	@Override
	public final void unregister(final GenericModelEntryChangeListener<Loan> listener) {
		if (listener == null || !listeners.contains(listener)) return;
		listeners.remove(listener);
	}

	/**
	 * Notify that the {@link Loan} has changed.
	 */
	protected void notifyChanged() {
		listeners.forEach(l -> l.entryChanged(this));
	}

	/**
	 * Update.
	 *
	 * @param name
	 *            {@link #name}
	 * @param amount
	 *            {@link #amount}
	 */
	protected void update(final String name, final double amount) {
		if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name '" + name + "' for loan cannot be null or empty.");
		this.name = name;
		if (amount <= 0) throw new IllegalArgumentException("Amount '" + amount + "'  for loan cannot be lower or equals 0.");
		this.amount = amount;
	}

	/** the amount. */
	private double amount;
	/** the id. */
	private final long id;
	/** the {@link GenericModelEntryChangeListener}. */
	private final List<GenericModelEntryChangeListener<Loan>> listeners = new LinkedList<>();
	/** the title. */
	private String name;

}
