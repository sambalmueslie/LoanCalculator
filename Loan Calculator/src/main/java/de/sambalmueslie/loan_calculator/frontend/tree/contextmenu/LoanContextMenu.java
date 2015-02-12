/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.tree.contextmenu;

import java.util.Optional;

import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoanSettings;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreement;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreementSettings;
import de.sambalmueslie.loan_calculator.frontend.dialog.ModifyAnnuityLoanDialog;
import de.sambalmueslie.loan_calculator.frontend.dialog.ModifyBuildingLoanAgreementDialog;
import de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener;

/**
 * The context menu for a loan list cell loan.
 *
 * @author sambalmueslie 2015
 */
public class LoanContextMenu extends BaseContextMenu {
	/** the logger. */
	private static final Logger logger = LogManager.getLogger(LoanContextMenu.class);

	/**
	 * Constructor.
	 *
	 * @param loan
	 *            the {@link Loan}
	 * @param listener
	 *            {@link #listener}
	 */
	public LoanContextMenu(final Loan loan, final ViewActionListener listener) {
		super(listener);
		this.listener = listener;
		updateMenuItem = MenuItemFactory.createUpdateLoanItem(loan);
		updateMenuItem.setOnAction(e -> update(loan));
		removeMenuItem = MenuItemFactory.createRemoveLoanItem(loan);
		removeMenuItem.setOnAction(e -> remove(loan));
		compareMenuItem = MenuItemFactory.createCompareLoanItem(loan);
		compareMenuItem.setOnAction(e -> compare(loan));
		getItems().addAll(new SeparatorMenuItem(), updateMenuItem, removeMenuItem, compareMenuItem);
	}

	/**
	 * Request to compare the {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	private void compare(final Loan loan) {
		if (logger.isDebugEnabled()) {
			logger.debug("Request compare " + loan);
		}
		final long loanId = loan.getId();
		listener.requestAddComparisonLoan(loanId);
	}

	/**
	 * Request to remove a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	private void remove(final Loan loan) {
		if (logger.isDebugEnabled()) {
			logger.debug("Request remove " + loan);
		}
		final long loanId = loan.getId();
		listener.requestRemoveLoan(loanId);
	}

	/**
	 * Request to update a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	private void update(final Loan loan) {
		if (logger.isDebugEnabled()) {
			logger.debug("Request update " + loan);
		}
		if (loan instanceof AnnuityLoan) {
			final ModifyAnnuityLoanDialog dialog = new ModifyAnnuityLoanDialog((AnnuityLoan) loan);
			final Optional<ButtonType> type = dialog.showAndWait();
			if (type.isPresent() && type.get() == ButtonType.OK) {

				final long loanId = loan.getId();
				final AnnuityLoanSettings settings = dialog.getSettings();

				listener.requestUpdateAnnuityLoan(loanId, settings);
			}
		} else if (loan instanceof BuildingLoanAgreement) {
			final ModifyBuildingLoanAgreementDialog dialog = new ModifyBuildingLoanAgreementDialog((BuildingLoanAgreement) loan);
			final Optional<ButtonType> type = dialog.showAndWait();
			if (type.isPresent() && type.get() == ButtonType.OK) {
				final long loanId = loan.getId();
				final BuildingLoanAgreementSettings settings = dialog.getSettings();
				listener.requestUpdateBuildingLoanAgreement(loanId, settings);
			}
		}
	}

	/** the compare {@link MenuItem}. */
	private final MenuItem compareMenuItem;
	/** the {@link ViewActionListener}. */
	private final ViewActionListener listener;
	/** the remove {@link MenuItem}. */
	private final MenuItem removeMenuItem;
	/** the update {@link MenuItem}. */
	private final MenuItem updateMenuItem;
}
