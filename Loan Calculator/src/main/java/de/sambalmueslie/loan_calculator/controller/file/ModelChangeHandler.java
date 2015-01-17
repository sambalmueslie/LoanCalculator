/**
 *
 */
package de.sambalmueslie.loan_calculator.controller.file;

import de.sambalmueslie.loan_calculator.model.Model;
import de.sambalmueslie.loan_calculator.model.ModelChangeListener;
import de.sambalmueslie.loan_calculator.model.compare.Comparison;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntryChangeListener;
import de.sambalmueslie.loan_calculator.model.loan.Loan;

/**
 * The model change handler.
 *
 * @author sambalmueslie 2015
 */
public class ModelChangeHandler implements ModelChangeListener, GenericModelEntryChangeListener {

	/**
	 * Constructor.
	 *
	 * @param model
	 *            the {@link Model} to register
	 * @param file
	 *            {@link #file}
	 */
	ModelChangeHandler(final Model model, final BaseLoanFile file) {
		this.file = file;
		model.getAllComparisons().forEach(c -> c.register(this));
		model.getAllFoundings().forEach(f -> f.register(this));
		model.getAllLoans().forEach(l -> l.register(this));
		model.listenerRegister(this);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#comparisonAdded(de.sambalmueslie.loan_calculator.model.compare.Comparison)
	 */
	@Override
	public void comparisonAdded(final Comparison<?> comparison) {
		comparison.register(this);
		file.setUnsavedChanges();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#comparisonRemoved(de.sambalmueslie.loan_calculator.model.compare.Comparison)
	 */
	@Override
	public void comparisonRemoved(final Comparison<?> comparison) {
		comparison.unregister(this);
		file.setUnsavedChanges();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.generic.GenericModelEntryChangeListener#entryChanged(de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry)
	 */
	@Override
	public void entryChanged(final GenericModelEntry entry) {
		file.setUnsavedChanges();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#foundingAdded(de.sambalmueslie.loan_calculator.model.founding.Founding)
	 */
	@Override
	public void foundingAdded(final Founding founding) {
		founding.register(this);
		file.setUnsavedChanges();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#foundingRemoved(de.sambalmueslie.loan_calculator.model.founding.Founding)
	 */
	@Override
	public void foundingRemoved(final Founding founding) {
		founding.unregister(this);
		file.setUnsavedChanges();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#loanAdded(de.sambalmueslie.loan_calculator.model.loan.Loan)
	 */
	@Override
	public void loanAdded(final Loan loan) {
		loan.register(this);
		file.setUnsavedChanges();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#loanRemoved(de.sambalmueslie.loan_calculator.model.loan.Loan)
	 */
	@Override
	public void loanRemoved(final Loan loan) {
		loan.unregister(this);
		file.setUnsavedChanges();
	}

	/** the {@link BaseLoanFile}. */
	private final BaseLoanFile file;

}
