/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.founding_mgt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.loan_calculator.backend.common.BaseBusinessObjectMgr;
import de.sambalmueslie.loan_calculator.backend.common.BusinessObjectModel;
import de.sambalmueslie.loan_calculator.backend.common.BusinessObjectModelListener;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoanMgr;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreement;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreementMgr;

/**
 * The {@link BaseBusinessObjectMgr} for the {@link Founding}.
 *
 * @author sambalmueslie 2015
 */
public class FoundingMgr extends BaseBusinessObjectMgr<Founding> {
	/** the logger. */
	private static final Logger logger = LogManager.getLogger(FoundingMgr.class);

	/**
	 * Constructor.
	 *
	 * @param annuityLoanMgr
	 *            {@link #annuityLoanMgr}
	 * @param buildingLoanAgreementMgr
	 *            {@link #buildingLoanAgreementMgr}
	 */
	public FoundingMgr(final AnnuityLoanMgr annuityLoanMgr, final BuildingLoanAgreementMgr buildingLoanAgreementMgr) {
		this.annuityLoanMgr = annuityLoanMgr;
		this.buildingLoanAgreementMgr = buildingLoanAgreementMgr;

		annuityLoanMgr.register(new BusinessObjectModelListener<AnnuityLoan>() {

			@Override
			public void entryAdded(final BusinessObjectModel<AnnuityLoan> model, final AnnuityLoan entry) {
				// intentionally left empty
			}

			@Override
			public void entryRemoved(final BusinessObjectModel<AnnuityLoan> model, final AnnuityLoan entry) {
				foundingsRemoveLoan(entry);
			}
		});

		buildingLoanAgreementMgr.register(new BusinessObjectModelListener<BuildingLoanAgreement>() {

			@Override
			public void entryAdded(final BusinessObjectModel<BuildingLoanAgreement> model, final BuildingLoanAgreement entry) {
				// intentionally left empty
			}

			@Override
			public void entryRemoved(final BusinessObjectModel<BuildingLoanAgreement> model, final BuildingLoanAgreement entry) {
				foundingsRemoveLoan(entry);
			}
		});
	}

	public Founding add(final String name, final String bankName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to add founding " + name + ", " + bankName);
		}
		if (!isInputValid(name, bankName)) return null;
		final BaseFounding founding = new BaseFounding(createNewId(), name, bankName);
		add(founding);
		return founding;
	}

	public void addLoan(final long foundingId, final Loan loan) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to founding add loan " + foundingId + ", " + loan.getId());
		}
		final BaseFounding founding = (BaseFounding) get(foundingId);
		if (founding == null || loan == null) return;
		founding.add(loan);
		// remove from other foundings
		getAll().stream().filter(f -> f.getLoans().contains(loan)).filter(f -> f != founding).forEach(f -> ((BaseFounding) f).remove(loan));
	}

	public void remove(final long foundingId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to remove founding " + foundingId);
		}
		final Founding founding = get(foundingId);
		if (founding == null) return;
		remove(founding);
		removeAssociatedLoans(founding);
	}

	public void removeLoan(final long foundingId, final Loan loan) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to founding remove loan " + foundingId + ", " + loan.getId());
		}
		final BaseFounding founding = (BaseFounding) get(foundingId);
		if (founding == null || loan == null) return;
		founding.remove(loan);
	}

	public void update(final long foundingId, final String name, final String bankName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to update founding " + foundingId + ", " + name + ", " + bankName);
		}
		if (!isInputValid(name, bankName)) return;
		final BaseFounding founding = (BaseFounding) get(foundingId);
		if (founding == null) return;
		founding.update(name, bankName);
	}

	/**
	 * Remove the {@link Loan} from all {@link Founding}s
	 *
	 * @param entry
	 *            the entry
	 */
	private void foundingsRemoveLoan(final Loan entry) {
		getAll().stream().filter(f -> f.getLoans().contains(entry)).forEach(f -> ((BaseFounding) f).remove(entry));
	}

	private boolean isInputValid(final String name, final String bankName) {
		if (name == null || name.isEmpty()) return false;
		if (bankName == null || bankName.isEmpty()) return false;
		return true;
	}

	private void removeAssociatedLoans(final Founding founding) {
		founding.getLoans().forEach(l -> annuityLoanMgr.remove(l.getId()));
		founding.getLoans().forEach(l -> buildingLoanAgreementMgr.remove(l.getId()));
	}

	/** the {@link AnnuityLoanMgr} */
	private final AnnuityLoanMgr annuityLoanMgr;
	/** the {@link BuildingLoanAgreementMgr} */
	private final BuildingLoanAgreementMgr buildingLoanAgreementMgr;
}
