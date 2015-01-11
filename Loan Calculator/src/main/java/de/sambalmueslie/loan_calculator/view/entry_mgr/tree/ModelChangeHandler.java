/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr.tree;

import de.sambalmueslie.loan_calculator.model.ModelChangeListener;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.loan.Loan;

/**
 * The handler for the {@link ModelChangeListener}.
 *
 * @author sambalmueslie 2015
 */
class ModelChangeHandler implements ModelChangeListener {

	/**
	 * Constructor.
	 *
	 * @param entryTree
	 *            {@link #entryTree}
	 */
	ModelChangeHandler(final EntryTree entryTree) {
		this.entryTree = entryTree;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#foundingAdded(de.sambalmueslie.loan_calculator.model.founding.Founding)
	 */
	@Override
	public void foundingAdded(final Founding founding) {
		entryTree.add(founding);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#foundingRemoved(de.sambalmueslie.loan_calculator.model.founding.Founding)
	 */
	@Override
	public void foundingRemoved(final Founding founding) {
		entryTree.remove(founding);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#loanAdded(de.sambalmueslie.loan_calculator.model.loan.Loan)
	 */
	@Override
	public void loanAdded(final Loan loan) {
		entryTree.add(loan);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#loanRemoved(de.sambalmueslie.loan_calculator.model.loan.Loan)
	 */
	@Override
	public void loanRemoved(final Loan loan) {
		entryTree.remove(loan);
	}

	/** the {@link EntryTree}. */
	private final EntryTree entryTree;

}