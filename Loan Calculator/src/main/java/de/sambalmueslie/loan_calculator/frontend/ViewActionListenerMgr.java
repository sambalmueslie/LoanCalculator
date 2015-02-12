/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend;

import java.util.LinkedList;
import java.util.List;

import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoanSettings;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreementSettings;
import de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener;

/**
 * The {@link ViewActionListenerMgr}.
 *
 * @author sambalmueslie 2015
 */
public class ViewActionListenerMgr implements ViewActionListener {

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.external.LoanActionListener#requestAddAnnuityLoan(de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoanSettings)
	 */
	@Override
	public void requestAddAnnuityLoan(final AnnuityLoanSettings settings) {
		listeners.forEach(l -> l.requestAddAnnuityLoan(settings));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.external.LoanActionListener#requestAddBuildingLoanAgreement(de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreementSettings)
	 */
	@Override
	public void requestAddBuildingLoanAgreement(final BuildingLoanAgreementSettings settings) {
		listeners.forEach(l -> l.requestAddBuildingLoanAgreement(settings));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener#requestAddComparisonFounding(long)
	 */
	@Override
	public void requestAddComparisonFounding(final long foundingId) {
		listeners.forEach(l -> l.requestAddComparisonFounding(foundingId));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener#requestAddComparisonLoan(long)
	 */
	@Override
	public void requestAddComparisonLoan(final long loanId) {
		listeners.forEach(l -> l.requestAddComparisonLoan(loanId));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener#requestAddFounding(java.lang.String, java.lang.String)
	 */
	@Override
	public void requestAddFounding(final String name, final String bankName) {
		listeners.forEach(l -> l.requestAddFounding(name, bankName));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener#requestComparisonAddFounding(long, long)
	 */
	@Override
	public void requestComparisonAddFounding(final long comparisonId, final long foundingId) {
		listeners.forEach(l -> l.requestComparisonAddFounding(comparisonId, foundingId));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener#requestComparisonAddLoan(long, long)
	 */
	@Override
	public void requestComparisonAddLoan(final long comparisonId, final long loanId) {
		listeners.forEach(l -> l.requestComparisonAddLoan(comparisonId, loanId));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener#requestComparisonRemoveFounding(long, long)
	 */
	@Override
	public void requestComparisonRemoveFounding(final long comparisonId, final long foundingId) {
		listeners.forEach(l -> l.requestComparisonRemoveFounding(comparisonId, foundingId));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener#requestComparisonRemoveLoan(long, long)
	 */
	@Override
	public void requestComparisonRemoveLoan(final long comparisonId, final long loanId) {
		listeners.forEach(l -> l.requestComparisonRemoveLoan(comparisonId, loanId));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener#requestFileNew()
	 */
	@Override
	public void requestFileNew() {
		listeners.forEach(l -> l.requestFileNew());
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener#requestFileOpen()
	 */
	@Override
	public void requestFileOpen() {
		listeners.forEach(l -> l.requestFileOpen());
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener#requestFileSave()
	 */
	@Override
	public void requestFileSave() {
		listeners.forEach(l -> l.requestFileSave());
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener#requestFoundingAddLoan(long, long)
	 */
	@Override
	public void requestFoundingAddLoan(final long foundingId, final long loanId) {
		listeners.forEach(l -> l.requestFoundingAddLoan(foundingId, loanId));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener#requestFoundingRemoveLoan(long, long)
	 */
	@Override
	public void requestFoundingRemoveLoan(final long foundingId, final long loanId) {
		listeners.forEach(l -> l.requestFoundingRemoveLoan(foundingId, loanId));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener#requestProgrammExit()
	 */
	@Override
	public void requestProgrammExit() {
		listeners.forEach(l -> l.requestProgrammExit());
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener#requestRemoveComparison(long)
	 */
	@Override
	public void requestRemoveComparison(final long comparisonId) {
		listeners.forEach(l -> l.requestRemoveComparison(comparisonId));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener#requestRemoveFounding(long)
	 */
	@Override
	public void requestRemoveFounding(final long foundingId) {
		listeners.forEach(l -> l.requestRemoveFounding(foundingId));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener#requestRemoveLoan(long)
	 */
	@Override
	public void requestRemoveLoan(final long loanId) {
		listeners.forEach(l -> l.requestRemoveLoan(loanId));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.external.LoanActionListener#requestUpdateAnnuityLoan(long,
	 *      de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoanSettings)
	 */
	@Override
	public void requestUpdateAnnuityLoan(final long loanId, final AnnuityLoanSettings settings) {
		listeners.forEach(l -> l.requestUpdateAnnuityLoan(loanId, settings));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.external.LoanActionListener#requestUpdateBuildingLoanAgreement(long,
	 *      de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreementSettings)
	 */
	@Override
	public void requestUpdateBuildingLoanAgreement(final long loanId, final BuildingLoanAgreementSettings settings) {
		listeners.forEach(l -> l.requestUpdateBuildingLoanAgreement(loanId, settings));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener#requestUpdateFounding(long, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public void requestUpdateFounding(final long foundingId, final String name, final String bankName) {
		listeners.forEach(l -> l.requestAddFounding(name, bankName));
	}

	/**
	 * Register a {@link ViewActionListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	void register(final ViewActionListener listener) {
		if (listener != null && !listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	/**
	 * Unregister a {@link ViewActionListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	void unregister(final ViewActionListener listener) {
		if (listener != null) {
			listeners.remove(listener);
		}
	}

	/** the {@link ViewActionListener}. */
	private final List<ViewActionListener> listeners = new LinkedList<>();
}
