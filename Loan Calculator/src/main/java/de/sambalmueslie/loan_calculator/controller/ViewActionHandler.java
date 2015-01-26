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
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestAddBuildingLoanAgreement(String, double, double, double, double,
	 *      int, double, double, double, double)
	 */
	@Override
	public void requestAddBuildingLoanAgreement(final String name, final double amount, final double creditInterest, final double regularSavingAmount,
			final double minimumSavings, final int savingDuration, final double savingPhaseInterest, final double debitInterest, final double contribution,
			final double aquisitonFee) {
		controller.handleRequestAddBuildingLoanAgreement(name, amount, creditInterest, regularSavingAmount, minimumSavings, savingDuration,
				savingPhaseInterest, debitInterest, contribution, aquisitonFee);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestAddComparisonFounding(long)
	 */
	@Override
	public void requestAddComparisonFounding(final long foundingId) {
		controller.handleRequestAddComparisonFounding(foundingId);

	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestAddComparisonLoan(long)
	 */
	@Override
	public void requestAddComparisonLoan(final long loanId) {
		controller.handleRequestAddComparisonLoan(loanId);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestAddFounding(java.lang.String, java.lang.String)
	 */
	@Override
	public void requestAddFounding(final String name, final String bankName) {
		controller.handleRequestAddFounding(name, bankName);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestComparisonAddFounding(long, long)
	 */
	@Override
	public void requestComparisonAddFounding(final long comparisonId, final long foundingId) {
		controller.handleRequestComparisonAddFounding(comparisonId, foundingId);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestComparisonAddLoan(long, long)
	 */
	@Override
	public void requestComparisonAddLoan(final long comparisonId, final long loanId) {
		controller.handleRequestComparisonAddLoan(comparisonId, loanId);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestComparisonRemoveFounding(long, long)
	 */
	@Override
	public void requestComparisonRemoveFounding(final long comparisonId, final long foundingId) {
		controller.handleRequestComparisonRemoveFounding(comparisonId, foundingId);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestComparisonRemoveLoan(long, long)
	 */
	@Override
	public void requestComparisonRemoveLoan(final long comparisonId, final long loanId) {
		controller.handleRequestComparisonRemoveLoan(comparisonId, loanId);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestFileNew()
	 */
	@Override
	public void requestFileNew() {
		controller.handleRequestFileNew();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestFileOpen()
	 */
	@Override
	public void requestFileOpen() {
		controller.handleRequestFileOpen();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestFileSave()
	 */
	@Override
	public void requestFileSave() {
		controller.handleRequestFileSave();
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
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestProgrammExit()
	 */
	@Override
	public void requestProgrammExit() {
		controller.handleRequestProgrammExit();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestRemoveComparison(long)
	 */
	@Override
	public void requestRemoveComparison(final long comparisonId) {
		controller.handleRequestRemoveComparison(comparisonId);
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
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestUpdateBuildingLoanAgreement(long, java.lang.String, double,
	 *      double, double, double, int, double, double, double, double)
	 */
	@Override
	public void requestUpdateBuildingLoanAgreement(final long loanId, final String name, final double amount, final double creditInterest,
			final double regularSavingAmount, final double minimumSavings, final int savingDuration, final double savingPhaseInterest,
			final double debitInterest, final double contribution, final double aquisitonFee) {
		controller.handleRequestUpdateBuildingLoanAgreement(loanId, name, amount, creditInterest, regularSavingAmount, minimumSavings, savingDuration,
				savingPhaseInterest, debitInterest, contribution, aquisitonFee);
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
