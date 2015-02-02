/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.tree;

import de.sambalmueslie.loan_calculator.backend.compare_mgt.Comparison;
import de.sambalmueslie.loan_calculator.backend.founding_mgt.Founding;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreement;
import de.sambalmueslie.loan_calculator.backend.model.ModelChangeListener;

/**
 * The handler for the {@link ModelChangeListener}.
 *
 * @author sambalmueslie 2015
 */
class ModelChangeHandler implements ModelChangeListener {

	/**
	 * Constructor.
	 *
	 * @param entryTreePane
	 *            {@link #entryTreePane}
	 */
	ModelChangeHandler(final EntryTreePane entryTreePane) {
		this.entryTreePane = entryTreePane;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.model.ModelChangeListener#annuityLoanAdded(de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoan)
	 */
	@Override
	public void annuityLoanAdded(final AnnuityLoan annuityLoan) {
		entryTreePane.add(annuityLoan);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.model.ModelChangeListener#annuityLoanRemoved(de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoan)
	 */
	@Override
	public void annuityLoanRemoved(final AnnuityLoan annuityLoan) {
		entryTreePane.remove(annuityLoan);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.model.ModelChangeListener#buildingLoanAgreementAdded(de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreement)
	 */
	@Override
	public void buildingLoanAgreementAdded(final BuildingLoanAgreement buildingLoanAgreement) {
		entryTreePane.add(buildingLoanAgreement);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.model.ModelChangeListener#buildingLoanAgreementRemoved(de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreement)
	 */
	@Override
	public void buildingLoanAgreementRemoved(final BuildingLoanAgreement buildingLoanAgreement) {
		entryTreePane.remove(buildingLoanAgreement);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.model.ModelChangeListener#comparisonAdded(de.sambalmueslie.loan_calculator.backend.compare_mgt.Comparison)
	 */
	@Override
	public void comparisonAdded(final Comparison<?> comparison) {
		entryTreePane.add(comparison);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.model.ModelChangeListener#comparisonRemoved(de.sambalmueslie.loan_calculator.backend.compare_mgt.Comparison)
	 */
	@Override
	public void comparisonRemoved(final Comparison<?> comparison) {
		entryTreePane.remove(comparison);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#foundingAdded(de.sambalmueslie.loan_calculator.model.founding.Founding)
	 */
	@Override
	public void foundingAdded(final Founding founding) {
		entryTreePane.add(founding);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#foundingRemoved(de.sambalmueslie.loan_calculator.model.founding.Founding)
	 */
	@Override
	public void foundingRemoved(final Founding founding) {
		entryTreePane.remove(founding);
	}

	/** the {@link EntryTreePane}. */
	private final EntryTreePane entryTreePane;

}