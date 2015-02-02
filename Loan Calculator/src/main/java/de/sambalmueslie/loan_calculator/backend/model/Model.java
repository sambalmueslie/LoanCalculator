/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.model;

import java.util.Collection;

import de.sambalmueslie.loan_calculator.backend.compare_mgt.Comparison;
import de.sambalmueslie.loan_calculator.backend.founding_mgt.Founding;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreement;

/**
 * The model.
 *
 * @author sambalmueslie 2015
 */
public interface Model {

	/**
	 * Get a {@link AnnuityLoan} by id.
	 *
	 * @param id
	 *            the id
	 * @return the annuity loan or <code>null</code> if not found
	 */
	AnnuityLoan getAnnuityLoan(final long id);

	/**
	 * @return a {@link Collection} of all currently stored {@link AnnuityLoan}s.
	 */
	Collection<AnnuityLoan> getAnnuityLoans();

	/**
	 * Get a {@link BuildingLoanAgreement} by id.
	 *
	 * @param id
	 *            the id
	 * @return the building loan agreement or <code>null</code> if not found
	 */
	BuildingLoanAgreement getBuildingLoanAgreement(final long id);

	/**
	 * @return a {@link Collection} of all currently stored {@link BuildingLoanAgreement}s.
	 */
	Collection<BuildingLoanAgreement> getBuildingLoanAgreements();

	/**
	 * Get a {@link Comparison} by id.
	 *
	 * @param id
	 *            the id
	 * @return the comparison or <code>null</code> if not found
	 */
	Comparison<?> getComparison(final long id);

	/**
	 * @return a {@link Collection} of all currently stored {@link Comparison}s.
	 */
	Collection<Comparison<?>> getComparisons();

	/**
	 * Get a {@link Founding} by id.
	 *
	 * @param id
	 *            the id
	 * @return the founding or <code>null</code> if not found
	 */
	Founding getFounding(final long id);

	/**
	 * @return a {@link Collection} of all currently stored {@link Founding}s.
	 */
	Collection<Founding> getFoundings();

	/**
	 * Get a {@link Loan} by id.
	 *
	 * @param id
	 *            the id
	 * @return the loan or <code>null</code> if not found
	 */
	Loan getLoan(long id);

	/**
	 * @return the {@link Collection} of {@link Loan}s.
	 */
	Collection<Loan> getLoans();

	/**
	 * @return <code>true</code> if the model is empty, otherwise <code>false</code>.
	 */
	boolean isEmpty();

	/**
	 * Register a {@link ModelChangeListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	void listenerRegister(final ModelChangeListener listener);

	/**
	 * Unregister a {@link ModelChangeListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	void listenerUnregister(final ModelChangeListener listener);

}
