/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.external;

/**
 * @author sambalmueslie 2015
 */
public interface FoundingActionListener {

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
	 * Request to remove a founding.
	 *
	 * @param foundingId
	 *            the founding id
	 */
	void requestRemoveFounding(long foundingId);

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
