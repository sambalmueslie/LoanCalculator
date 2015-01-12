/**
 *
 */
package de.sambalmueslie.loan_calculator.view;

import java.util.LinkedList;
import java.util.List;

/**
 * The {@link ViewActionListenerMgr}.
 *
 * @author sambalmueslie 2015
 */
public class ViewActionListenerMgr implements ViewActionListener {
	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestAddAnnuityLoan(java.lang.String, double, double, double, int,
	 *      double)
	 */
	@Override
	public void requestAddAnnuityLoan(final String name, final double amount, final double paymentRate, final double fixedDebitInterest,
			final int fixedInterestPeriod, final double estimatedDebitInterest) {
		listeners.forEach(l -> l.requestAddAnnuityLoan(name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestAddFounding(java.lang.String, java.lang.String)
	 */
	@Override
	public void requestAddFounding(final String name, final String bankName) {
		listeners.forEach(l -> l.requestAddFounding(name, bankName));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestAddComparisonFounding(long)
	 */
	@Override
	public void requestAddComparisonFounding(final long foundingId) {
		listeners.forEach(l -> l.requestAddComparisonFounding(foundingId));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestAddComparisonLoan(long)
	 */
	@Override
	public void requestAddComparisonLoan(final long loanId) {
		listeners.forEach(l -> l.requestAddComparisonLoan(loanId));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestComparisonAddFounding(long, long)
	 */
	@Override
	public void requestComparisonAddFounding(final long comparisonId, final long foundingId) {
		listeners.forEach(l -> l.requestComparisonAddFounding(comparisonId, foundingId));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestComparisonAddLoan(long, long)
	 */
	@Override
	public void requestComparisonAddLoan(final long comparisonId, final long loanId) {
		listeners.forEach(l -> l.requestComparisonAddLoan(comparisonId, loanId));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestComparisonRemoveFounding(long, long)
	 */
	@Override
	public void requestComparisonRemoveFounding(final long comparisonId, final long foundingId) {
		listeners.forEach(l -> l.requestComparisonRemoveFounding(comparisonId, foundingId));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestComparisonRemoveLoan(long, long)
	 */
	@Override
	public void requestComparisonRemoveLoan(final long comparisonId, final long loanId) {
		listeners.forEach(l -> l.requestComparisonRemoveLoan(comparisonId, loanId));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestFoundingAddLoan(long, long)
	 */
	@Override
	public void requestFoundingAddLoan(final long foundingId, final long loanId) {
		listeners.forEach(l -> l.requestFoundingAddLoan(foundingId, loanId));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestFoundingRemoveLoan(long, long)
	 */
	@Override
	public void requestFoundingRemoveLoan(final long foundingId, final long loanId) {
		listeners.forEach(l -> l.requestFoundingRemoveLoan(foundingId, loanId));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestRemoveComparison(long)
	 */
	@Override
	public void requestRemoveComparison(final long comparisonId) {
		listeners.forEach(l -> l.requestRemoveComparison(comparisonId));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestRemoveFounding(long)
	 */
	@Override
	public void requestRemoveFounding(final long foundingId) {
		listeners.forEach(l -> l.requestRemoveFounding(foundingId));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestRemoveLoan(long)
	 */
	@Override
	public void requestRemoveLoan(final long loanId) {
		listeners.forEach(l -> l.requestRemoveLoan(loanId));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestUpdateAnnuityLoan(long, java.lang.String, double, double,
	 *      double, int, double)
	 */
	@Override
	public void requestUpdateAnnuityLoan(final long loanId, final String name, final double amount, final double paymentRate, final double fixedDebitInterest,
			final int fixedInterestPeriod, final double estimatedDebitInterest) {
		listeners.forEach(l -> l.requestUpdateAnnuityLoan(loanId, name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.ViewActionListener#requestUpdateFounding(long, java.lang.String, java.lang.String)
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
