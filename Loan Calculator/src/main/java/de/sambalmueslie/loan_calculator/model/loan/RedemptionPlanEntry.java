/**
 *
 */
package de.sambalmueslie.loan_calculator.model.loan;

/**
 * A redemption plan entry.
 * 
 * @author sambalmueslie 2015
 */
public interface RedemptionPlanEntry {

	/**
	 * @return the interest
	 */
	double getInterest();

	/**
	 * @return the redemption
	 */
	double getRedemption();

	/**
	 * @return the residual debt.
	 */
	double getResidualDebt();

}
