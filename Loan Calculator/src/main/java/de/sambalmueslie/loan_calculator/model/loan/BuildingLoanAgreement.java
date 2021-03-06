/**
 *
 */
package de.sambalmueslie.loan_calculator.model.loan;

import java.util.List;

/**
 * A building loan agreement.
 *
 * @author sambalmueslie 2015
 */
public interface BuildingLoanAgreement extends Loan {
	/**
	 * @return the aquisition fee (abschlussgebuehr in prozent).
	 */
	double getAquisitonFee();

	/**
	 * @return the contribution. (zins und tilgungsbeitrag mtl in promille).
	 */
	double getContribution();

	/**
	 * @return the credit interest (guthabenszins).
	 */
	double getCreditInterest();

	/**
	 * @return the debit interest (sollzins ab zuteilung).
	 */
	double getDebitInterest();

	/**
	 * @return the minimum savings (mindestsparguthaben in prozent).
	 */
	double getMinimumSavings();

	/**
	 * @return the regular saving amount. (monatlicher regelsparbetrag in promille).
	 */
	double getRegularSavingAmount();

	/**
	 * @return the saving duration (spardauer).
	 */
	int getSavingDuration();

	/**
	 * @return the interest to pay for getting the money, while beeing in saving phase (zins f�r uebergangsdarlehen).
	 */
	double getSavingPhaseInterest();

	/**
	 * @return the saving phase plan.
	 */
	List<RedemptionPlanEntry> getSavingPhasePlan();
}
