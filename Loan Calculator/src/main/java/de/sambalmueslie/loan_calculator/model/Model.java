/**
 *
 */
package de.sambalmueslie.loan_calculator.model;

import java.util.Collection;

import de.sambalmueslie.loan_calculator.model.compare.Comparison;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.loan.Loan;

/**
 * The model.
 *
 * @author sambalmueslie 2015
 */
public interface Model {

	/**
	 * @return a {@link Collection} of all currently stored {@link Comparison}s.
	 */
	Collection<Comparison<?>> getAllComparisons();

	/**
	 * @return a {@link Collection} of all currently stored {@link Founding}s.
	 */
	Collection<Founding> getAllFoundings();

	/**
	 * @return a {@link Collection} of all currently stored {@link Loan}s.
	 */
	Collection<Loan> getAllLoans();

	/**
	 * Get a {@link Comparison} by id.
	 *
	 * @param id
	 *            the id
	 * @return the comparison or <code>null</code> if not found
	 */
	Comparison<?> getComparison(final long id);

	/**
	 * Get a {@link Founding} by id.
	 *
	 * @param id
	 *            the id
	 * @return the founding or <code>null</code> if not found
	 */
	Founding getFounding(final long id);

	/**
	 * Get a {@link Loan} by id.
	 *
	 * @param id
	 *            the id
	 * @return the loan or <code>null</code> if not found
	 */
	Loan getLoan(final long id);

	/**
	 * @return <code>true</code> if the model is empty, otherwise <code>false</code>.
	 */
	boolean isEmpty();

	/**
	 * Register a {@link ModelChangeListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	void listenerRegister(final ModelChangeListener listener);

	/**
	 * Unregister a {@link ModelChangeListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	void listenerUnregister(final ModelChangeListener listener);

}
