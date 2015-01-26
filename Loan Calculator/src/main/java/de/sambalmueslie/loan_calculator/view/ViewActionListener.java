/**
 *
 */
package de.sambalmueslie.loan_calculator.view;

import de.sambalmueslie.loan_calculator.model.compare.Comparison;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.loan.Loan;

/**
 * The view action listener.
 *
 * @author sambalmueslie 2015
 */
public interface ViewActionListener {

	/**
	 * Request to add a loan.
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
	 */
	void requestAddAnnuityLoan(final String name, final double amount, final double paymentRate, final double fixedDebitInterest,
			final int fixedInterestPeriod, final double estimatedDebitInterest);

	/**
	 * Request to add a building loan agreement.
	 *
	 * @param name
	 *            the name
	 * @param amount
	 *            the amount
	 * @param creditInterest
	 *            the credit interest (guthabenszins).
	 * @param regularSavingAmount
	 *            the regular saving amount. (monatlicher regelsparbetrag in promille).
	 * @param minimumSavings
	 *            the minimum savings (mindestsparguthaben in prozent).
	 * @param savingDuration
	 *            the saving duration (spardauer).
	 * @param savingPhaseInterest
	 *            the interest to pay for getting the money, while beeing in saving phase (zins für uebergangsdarlehen).
	 * @param debitInterest
	 *            the debit interest (sollzins ab zuteilung).
	 * @param contribution
	 *            the contribution. (zins und tilgungsbeitrag mtl in promille).
	 * @param aquisitonFee
	 *            the aquisition fee (abschlussgebuehr in prozent).
	 */
	void requestAddBuildingLoanAgreement(String name, double amount, final double creditInterest, final double regularSavingAmount,
			final double minimumSavings, final int savingDuration, final double savingPhaseInterest, final double debitInterest, final double contribution,
			final double aquisitonFee);

	/**
	 * Request to add a loan comparison.
	 *
	 * @param foundingId
	 *            the initial founding id
	 */
	void requestAddComparisonFounding(long foundingId);

	/**
	 * Request to add a loan comparison.
	 *
	 * @param loanId
	 *            the initial loan id
	 */
	void requestAddComparisonLoan(long loanId);

	/**
	 * Request to add a new founding.
	 *
	 * @param name
	 *            the name
	 * @param bankName
	 *            the bank name
	 */
	void requestAddFounding(String name, String bankName);

	/**
	 * Request to add a {@link Founding} to a {@link Comparison}.
	 *
	 * @param comparisonId
	 *            the comparison id
	 * @param foundingId
	 *            the founding id
	 */
	void requestComparisonAddFounding(long comparisonId, long foundingId);

	/**
	 * Request to add a {@link Loan} to a {@link Comparison}.
	 *
	 * @param comparisonId
	 *            the comparison id
	 * @param loanId
	 *            the loan id
	 */
	void requestComparisonAddLoan(long comparisonId, long loanId);

	/**
	 * Request to remove a {@link Founding} to a {@link Comparison}.
	 *
	 * @param comparisonId
	 *            the comparison id
	 * @param foundingId
	 *            the founding id
	 */
	void requestComparisonRemoveFounding(long comparisonId, long foundingId);

	/**
	 * Request to remove a {@link Loan} to a {@link Comparison}.
	 *
	 * @param comparisonId
	 *            the comparison id
	 * @param loanId
	 *            the loan id
	 */
	void requestComparisonRemoveLoan(long comparisonId, long loanId);

	/**
	 * Create a new file.
	 */
	void requestFileNew();

	/**
	 * Open a file.
	 */
	void requestFileOpen();

	/**
	 * Save a file.
	 */
	void requestFileSave();

	/**
	 * Request to add a loan to a founding.
	 *
	 * @param foundingId
	 *            the founding id
	 * @param loanId
	 *            the loan id to add
	 */
	void requestFoundingAddLoan(long foundingId, long loanId);

	/**
	 * Request to remove a loan from a founding.
	 *
	 * @param foundingId
	 *            the founding id
	 * @param loanId
	 *            the loan id to add
	 */
	void requestFoundingRemoveLoan(long foundingId, long loanId);

	/**
	 * Request to exit the programm.
	 */
	void requestProgrammExit();

	/**
	 * Request to remove a comparison.
	 *
	 * @param comparisonId
	 *            the comparison id
	 */
	void requestRemoveComparison(long comparisonId);

	/**
	 * Request to remove a founding.
	 *
	 * @param foundingId
	 *            the founding id
	 */
	void requestRemoveFounding(long foundingId);

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
	 */
	void requestUpdateAnnuityLoan(long loanId, final String name, final double amount, final double paymentRate, final double fixedDebitInterest,
			final int fixedInterestPeriod, final double estimatedDebitInterest);

	/**
	 * Request to update a building loan agreement.
	 *
	 * @param loanId
	 *            the loan id
	 * @param name
	 *            the name
	 * @param amount
	 *            the amount
	 * @param creditInterest
	 *            the credit interest (guthabenszins).
	 * @param regularSavingAmount
	 *            the regular saving amount. (monatlicher regelsparbetrag in promille).
	 * @param minimumSavings
	 *            the minimum savings (mindestsparguthaben in prozent).
	 * @param savingDuration
	 *            the saving duration (spardauer).
	 * @param savingPhaseInterest
	 *            the interest to pay for getting the money, while beeing in saving phase (zins für uebergangsdarlehen).
	 * @param debitInterest
	 *            the debit interest (sollzins ab zuteilung).
	 * @param contribution
	 *            the contribution. (zins und tilgungsbeitrag mtl in promille).
	 * @param aquisitonFee
	 *            the aquisition fee (abschlussgebuehr in prozent).
	 */
	void requestUpdateBuildingLoanAgreement(long loanId, String name, double amount, final double creditInterest, final double regularSavingAmount,
			final double minimumSavings, final int savingDuration, final double savingPhaseInterest, final double debitInterest, final double contribution,
			final double aquisitonFee);

	/**
	 * Request to update a founding.
	 *
	 * @param foundingId
	 *            the founding id
	 * @param name
	 *            the name
	 * @param bankName
	 *            the bank name
	 */
	void requestUpdateFounding(long foundingId, String name, String bankName);

}
