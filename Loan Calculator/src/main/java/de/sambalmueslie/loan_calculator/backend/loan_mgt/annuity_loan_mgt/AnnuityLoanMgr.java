/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.loan_calculator.backend.common.BaseBusinessObjectMgr;
import de.sambalmueslie.loan_calculator.backend.common.BusinessObjectMgr;

/**
 * The {@link AnnuityLoan} {@link BusinessObjectMgr}.
 *
 * @author sambalmueslie 2015
 */
public class AnnuityLoanMgr extends BaseBusinessObjectMgr<AnnuityLoan> {
	/** the logger. */
	private static final Logger logger = LogManager.getLogger(AnnuityLoanMgr.class);

	public AnnuityLoan add(final AnnuityLoanSettings settings) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to add loan " + settings);
		}
		if (!isInputValid(settings)) {
			return null;
		}
		final AnnuityLoan annuityLoan = new BaseAnnuityLoan(createNewId(), settings);
		add(annuityLoan);
		return annuityLoan;
	}

	public void remove(final long loanId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to remove loan " + loanId);
		}
		final AnnuityLoan loan = get(loanId);
		if (loan == null) {
			return;
		}
		remove(loan);
	}

	public AnnuityLoan update(final long loanId, final AnnuityLoanSettings settings) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to update loan " + loanId + ", " + settings);
		}
		if (!isInputValid(settings)) {
			return null;
		}
		final AnnuityLoan loan = get(loanId);
		if (loan == null) {
			return null;
		}
		final BaseAnnuityLoan annuityLoan = (BaseAnnuityLoan) loan;
		annuityLoan.update(settings);
		return annuityLoan;
	}

	private boolean isInputValid(final AnnuityLoanSettings settings) {
		if (settings.getName() == null || settings.getName().isEmpty()) {
			return false;
		}
		if (settings.getAmount() <= 0) {
			return false;
		}
		if (settings.getPaymentRate() <= 0 || settings.getPaymentRate() >= 100) {
			return false;
		}
		if (settings.getFixedDebitInterest() < 0 || settings.getFixedDebitInterest() >= 100) {
			return false;
		}
		if (settings.getFixedInterestPeriod() < 0) {
			return false;
		}
		if (settings.getEstimatedDebitInterest() < 0 || settings.getEstimatedDebitInterest() >= 100) {
			return false;
		}
		if (settings.getStartDate() == null) {
			return false;
		}
		if (settings.getUnscheduledRepayment() < 0 || settings.getUnscheduledRepayment() >= 100) {
			return false;
		}
		return true;
	}

}
