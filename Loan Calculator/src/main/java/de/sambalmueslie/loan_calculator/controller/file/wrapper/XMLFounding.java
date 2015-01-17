/**
 *
 */
package de.sambalmueslie.loan_calculator.controller.file.wrapper;

import java.util.Set;

/**
 * @author sambalmueslie 2015
 */
public class XMLFounding {
	/**
	 * @return the {@link #bankName}
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @return the {@link #id}
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the {@link #loanIds}
	 */
	public Set<Long> getLoanIds() {
		return loanIds;
	}

	/**
	 * @param bankName
	 *            the bankName to set
	 */
	public void setBankName(final String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final long id) {
		this.id = id;
	}

	/**
	 * @param loanIds
	 *            the loanIds to set
	 */
	public void setLoanIds(final Set<Long> loanIds) {
		this.loanIds = loanIds;
	}

	/** the name of the bank. */
	private String bankName;
	/** the id. */
	private long id;
	/** the loan ids. */
	private Set<Long> loanIds;
}
