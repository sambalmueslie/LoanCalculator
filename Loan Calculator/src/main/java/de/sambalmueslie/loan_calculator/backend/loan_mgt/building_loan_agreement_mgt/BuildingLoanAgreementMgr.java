/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.loan_calculator.backend.common.BaseBusinessObjectMgr;

/**
 * The {@link BaseBusinessObjectMgr} for the {@link BuildingLoanAgreement}.
 *
 * @author sambalmueslie 2015
 */
public class BuildingLoanAgreementMgr extends BaseBusinessObjectMgr<BuildingLoanAgreement> {
	/** the logger. */
	private static final Logger logger = LogManager.getLogger(BuildingLoanAgreementMgr.class);

	public BuildingLoanAgreement add(final BuildingLoanAgreementSettings settings) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to add building loan agreement " + settings);
		}
		if (!isInputValid(settings)) return null;
		final BaseBuildingLoanAgreement baseBuildingLoanAgreement = new BaseBuildingLoanAgreement(createNewId(), settings);
		add(baseBuildingLoanAgreement);
		return baseBuildingLoanAgreement;
	}

	public void remove(final long loanId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to remove loan " + loanId);
		}
		final BuildingLoanAgreement loan = get(loanId);
		if (loan == null) return;
		remove(loan);
	}

	public BuildingLoanAgreement update(final long loanId, final BuildingLoanAgreementSettings settings) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to update building loan agreement " + loanId + ", " + settings);
		}
		if (!isInputValid(settings)) return null;
		final BuildingLoanAgreement loan = get(loanId);
		if (loan == null) return null;
		final BaseBuildingLoanAgreement buildingLoanAgreement = (BaseBuildingLoanAgreement) loan;
		buildingLoanAgreement.update(settings);
		return buildingLoanAgreement;
	}

	private boolean isInputValid(final BuildingLoanAgreementSettings settings) {
		if (settings.getName() == null || settings.getName().isEmpty()) return false;
		if (settings.getAmount() <= 0) return false;
		if (settings.getCreditInterest() <= 0 || settings.getCreditInterest() >= 100) return false;
		if (settings.getRegularSavingAmount() <= 0 || settings.getRegularSavingAmount() >= 100) return false;
		if (settings.getMinimumSavings() <= 0 || settings.getMinimumSavings() >= 100) return false;
		if (settings.getSavingDuration() < 0) return false;
		if (settings.getSavingPhaseInterest() <= 0 || settings.getSavingPhaseInterest() >= 100) return false;
		if (settings.getDebitInterest() <= 0 || settings.getDebitInterest() >= 100) return false;
		if (settings.getContribution() <= 0 || settings.getContribution() >= 100) return false;
		if (settings.getAquisitonFee() < 0 || settings.getAquisitonFee() >= 100) return false;
		if (settings.getStartDate() == null) return false;
		return true;
	}

}
