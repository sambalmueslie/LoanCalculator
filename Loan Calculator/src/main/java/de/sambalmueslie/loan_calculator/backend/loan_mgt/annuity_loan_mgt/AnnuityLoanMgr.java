/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt;

import java.time.LocalDate;

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

	public AnnuityLoan add(final String name, final double amount, final LocalDate startDate, final double paymentRate, final double fixedDebitInterest,
			final int fixedInterestPeriod, final double estimatedDebitInterest) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to add loan " + name + ", " + amount + ", " + startDate + ", " + paymentRate + ", " + fixedDebitInterest + ", "
					+ fixedInterestPeriod + ", " + estimatedDebitInterest);
		}
		if (!isInputValid(name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest)) return null;
		final AnnuityLoan annuityLoan = new BaseAnnuityLoan(createNewId(), name, amount, startDate, paymentRate, fixedDebitInterest, fixedInterestPeriod,
				estimatedDebitInterest);
		add(annuityLoan);
		return annuityLoan;
	}

	public void remove(final long loanId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to remove loan " + loanId);
		}
		final AnnuityLoan loan = get(loanId);
		if (loan == null) return;
		remove(loan);
	}

	public AnnuityLoan update(final long loanId, final String name, final double amount, final LocalDate startDate, final double paymentRate,
			final double fixedDebitInterest, final int fixedInterestPeriod, final double estimatedDebitInterest) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to update loan " + loanId + ", " + name + ", " + amount + ", " + startDate + ", " + paymentRate + ", "
					+ fixedDebitInterest + ", " + fixedInterestPeriod + ", " + estimatedDebitInterest);
		}
		if (!isInputValid(name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest)) return null;
		final AnnuityLoan loan = get(loanId);
		if (loan == null) return null;
		final BaseAnnuityLoan annuityLoan = (BaseAnnuityLoan) loan;
		annuityLoan.update(name, amount, startDate, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest);
		return annuityLoan;
	}

	/**
	 * Validate the input.
	 *
	 * @param name
	 *            the name
	 * @param amount
	 *            the amount
	 * @param paymentRate
	 *            the payment rate
	 * @param fixedDebitInterest
	 *            the fixed debit interest
	 * @param fixedInterestPeriod
	 *            the fixed interest period
	 * @param estimatedDebitInterest
	 *            the estimated debit interest
	 * @return <code>true</code> if valid, otherwise false
	 */
	private boolean isInputValid(final String name, final double amount, final double paymentRate, final double fixedDebitInterest,
			final int fixedInterestPeriod, final double estimatedDebitInterest) {
		if (name == null || name.isEmpty()) return false;
		if (amount <= 0) return false;
		if (paymentRate <= 0 || paymentRate >= 100) return false;
		if (fixedDebitInterest < 0 || fixedDebitInterest >= 100) return false;
		if (fixedInterestPeriod < 0) return false;
		if (estimatedDebitInterest < 0 || estimatedDebitInterest >= 100) return false;
		return true;
	}

}
