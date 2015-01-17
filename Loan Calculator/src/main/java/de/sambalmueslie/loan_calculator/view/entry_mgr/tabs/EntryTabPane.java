/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr.tabs;

import static de.sambalmueslie.loan_calculator.view.Constants.CLASS_PANEL_EMPTY;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import de.sambalmueslie.loan_calculator.controller.file.LoanFile;
import de.sambalmueslie.loan_calculator.model.Model;
import de.sambalmueslie.loan_calculator.model.ModelChangeListener;
import de.sambalmueslie.loan_calculator.model.compare.Comparison;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;

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
		 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#comparisonAdded(de.sambalmueslie.loan_calculator.model.compare.Comparison)
		 */
		@Override
		public void comparisonAdded(final Comparison<?> comparison) {
			// add(comparison);
		}

		/**
		 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#comparisonRemoved(de.sambalmueslie.loan_calculator.model.compare.Comparison)
		 */
		@Override
		public void comparisonRemoved(final Comparison<?> comparison) {
			remove(comparison);
		}

		/**
		 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#foundingAdded(de.sambalmueslie.loan_calculator.model.founding.Founding)
		 */
		@Override
		public void foundingAdded(final Founding founding) {
			// add(founding);
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
			// add(loan);
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
	 *
	 * @param actionListener
	 *            {@link #actionListener}
	 */
	public EntryTabPane(final ViewActionListener actionListener) {
		this.actionListener = actionListener;
		getStyleClass().add(CLASS_PANEL_EMPTY);

	}

	public void show(final GenericModelEntry entry) {
		for (final Tab tab : getTabs()) {
			if (((EntryTab) tab).getEntry().equals(entry)) return;
		}
		add(entry);
	}

	/**
	 * Show.
	 *
	 * @param file
	 *            the {@link LoanFile}
	 */
	public void update(final LoanFile file) {
		getTabs().clear();
		model = file.getModel();
		model.listenerRegister(new ModelChangeHandler());
		//
		// model.getAllLoans().forEach(this::add);
		// model.getAllFoundings().forEach(this::add);
		// model.getAllComparisons().forEach(this::add);
	}

	/**
	 * Add a {@link GenericModelEntry}.
	 *
	 * @param entry
	 *            the entry
	 */
	private void add(final GenericModelEntry entry) {
		final EntryTab tab = new EntryTab(entry, actionListener, model);
		getTabs().add(tab);
		getSelectionModel().select(tab);
	}

	/**
	 * Remove a {@link GenericModelEntry}.
	 *
	 * @param entry
	 *            the entry
	 */
	private void remove(final GenericModelEntry entry) {
		getTabs().removeIf(t -> ((EntryTab) t).getEntry().equals(entry));
	}

	/** the {@link ViewActionListener}. */
	private final ViewActionListener actionListener;
	/** the {@link Model}. */
	private Model model;

}
