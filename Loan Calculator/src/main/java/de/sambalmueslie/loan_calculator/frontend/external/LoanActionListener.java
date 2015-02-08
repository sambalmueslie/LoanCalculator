/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.external;

/**
 * The loan action listener.
 *
 * @author sambalmueslie 2015
 */
public interface LoanActionListener {

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

}
