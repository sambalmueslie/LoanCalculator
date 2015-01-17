/**
 *
 */
package de.sambalmueslie.loan_calculator.controller.file.xml.data;

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
	 * @return the {@link #name}
	 */
	public String getName() {
		return name;
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

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/** the name of the bank. */
	private String bankName;
	/** the id. */
	private long id;
	/** the loan ids. */
	private Set<Long> loanIds;
	/** the name. */
	private String name;
}
