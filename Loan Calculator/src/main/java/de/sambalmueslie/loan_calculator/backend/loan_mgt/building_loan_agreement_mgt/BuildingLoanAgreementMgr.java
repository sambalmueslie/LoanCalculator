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

	public BuildingLoanAgreement add(final String name, final double amount, final double creditInterest, final double regularSavingAmount,
			final double minimumSavings, final int savingDuration, final double savingPhaseInterest, final double debitInterest, final double contribution,
			final double aquisitonFee) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to add building loan agreement " + name + ", " + amount + ", " + creditInterest + ", " + regularSavingAmount + ", "
					+ minimumSavings + ", " + savingDuration + ", " + savingPhaseInterest + ", " + debitInterest + ", " + contribution + ", " + aquisitonFee);
		}
		if (!isInputValid(name, amount, creditInterest, regularSavingAmount, minimumSavings, savingDuration, savingPhaseInterest, debitInterest, contribution,
				aquisitonFee)) return null;
		final BaseBuildingLoanAgreement baseBuildingLoanAgreement = new BaseBuildingLoanAgreement(createNewId(), name, amount, creditInterest,
				regularSavingAmount, minimumSavings, savingDuration, savingPhaseInterest, debitInterest, contribution, aquisitonFee);
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

	public BuildingLoanAgreement update(final long loanId, final String name, final double amount, final double creditInterest,
			final double regularSavingAmount, final double minimumSavings, final int savingDuration, final double savingPhaseInterest,
			final double debitInterest, final double contribution, final double aquisitonFee) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to update building loan agreement " + loanId + ", " + name + ", " + amount + ", " + creditInterest + ", "
					+ regularSavingAmount + ", " + minimumSavings + ", " + savingDuration + ", " + savingPhaseInterest + ", " + debitInterest + ", "
					+ contribution + ", " + aquisitonFee);
		}
		if (!isInputValid(name, amount, creditInterest, regularSavingAmount, minimumSavings, savingDuration, savingPhaseInterest, debitInterest, contribution,
				aquisitonFee)) return null;
		final BuildingLoanAgreement loan = get(loanId);
		if (loan == null) return null;
		final BaseBuildingLoanAgreement buildingLoanAgreement = (BaseBuildingLoanAgreement) loan;
		buildingLoanAgreement.update(name, amount, creditInterest, regularSavingAmount, minimumSavings, savingDuration, savingPhaseInterest, debitInterest,
				contribution, aquisitonFee);
		return buildingLoanAgreement;
	}

	private boolean isInputValid(final String name, final double amount, final double creditInterest, final double regularSavingAmount,
			final double minimumSavings, final int savingDuration, final double savingPhaseInterest, final double debitInterest, final double contribution,
			final double aquisitonFee) {
		if (name == null || name.isEmpty()) return false;
		if (amount <= 0) return false;
		if (creditInterest <= 0 || creditInterest >= 100) return false;
		if (regularSavingAmount <= 0 || regularSavingAmount >= 100) return false;
		if (minimumSavings <= 0 || minimumSavings >= 100) return false;
		if (savingDuration < 0) return false;
		if (savingPhaseInterest <= 0 || savingPhaseInterest >= 100) return false;
		if (debitInterest <= 0 || debitInterest >= 100) return false;
		if (contribution <= 0 || contribution >= 100) return false;
		if (aquisitonFee < 0 || aquisitonFee >= 100) return false;
		return true;
	}

}
