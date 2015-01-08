/**
 *
 */
package de.sambalmueslie.loan_calculator.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.generic.GenericModel;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelListener;
import de.sambalmueslie.loan_calculator.model.loan.Loan;

/**
 * The base {@link Model}.
 *
 * @author sambalmueslie 2015
 */
public class BaseModel implements Model {

	/**
	 * Constructor.
	 */
	public BaseModel() {
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
				listeners.forEach(l -> l.loanAdded(entry));
			}

			@Override
			public void entryRemoved(final GenericModel<Loan> model, final Loan entry) {
				listeners.forEach(l -> l.loanRemoved(entry));
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
	 * @see de.sambalmueslie.loan_calculator.model.Model#getAllFoundings()
	 */
	@Override
	public Collection<Founding> getAllFoundings() {
		return foundingModel.getAll();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.Model#getAllLoans()
	 */
	@Override
	public Collection<Loan> getAllLoans() {
		return loanModel.getAll();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.Model#getFounding(long)
	 */
	@Override
	public Founding getFounding(final long id) {
		return foundingModel.get(id);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.Model#getLoan(long)
	 */
	@Override
	public Loan getLoan(final long id) {
		return loanModel.get(id);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.Model#listenerRegister(de.sambalmueslie.loan_calculator.model.ModelChangeListener)
	 */
	@Override
	public void listenerRegister(final ModelChangeListener listener) {
		if (listener != null && !listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.Model#listenerUnregister(de.sambalmueslie.loan_calculator.model.ModelChangeListener)
	 */
	@Override
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
