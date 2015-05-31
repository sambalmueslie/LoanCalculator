/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import de.sambalmueslie.loan_calculator.backend.common.BusinessObjectModel;
import de.sambalmueslie.loan_calculator.backend.common.BusinessObjectModelListener;
import de.sambalmueslie.loan_calculator.backend.compare_mgt.CompareMgr;
import de.sambalmueslie.loan_calculator.backend.compare_mgt.Comparison;
import de.sambalmueslie.loan_calculator.backend.founding_mgt.Founding;
import de.sambalmueslie.loan_calculator.backend.founding_mgt.FoundingMgr;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoanMgr;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreement;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreementMgr;

/**
 * The base {@link Model}.
 *
 * @author sambalmueslie 2015
 */
public class BaseModel implements Model {

	/**
	 * Constructor.
	 *
	 * @param annuityLoanMgr
	 *            {@link #annuityLoanMgr}
	 * @param buildingLoanAgreementMgr
	 *            {@link #buildingLoanAgreementMgr}
	 * @param foundingMgr
	 *            {@link #foundingMgr}
	 * @param compareMgr
	 *            {@link #compareMgr}
	 */
	public BaseModel(final AnnuityLoanMgr annuityLoanMgr, final BuildingLoanAgreementMgr buildingLoanAgreementMgr, final FoundingMgr foundingMgr,
			final CompareMgr compareMgr) {
		this.annuityLoanMgr = annuityLoanMgr;
		this.buildingLoanAgreementMgr = buildingLoanAgreementMgr;
		this.foundingMgr = foundingMgr;
		this.compareMgr = compareMgr;

		annuityLoanMgr.register(new BusinessObjectModelListener<AnnuityLoan>() {

			@Override
			public void entryAdded(final BusinessObjectModel<AnnuityLoan> model, final AnnuityLoan entry) {
				listeners.forEach(l -> l.annuityLoanAdded(entry));
			}

			@Override
			public void entryRemoved(final BusinessObjectModel<AnnuityLoan> model, final AnnuityLoan entry) {
				listeners.forEach(l -> l.annuityLoanRemoved(entry));
			}
		});

		buildingLoanAgreementMgr.register(new BusinessObjectModelListener<BuildingLoanAgreement>() {

			@Override
			public void entryAdded(final BusinessObjectModel<BuildingLoanAgreement> model, final BuildingLoanAgreement entry) {
				listeners.forEach(l -> l.buildingLoanAgreementAdded(entry));
			}

			@Override
			public void entryRemoved(final BusinessObjectModel<BuildingLoanAgreement> model, final BuildingLoanAgreement entry) {
				listeners.forEach(l -> l.buildingLoanAgreementRemoved(entry));
			}
		});

		foundingMgr.register(new BusinessObjectModelListener<Founding>() {

			@Override
			public void entryAdded(final BusinessObjectModel<Founding> model, final Founding entry) {
				listeners.forEach(l -> l.foundingAdded(entry));
			}

			@Override
			public void entryRemoved(final BusinessObjectModel<Founding> model, final Founding entry) {
				listeners.forEach(l -> l.foundingRemoved(entry));
			}
		});

		compareMgr.register(new BusinessObjectModelListener<Comparison<?>>() {

			@Override
			public void entryAdded(final BusinessObjectModel<Comparison<?>> model, final Comparison<?> entry) {
				listeners.forEach(l -> l.comparisonAdded(entry));
			}

			@Override
			public void entryRemoved(final BusinessObjectModel<Comparison<?>> model, final Comparison<?> entry) {
				listeners.forEach(l -> l.comparisonRemoved(entry));
			}
		});

	}

	/**
	 * Add a {@link AnnuityLoan}.
	 *
	 * @param annuityLoan
	 *            the annuityLoan
	 */
	public void add(final AnnuityLoan annuityLoan) {
		annuityLoanMgr.add(annuityLoan);
	}

	/**
	 * Add a {@link BuildingLoanAgreement}.
	 *
	 * @param buildingLoanAgreement
	 *            the buildingLoanAgreement
	 */
	public void add(final BuildingLoanAgreement buildingLoanAgreement) {
		buildingLoanAgreementMgr.add(buildingLoanAgreement);
	}

	/**
	 * Add a {@link Comparison}.
	 *
	 * @param comparison
	 *            the comparison
	 */
	public void add(final Comparison<?> comparison) {
		compareMgr.add(comparison);
	}

	/**
	 * Add a {@link Founding}.
	 *
	 * @param founding
	 *            the founding
	 */
	public void add(final Founding founding) {
		foundingMgr.add(founding);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.model.Model#getAnnuityLoan(long)
	 */
	@Override
	public AnnuityLoan getAnnuityLoan(final long id) {
		return annuityLoanMgr.get(id);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.model.Model#getAnnuityLoans()
	 */
	@Override
	public Collection<AnnuityLoan> getAnnuityLoans() {
		return annuityLoanMgr.getAll();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.model.Model#getBuildingLoanAgreement(long)
	 */
	@Override
	public BuildingLoanAgreement getBuildingLoanAgreement(final long id) {
		return buildingLoanAgreementMgr.get(id);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.model.Model#getBuildingLoanAgreements()
	 */
	@Override
	public Collection<BuildingLoanAgreement> getBuildingLoanAgreements() {
		return buildingLoanAgreementMgr.getAll();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.model.Model#getComparison(long)
	 */
	@Override
	public Comparison<?> getComparison(final long id) {
		return compareMgr.get(id);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.model.Model#getComparisons()
	 */
	@Override
	public Collection<Comparison<?>> getComparisons() {
		return compareMgr.getAll();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.model.Model#getFounding(long)
	 */
	@Override
	public Founding getFounding(final long id) {
		return foundingMgr.get(id);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.model.Model#getFoundings()
	 */
	@Override
	public Collection<Founding> getFoundings() {
		return foundingMgr.getAll();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.model.Model#getLoan(long)
	 */
	@Override
	public Loan getLoan(final long id) {
		final Loan loan = annuityLoanMgr.get(id);
		if (loan != null) {
			return loan;
		}
		return buildingLoanAgreementMgr.get(id);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.model.Model#getLoans()
	 */
	@Override
	public Collection<Loan> getLoans() {
		final LinkedList<Loan> loans = new LinkedList<>(getAnnuityLoans());
		loans.addAll(getBuildingLoanAgreements());
		return loans;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.model.Model#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return annuityLoanMgr.isEmpty() && buildingLoanAgreementMgr.isEmpty() && foundingMgr.isEmpty() && compareMgr.isEmpty();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.Model#listenerRegister(de.sambalmueslie.loan_calculator.model.ModelChangeListener)
	 */
	@Override
	public void listenerRegister(final ModelChangeListener listener) {
		if (listener != null && !listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.Model#listenerUnregister(de.sambalmueslie.loan_calculator.model.ModelChangeListener)
	 */
	@Override
	public void listenerUnregister(final ModelChangeListener listener) {
		if (listener != null) {
			listeners.remove(listener);
		}
	}

	/** the {@link AnnuityLoanMgr}. */
	private final AnnuityLoanMgr annuityLoanMgr;
	/** the {@link BuildingLoanAgreementMgr}. */
	private final BuildingLoanAgreementMgr buildingLoanAgreementMgr;
	/** the {@link CompareMgr}. */
	private final CompareMgr compareMgr;
	/** the {@link FoundingMgr}. */
	private final FoundingMgr foundingMgr;
	/** the {@link ModelChangeListener}. */
	private final List<ModelChangeListener> listeners = new LinkedList<>();

}
