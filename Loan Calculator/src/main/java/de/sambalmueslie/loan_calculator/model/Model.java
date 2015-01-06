/**
 *
 */
package de.sambalmueslie.loan_calculator.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.generic.GenericModel;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelListener;
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
	 * Constructor.
	 */
	public Model() {
		foundingModel = new GenericModel<>();
		foundingModel.register(new GenericModelListener<Founding>() {

			@Override
			public void entryAdded(final GenericModel<Founding> model, final Founding entry) {
				listeners.forEach(l -> l.foundingAdded(entry));
			}

			@Override
			public void entryRemoved(final GenericModel<Founding> model, final Founding entry) {
				listeners.forEach(l -> l.foundingRemoved(entry));
			}
		});
		loanModel = new GenericModel<>();
		loanModel.register(new GenericModelListener<Loan>() {

			@Override
			public void entryAdded(final GenericModel<Loan> model, final Loan entry) {
				listeners.forEach(l -> l.loanRemoved(entry));
			}

			@Override
			public void entryRemoved(final GenericModel<Loan> model, final Loan entry) {
				listeners.forEach(l -> l.loanAdded(entry));
			}
		});
	}

	/**
	 * Add a {@link Founding}.
	 *
	 * @param founding
	 *            the founding.
	 */
	public void add(final Founding founding) {
		foundingModel.add(founding);
	}

	/**
	 * Add a {@link Loan}.
	 *
	 * @param loan
	 *            the loan to add
	 */
	public void add(final Loan loan) {
		loanModel.add(loan);
	}

	/**
	 * @return a {@link Collection} of all currently stored {@link Founding}s.
	 */
	public Collection<Founding> getAllFoundings() {
		return foundingModel.getAll();
	}

	/**
	 * @return a {@link Collection} of all currently stored {@link Loan}s.
	 */
	public Collection<Loan> getAllLoans() {
		return loanModel.getAll();
	}

	/**
	 * Get a {@link Founding} by id.
	 *
	 * @param id
	 *            the id
	 * @return the founding or <code>null</code> if not found
	 */
	public Founding getFounding(final long id) {
		return foundingModel.get(id);
	}

	/**
	 * Get a {@link Loan} by id.
	 *
	 * @param id
	 *            the id
	 * @return the loan or <code>null</code> if not found
	 */
	public Loan getLoan(final long id) {
		return loanModel.get(id);
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
	 *            the founding
	 */
	public void remove(final Founding founding) {
		foundingModel.remove(founding);
	}

	/**
	 * Remove a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	public void remove(final Loan loan) {
		loanModel.remove(loan);
	}

	/** the {@link GenericModel} for the {@link Founding}s. */
	private final GenericModel<Founding> foundingModel;
	/** the {@link ModelChangeListener}. */
	private final List<ModelChangeListener> listeners = new LinkedList<>();
	/** the {@link GenericModel} for the {@link Loan}s. */
	private final GenericModel<Loan> loanModel;
}
