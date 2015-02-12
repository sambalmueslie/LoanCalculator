/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.external;

import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoanSettings;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreementSettings;


/**
 * The loan action listener.
 *
 * @author sambalmueslie 2015
 */
public interface LoanActionListener {

	/**
	 * Request to add a loan.
	 *
	 * @param settings
	 *            the {@link AnnuityLoanSettings}
	 */
	void requestAddAnnuityLoan(AnnuityLoanSettings settings);

	/**
	 * Request to add a building loan agreement.
	 *
	 * @param settings
	 *            the {@link BuildingLoanAgreementSettings}
	 */
	void requestAddBuildingLoanAgreement(BuildingLoanAgreementSettings settings);

	/**
	 * Request to remove a loan.
	 *
	 * @param loanId
	 *            the loan id
	 */
	void requestRemoveLoan(long loanId);

	/**
	 * Request to update a loan.
	 *
	 * @param loanId
	 *            the loan id
	 * @param settings
	 *            the {@link AnnuityLoanSettings}
	 */
	void requestUpdateAnnuityLoan(long loanId, AnnuityLoanSettings settings);

	/**
	 * Request to update a building loan agreement.
	 *
	 * @param loanId
	 *            the loan id
	 * @param settings
	 *            the {@link BuildingLoanAgreementSettings}
	 */
	void requestUpdateBuildingLoanAgreement(long loanId, BuildingLoanAgreementSettings settings);

}
