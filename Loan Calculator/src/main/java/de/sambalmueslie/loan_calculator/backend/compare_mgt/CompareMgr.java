/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.compare_mgt;

import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.loan_calculator.backend.common.BaseBusinessObjectMgr;
import de.sambalmueslie.loan_calculator.backend.common.BusinessObject;
import de.sambalmueslie.loan_calculator.backend.common.BusinessObjectModel;
import de.sambalmueslie.loan_calculator.backend.common.BusinessObjectModelListener;
import de.sambalmueslie.loan_calculator.backend.founding_mgt.Founding;
import de.sambalmueslie.loan_calculator.backend.founding_mgt.FoundingMgr;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoanMgr;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreement;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreementMgr;

/**
 * The manager for the {@link Comparison}s.
 *
 * @author sambalmueslie 2015
 */
public class CompareMgr extends BaseBusinessObjectMgr<Comparison<?>> {
	/** the logger. */
	private static final Logger logger = LogManager.getLogger(CompareMgr.class);

	/**
	 * Constructor.
	 */
	public CompareMgr(final AnnuityLoanMgr annuityLoanMgr, final BuildingLoanAgreementMgr buildingLoanAgreementMgr, final FoundingMgr foundingMgr) {
		annuityLoanMgr.register(new BusinessObjectModelListener<AnnuityLoan>() {

			@Override
			public void entryAdded(final BusinessObjectModel<AnnuityLoan> model, final AnnuityLoan entry) {
				// intentionally left empty
			}

			@Override
			public void entryRemoved(final BusinessObjectModel<AnnuityLoan> model, final AnnuityLoan entry) {
				comparisonRemoveEntry(entry, AnnuityLoan.class);
			}
		});
		buildingLoanAgreementMgr.register(new BusinessObjectModelListener<BuildingLoanAgreement>() {

			@Override
			public void entryAdded(final BusinessObjectModel<BuildingLoanAgreement> model, final BuildingLoanAgreement entry) {
				// intentionally left empty
			}

			@Override
			public void entryRemoved(final BusinessObjectModel<BuildingLoanAgreement> model, final BuildingLoanAgreement entry) {
				comparisonRemoveEntry(entry, BuildingLoanAgreement.class);
			}
		});
		foundingMgr.register(new BusinessObjectModelListener<Founding>() {

			@Override
			public void entryAdded(final BusinessObjectModel<Founding> model, final Founding entry) {
				// intentionally left empty
			}

			@Override
			public void entryRemoved(final BusinessObjectModel<Founding> model, final Founding entry) {
				comparisonRemoveEntry(entry, Founding.class);
			}
		});
	}

	public Comparison<Founding> add(final Founding founding) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to add comparison founding " + founding.getId());
		}
		return add(founding, Founding.class);
	}

	public Comparison<Loan> add(final Loan loan) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to add comparison loan " + loan.getId());
		}
		return add(loan, Loan.class);
	}

	public void comparisonAdd(final long comparisonId, final Founding founding) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to remove comparison founding " + comparisonId + ", " + founding.getId());
		}
		comparisonAddEntry(comparisonId, founding, Founding.class);
	}

	public void comparisonAdd(final long comparisonId, final Loan loan) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to add comparison loan " + comparisonId + ", " + loan.getId());
		}
		comparisonAddEntry(comparisonId, loan, Loan.class);
	}

	public void comparisonRemove(final long comparisonId, final Founding founding) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to remove comparison founding " + comparisonId + ", " + founding.getId());
		}
		comparisonRemoveEntry(comparisonId, founding, Founding.class);
	}

	public void comparisonRemove(final long comparisonId, final Loan loan) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to remove comparison loan " + comparisonId + ", " + loan.getId());
		}
		comparisonRemoveEntry(comparisonId, loan, Loan.class);
	}

	public void remove(final long comparisonId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to remove comparison " + comparisonId);
		}
		final Comparison<?> comparison = get(comparisonId);
		if (comparison == null) return;
		remove(comparison);
	}

	private <T extends BusinessObject> Comparison<T> add(final T businessObject, final Class<T> type) {
		final String name = "Comparison-" + getAll().size();
		final BaseComparison<T> comparison = new BaseComparison<>(createNewId(), name, type);
		comparison.add(businessObject);
		add(comparison);
		return comparison;
	}

	/**
	 * Handle {@link Comparison} add entry.
	 *
	 * @param comparisonId
	 *            the comparison id
	 * @param entry
	 *            the entry
	 * @param type
	 *            the entry type
	 */
	@SuppressWarnings("unchecked")
	private <N extends BusinessObject> void comparisonAddEntry(final long comparisonId, final N entry, final Class<N> type) {
		final Comparison<?> comparison = get(comparisonId);
		if (comparison == null || !comparison.getType().equals(type)) return;

		final BaseComparison<N> bc = (BaseComparison<N>) comparison;
		bc.add(entry);
	}

	/**
	 * Handle {@link Comparison} remove entry.
	 *
	 * @param comparisonId
	 *            the comparison id
	 * @param entry
	 *            the entry
	 * @param type
	 *            the entry type
	 */
	@SuppressWarnings("unchecked")
	private <N extends BusinessObject> void comparisonRemoveEntry(final long comparisonId, final N entry, final Class<N> type) {
		final Comparison<?> comparison = get(comparisonId);
		if (comparison == null || !comparison.getType().equals(type)) return;

		final BaseComparison<N> bc = (BaseComparison<N>) comparison;
		bc.remove(entry);

		if (comparison.getElements().isEmpty()) {
			remove(comparison);
		}
	}

	/**
	 * Remove the entry from all comparisons.
	 *
	 * @param entry
	 *            the entry
	 * @param type
	 *            the type
	 */
	@SuppressWarnings("unchecked")
	private <N extends BusinessObject> void comparisonRemoveEntry(final N entry, final Class<N> type) {
		final Collection<Comparison<?>> typeComps = getAll().stream().filter(c -> c.getType().equals(type)).collect(Collectors.toList());
		typeComps.stream().filter(c -> c.getElements().contains(entry)).forEach(c -> ((BaseComparison<N>) c).remove(entry));
	}
}
