/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.model;

import de.sambalmueslie.loan_calculator.backend.compare_mgt.Comparison;
import de.sambalmueslie.loan_calculator.backend.founding_mgt.Founding;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreement;

/**
 * The change listener for the model.
 *
 * @author sambalmueslie 2015
 */
public interface ModelChangeListener {
	/**
	 * A {@link AnnuityLoan} was added.
	 *
	 * @param annuityLoan
	 *            the annuity loan
	 */
	void annuityLoanAdded(AnnuityLoan annuityLoan);

	/**
	 * A {@link AnnuityLoan} was removed.
	 *
	 * @param annuityLoan
	 *            the annuity loan
	 */
	void annuityLoanRemoved(AnnuityLoan annuityLoan);

	/**
	 * A {@link BuildingLoanAgreement} was added.
	 *
	 * @param buildingLoanAgreement
	 *            the building loan agreement
	 */
	void buildingLoanAgreementAdded(BuildingLoanAgreement buildingLoanAgreement);

	/**
	 * A {@link BuildingLoanAgreement} was removed.
	 *
	 * @param buildingLoanAgreement
	 *            the building loan agreement
	 */
	void buildingLoanAgreementRemoved(BuildingLoanAgreement buildingLoanAgreement);

	/**
	 * A {@link Comparison} was added.
	 *
	 * @param comparison
	 *            the comparison
	 */
	void comparisonAdded(Comparison<?> comparison);

	/**
	 * A {@link Comparison} was removed.
	 *
	 * @param comparison
	 *            the comparison
	 */
	void comparisonRemoved(Comparison<?> comparison);

	/**
	 * A {@link Founding} was added.
	 *
	 * @param founding
	 *            the founding
	 */
	void foundingAdded(Founding founding);

	/**
	 * A {@link Founding} was added.
	 *
	 * @param founding
	 *            the founding
	 */
	void foundingRemoved(Founding founding);

}
