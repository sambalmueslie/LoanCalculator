/**
 *
 */
package de.sambalmueslie.loan_calculator.model;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The model.
 *
 * @author sambalmueslie 2015
 */
public class Model {

	/** the logger. */
	private static final Logger logger = LogManager.getLogger(Model.class);

	/**
	 * Add a new annuity loan.
	 *
	 * @param name
	 *            the name
	 * @param amount
	 *            the amount
	 * @param paymentRate
	 *            the payment rate
	 * @param fixedDebitInterest
	 *            the fixed debit interest
	 * @param fixedInterestPeriod
	 *            the fixed interest period
	 * @param estimatedDebitInterest
	 *            the estimated debit interest
	 */
	public void addAnnuityLoan(final String name, final double amount, final double paymentRate, final double fixedDebitInterest,
			final int fixedInterestPeriod, final double estimatedDebitInterest) {
		final AnnuityLoan annuityLoan = new AnnuityLoan(name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest);
		add(annuityLoan);
	}

	/**
	 * Get a {@link Loan} by id.
	 *
	 * @param id
	 *            the id
	 * @return the loan or <code>null</code> if not found
	 */
	public Loan get(final long id) {
		return loans.get(id);
	}

	/**
	 * @return a {@link Collection} of all currently stored {@link Loan}s.
	 */
	public Collection<Loan> getAll() {
		return Collections.unmodifiableCollection(loans.values());
	}

	/**
	 * Register a {@link ModelChangeListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	public void listenerRegister(final ModelChangeListener listener) {
		if (listener != null && !listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	/**
	 * Unregister a {@link ModelChangeListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	public void listenerUnregister(final ModelChangeListener listener) {
		if (listener != null) {
			listeners.remove(listener);
		}
	}

	/**
	 * Remove a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	public void remove(final Loan loan) {
		if (loan == null) {
			logger.error("Cannot remove loan null value.");
			return;
		}
		final long id = loan.getId();
		if (!loans.containsKey(id)) {
			logger.warn("Try to remove not added loan " + loan);
			return;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Remove loan " + loan);
		}
		loans.remove(id);
		listeners.forEach(l -> l.loanRemoved(loan));
	}

	/**
	 * Update a annuity loan.
	 *
	 * @param loanId
	 *            the loan id
	 * @param name
	 *            the name
	 * @param amount
	 *            the amount
	 * @param paymentRate
	 *            the payment rate
	 * @param fixedDebitInterest
	 *            the fixed debit interest
	 * @param fixedInterestPeriod
	 *            the fixed interest period
	 * @param estimatedDebitInterest
	 *            the estimated debit interest
	 */
	public void updateAnnuityLoan(final long loanId, final String name, final double amount, final double paymentRate, final double fixedDebitInterest,
			final int fixedInterestPeriod, final double estimatedDebitInterest) {
		final Loan loan = loans.get(loanId);
		if (loan == null || !(loan instanceof AnnuityLoan)) return;
		final AnnuityLoan annuityLoan = (AnnuityLoan) loan;
		annuityLoan.update(name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest);
	}

	/**
	 * Add a {@link Loan}.
	 *
	 * @param loan
	 *            the loan to add
	 */
	private void add(final Loan loan) {
		if (loan == null) {
			logger.error("Cannot add loan null value.");
			return;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Add new loan " + loan);
		}
		final long id = loan.getId();
		loans.put(id, loan);
		listeners.forEach(l -> l.loanAdded(loan));
	}

	/** the {@link ModelChangeListener}. */
	private final List<ModelChangeListener> listeners = new LinkedList<>();

	/** the {@link Loan} by id. */
	private final Map<Long, Loan> loans = new HashMap<>();
}
