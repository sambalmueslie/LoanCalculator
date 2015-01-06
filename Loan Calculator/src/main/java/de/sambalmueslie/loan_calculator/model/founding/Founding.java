/**
 *
 */
package de.sambalmueslie.loan_calculator.model.founding;

import java.util.List;

import de.sambalmueslie.loan_calculator.model.loan.RedemptionPlanEntry;

/**
 * A founding, group several loans together.
 *
 * @author sambalmueslie 2015
 */
public interface Founding {
	/**
	 * @return the amount of the loan.
	 */
	double getAmount();

	/**
	 * @return the bank name.
	 */
	String getBankName();

	/**
	 * @return the id.
	 */
	long getId();

	/**
	 * @return the name.
	 */
	String getName();

	/**
	 * @return the redemption plan.
	 */
	List<RedemptionPlanEntry> getRedemptionPlan();

	/**
	 * @return the term in months.
	 */
	int getTerm();

	/**
	 * @return the total interest to pay.
	 */
	double getTotalInterest();

	/**
	 * @return the total payment (interest and amount).
	 */
	double getTotalPayment();

	/**
	 * Register a {@link FoundingChangeListener}.
	 * 
	 * @param listener
	 *            the listener
	 */
	void register(FoundingChangeListener listener);

	/**
	 * Unregister a {@link FoundingChangeListener}.
	 * 
	 * @param listener
	 *            the listener
	 */
	void unregister(FoundingChangeListener listener);

}
