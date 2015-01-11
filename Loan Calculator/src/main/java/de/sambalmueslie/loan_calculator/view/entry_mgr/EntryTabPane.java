/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr;

import javafx.scene.control.TabPane;
import de.sambalmueslie.loan_calculator.model.Model;
import de.sambalmueslie.loan_calculator.model.ModelChangeListener;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;
import de.sambalmueslie.loan_calculator.model.loan.Loan;

/**
 * The {@link TabPane} for the
 *
 * @author sambalmueslie 2015
 */
public class EntryTabPane extends TabPane {
	/**
	 * The handler for the {@link ModelChangeListener}.
	 *
	 * @author sambalmueslie 2015
	 */
	private class ModelChangeHandler implements ModelChangeListener {

		/**
		 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#foundingAdded(de.sambalmueslie.loan_calculator.model.founding.Founding)
		 */
		@Override
		public void foundingAdded(final Founding founding) {
			add(founding);
		}

		/**
		 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#foundingRemoved(de.sambalmueslie.loan_calculator.model.founding.Founding)
		 */
		@Override
		public void foundingRemoved(final Founding founding) {
			remove(founding);
		}

		/**
		 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#loanAdded(de.sambalmueslie.loan_calculator.model.loan.Loan)
		 */
		@Override
		public void loanAdded(final Loan loan) {
			add(loan);
		}

		/**
		 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#loanRemoved(de.sambalmueslie.loan_calculator.model.loan.Loan)
		 */
		@Override
		public void loanRemoved(final Loan loan) {
			remove(loan);
		}

	}

	/**
	 * Constructor.
	 */
	public EntryTabPane(final Model model) {
		model.listenerRegister(modelChangeHandler);

		model.getAllLoans().forEach(loan -> add(loan));
		model.getAllFoundings().forEach(founding -> add(founding));

	}

	/**
	 * Add a {@link GenericModelEntry}.
	 *
	 * @param entry
	 *            the entry
	 */
	private void add(final GenericModelEntry<?> entry) {
		final EntryTab tab = new EntryTab(entry);
		getTabs().add(tab);
		getSelectionModel().select(tab);
	}

	/**
	 * Remove a {@link GenericModelEntry}.
	 *
	 * @param entry
	 *            the entry
	 */
	private void remove(final GenericModelEntry<?> entry) {
		getTabs().removeIf(t -> ((EntryTab) t).getEntry().equals(entry));
	}

	private final ModelChangeHandler modelChangeHandler = new ModelChangeHandler();
}
