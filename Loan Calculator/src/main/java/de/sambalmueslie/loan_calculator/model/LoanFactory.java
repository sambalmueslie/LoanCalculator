/**
 *
 */
package de.sambalmueslie.loan_calculator.model;

/**
 * The factory for the {@link Loan}.
 *
 * @author sambalmueslie 2015
 */
public final class LoanFactory {

	/**
	 * Create a annuity {@link Loan}.
	 *
	 * @param title
	 *            a unique title
	 * @param amount
	 *            the total amount (Finanzmittel)
	 * @param paymentRate
	 *            the payment rate (Tilgung in Prozent)
	 * @param fixedDebitInterest
	 *            the fixed debit interest (Gebundener Sollzins)
	 * @param fixedInterestPeriod
	 *            the fixed debit interest period (Sollzinsbindung)
	 * @param estimatedDebitInterest
	 *            the estimated debit interest (geschätzter Sollzins nach Bindungsende)
	 * @return the {@link Loan}
	 */
	public static Loan createAnnuityLoan(final String title, final double amount, final double paymentRate, final double fixedDebitInterest,
			final int fixedInterestPeriod, final double estimatedDebitInterest) {
		return new AnnuityLoan(title, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest);
	}

	/**
	 * Constructor.
	 */
	private LoanFactory() {
		// intentionally left empty
	}

}
