/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.external;

import de.sambalmueslie.loan_calculator.backend.founding_mgt.Founding;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;

/**
 * The comparison action listener.
 *
 * @author sambalmueslie 2015
 */
public interface ComparisonActionListener {

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
	 * Request to remove a comparison.
	 *
	 * @param comparisonId
	 *            the comparison id
	 */
	void requestRemoveComparison(long comparisonId);

}
