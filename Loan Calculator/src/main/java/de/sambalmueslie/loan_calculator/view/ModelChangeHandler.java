/**
 *
 */
package de.sambalmueslie.loan_calculator.view;

import de.sambalmueslie.loan_calculator.model.Loan;
import de.sambalmueslie.loan_calculator.model.Model;
import de.sambalmueslie.loan_calculator.model.ModelChangeListener;

/**
 * The handler for the {@link ModelChangeListener}.
 *
 * @author sambalmueslie 2015
 */
public class ModelChangeHandler implements ModelChangeListener {

	/**
	 * Constructor.
	 *
	 * @param view
	 */
	ModelChangeHandler(final View view) {
		this.view = view;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#loanAdded(de.sambalmueslie.loan_calculator.model.Loan)
	 */
	@Override
	public void loanAdded(final Loan loan) {
		view.handleLoanAdded(loan);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#loanRemoved(de.sambalmueslie.loan_calculator.model.Loan)
	 */
	@Override
	public void loanRemoved(final Loan loan) {
		view.handleLoanRemoved(loan);
	}

	/**
	 * Register.
	 *
	 * @param model
	 *            the {@link Model}
	 */
	void register(final Model model) {
		model.listenerRegister(this);
	}

	/**
	 * Unregister.
	 *
	 * @param model
	 *            the {@link Model}
	 */
	void unregister(final Model model) {
		model.listenerUnregister(this);
	}

	/** the {@link View}. */
	private final View view;

}
