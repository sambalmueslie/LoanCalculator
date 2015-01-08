/**
 *
 */
package de.sambalmueslie.loan_calculator.controller;

import de.sambalmueslie.loan_calculator.view.ViewActionListener;

/**
 * The handler for the {@link ViewActionListener}.
 *
 * @author sambalmueslie 2015
 */
public class ViewActionHandler implements ViewActionListener {

	/**
	 * Constructor.
	 *
	 * @param controller
	 *            {@link #controller}
	 */
	ViewActionHandler(final Controller controller) {
		this.controller = controller;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestAddAnnuityLoan(java.lang.String, double, double, double, int,
	 *      double)
	 */
	@Override
	public void requestAddAnnuityLoan(final String name, final double amount, final double paymentRate, final double fixedDebitInterest,
			final int fixedInterestPeriod, final double estimatedDebitInterest) {
		controller.handleRequestAddAnnuityLoan(name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestAddFounding(java.lang.String, java.lang.String)
	 */
	@Override
	public void requestAddFounding(final String name, final String bankName) {
		controller.handleRequestAddFounding(name, bankName);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestFoundingAddLoan(long, long)
	 */
	@Override
	public void requestFoundingAddLoan(final long foundingId, final long loanId) {
		controller.handleRequestFoundingAddLoan(foundingId, loanId);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestFoundingRemoveLoan(long, long)
	 */
	@Override
	public void requestFoundingRemoveLoan(final long foundingId, final long loanId) {
		controller.handleRequestFoundingRemoveLoan(foundingId, loanId);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestRemoveFounding(long)
	 */
	@Override
	public void requestRemoveFounding(final long foundingId) {
		controller.handleRequestRemoveFounding(foundingId);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestRemoveLoan(long)
	 */
	@Override
	public void requestRemoveLoan(final long loanId) {
		controller.handleRequestRemoveLoan(loanId);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestUpdateAnnuityLoan(long, java.lang.String, double, double,
	 *      double, int, double)
	 */
	@Override
	public void requestUpdateAnnuityLoan(final long loanId, final String name, final double amount, final double paymentRate, final double fixedDebitInterest,
			final int fixedInterestPeriod, final double estimatedDebitInterest) {
		controller.handleRequestUpdateAnnuityLoan(loanId, name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestUpdateFounding(long, java.lang.String, java.lang.String)
	 */
	@Override
	public void requestUpdateFounding(final long foundingId, final String name, final String bankName) {
		controller.handleRequestUpdateFounding(foundingId, name, bankName);
	}

	/** the {@link Controller}. */
	private final Controller controller;
}
