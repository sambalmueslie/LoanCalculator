/**
 *
 */
package de.sambalmueslie.loan_calculator.model;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.loan.Loan;

/**
 * The model.
 *
 * @author sambalmueslie 2015
 */
public class Model {

	/** the logger. */
	private static final Logger logger = LogManager.getLogger(Model.class);

	/**
	 * Add a {@link Founding}.
	 *
	 * @param founding
	 *            the founding.
	 */
	public void add(final Founding founding) {
		if (founding == null) {
			logger.error("Cannot add founding null value.");
			return;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Add new founding " + founding);
		}
		final long id = founding.getId();
		foundings.put(id, founding);
		listeners.forEach(l -> l.foundingAdded(founding));
	}

	/**
	 * Add a {@link Loan}.
	 *
	 * @param loan
	 *            the loan to add
	 */
	public void add(final Loan loan) {
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

	/**
	 * @return a {@link Collection} of all currently stored {@link Founding}s.
	 */
	public Collection<Founding> getAllFoundings() {
		return Collections.unmodifiableCollection(foundings.values());
	}

	/**
	 * @return a {@link Collection} of all currently stored {@link Loan}s.
	 */
	public Collection<Loan> getAllLoans() {
		return Collections.unmodifiableCollection(loans.values());
	}

	/**
	 * Get a {@link Founding} by id.
	 *
	 * @param id
	 *            the id
	 * @return the founding or <code>null</code> if not found
	 */
	public Founding getFounding(final long id) {
		return foundings.get(id);
	}

	/**
	 * Get a {@link Loan} by id.
	 *
	 * @param id
	 *            the id
	 * @return the loan or <code>null</code> if not found
	 */
	public Loan getLoan(final long id) {
		return loans.get(id);
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
	 * Remove a {@link Founding}.
	 *
	 * @param founding
	 *            the loan
	 */
	public void remove(final Founding founding) {
		if (founding == null) {
			logger.error("Cannot remove founding null value.");
			return;
		}
		final long id = founding.getId();
		if (!loans.containsKey(id)) {
			logger.warn("Try to remove not added founding " + founding);
			return;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Remove founding " + founding);
		}
		foundings.remove(id);
		listeners.forEach(l -> l.foundingRemoved(founding));
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

	/** the {@link Founding} by id. */
	private final Map<Long, Founding> foundings = new HashMap<>();

	/** the {@link ModelChangeListener}. */
	private final List<ModelChangeListener> listeners = new LinkedList<>();

	/** the {@link Loan} by id. */
	private final Map<Long, Loan> loans = new HashMap<>();
}
