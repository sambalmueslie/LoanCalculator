/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.tabs;

import static de.sambalmueslie.loan_calculator.frontend.Constants.CLASS_PANEL_EMPTY;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import de.sambalmueslie.loan_calculator.backend.common.BusinessObject;
import de.sambalmueslie.loan_calculator.backend.compare_mgt.Comparison;
import de.sambalmueslie.loan_calculator.backend.file_mgt.LoanFile;
import de.sambalmueslie.loan_calculator.backend.founding_mgt.Founding;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreement;
import de.sambalmueslie.loan_calculator.backend.model.Model;
import de.sambalmueslie.loan_calculator.backend.model.ModelChangeListener;
import de.sambalmueslie.loan_calculator.frontend.ViewActionListener;

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
		 * @see de.sambalmueslie.loan_calculator.backend.model.ModelChangeListener#annuityLoanAdded(de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoan)
		 */
		@Override
		public void annuityLoanAdded(final AnnuityLoan annuityLoan) {
			// intentionally left empty
		}

		/**
		 * @see de.sambalmueslie.loan_calculator.backend.model.ModelChangeListener#annuityLoanRemoved(de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoan)
		 */
		@Override
		public void annuityLoanRemoved(final AnnuityLoan annuityLoan) {
			remove(annuityLoan);
		}

		/**
		 * @see de.sambalmueslie.loan_calculator.backend.model.ModelChangeListener#buildingLoanAgreementAdded(de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreement)
		 */
		@Override
		public void buildingLoanAgreementAdded(final BuildingLoanAgreement buildingLoanAgreement) {
			// intentionally left empty
		}

		/**
		 * @see de.sambalmueslie.loan_calculator.backend.model.ModelChangeListener#buildingLoanAgreementRemoved(de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreement)
		 */
		@Override
		public void buildingLoanAgreementRemoved(final BuildingLoanAgreement buildingLoanAgreement) {
			remove(buildingLoanAgreement);
		}

		/**
		 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#comparisonAdded(de.sambalmueslie.loan_calculator.model.compare.Comparison)
		 */
		@Override
		public void comparisonAdded(final Comparison<?> comparison) {
			// add(comparison);
		}

		/**
		 * @see de.sambalmueslie.loan_calculator.backend.model.ModelChangeListener#comparisonRemoved(de.sambalmueslie.loan_calculator.backend.compare_mgt.Comparison)
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

	public void show(final BusinessObject entry) {
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
	}

	/**
	 * Add a {@link GenericModelEntry}.
	 *
	 * @param entry
	 *            the entry
	 */
	private void add(final BusinessObject entry) {
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
	private void remove(final BusinessObject entry) {
		getTabs().removeIf(t -> ((EntryTab) t).getEntry().equals(entry));
	}

	/** the {@link ViewActionListener}. */
	private final ViewActionListener actionListener;
	/** the {@link Model}. */
	private Model model;

}
