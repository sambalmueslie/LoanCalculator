/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.file_mgt;

import de.sambalmueslie.loan_calculator.backend.common.BusinessObject;
import de.sambalmueslie.loan_calculator.backend.common.BusinessObjectChangeListener;
import de.sambalmueslie.loan_calculator.backend.compare_mgt.Comparison;
import de.sambalmueslie.loan_calculator.backend.founding_mgt.Founding;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreement;
import de.sambalmueslie.loan_calculator.backend.model.Model;
import de.sambalmueslie.loan_calculator.backend.model.ModelChangeListener;

/**
 * The handler for the {@link ModelChangeListener}.
 *
 * @author sambalmueslie 2015
 */
public class ModelChangeHandler implements ModelChangeListener, BusinessObjectChangeListener<BusinessObject> {

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
		model.getComparisons().forEach(c -> c.register(this));
		model.getFoundings().forEach(f -> f.register(this));
		model.getLoans().forEach(l -> l.register(this));
		model.listenerRegister(this);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.model.ModelChangeListener#annuityLoanAdded(de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoan)
	 */
	@Override
	public void annuityLoanAdded(final AnnuityLoan annuityLoan) {
		added(annuityLoan);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.model.ModelChangeListener#annuityLoanRemoved(de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoan)
	 */
	@Override
	public void annuityLoanRemoved(final AnnuityLoan annuityLoan) {
		removed(annuityLoan);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.model.ModelChangeListener#buildingLoanAgreementAdded(de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreement)
	 */
	@Override
	public void buildingLoanAgreementAdded(final BuildingLoanAgreement buildingLoanAgreement) {
		added(buildingLoanAgreement);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.model.ModelChangeListener#buildingLoanAgreementRemoved(de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreement)
	 */
	@Override
	public void buildingLoanAgreementRemoved(final BuildingLoanAgreement buildingLoanAgreement) {
		removed(buildingLoanAgreement);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.common.BusinessObjectChangeListener#businessObjectChanged(de.sambalmueslie.loan_calculator.backend.common.BusinessObject)
	 */
	@Override
	public void businessObjectChanged(final BusinessObject businessObject) {
		file.setUnsavedChanges();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.model.ModelChangeListener#comparisonAdded(de.sambalmueslie.loan_calculator.backend.compare_mgt.Comparison)
	 */
	@Override
	public void comparisonAdded(final Comparison<?> comparison) {
		added(comparison);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.model.ModelChangeListener#comparisonRemoved(de.sambalmueslie.loan_calculator.backend.compare_mgt.Comparison)
	 */
	@Override
	public void comparisonRemoved(final Comparison<?> comparison) {
		removed(comparison);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#foundingAdded(de.sambalmueslie.loan_calculator.model.founding.Founding)
	 */
	@Override
	public void foundingAdded(final Founding founding) {
		added(founding);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#foundingRemoved(de.sambalmueslie.loan_calculator.model.founding.Founding)
	 */
	@Override
	public void foundingRemoved(final Founding founding) {
		founding.unregister(this);
		file.setUnsavedChanges();
	}

	private void added(final BusinessObject businessObject) {
		businessObject.register(this);
		file.setUnsavedChanges();
	}

	private void removed(final BusinessObject businessObject) {
		businessObject.unregister(this);
		file.setUnsavedChanges();
	}

	/** the {@link BaseLoanFile}. */
	private final BaseLoanFile file;

}
