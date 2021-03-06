/**
 *
 */
package de.sambalmueslie.loan_calculator.view.tree.contextmenu;

import java.util.Optional;

import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.loan_calculator.model.loan.AnnuityLoan;
import de.sambalmueslie.loan_calculator.model.loan.BuildingLoanAgreement;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;
import de.sambalmueslie.loan_calculator.view.dialog.ModifyAnnuityLoanDialog;
import de.sambalmueslie.loan_calculator.view.dialog.ModifyBuildingLoanAgreementDialog;
import de.sambalmueslie.loan_calculator.view.i18n.I18n;
import de.sambalmueslie.loan_calculator.view.icons.IconProvider;

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
		updateMenuItem = new MenuItem(I18n.get(I18n.CONTEXT_MENU_UPDATE) + " " + loan.getName(), IconProvider.createImageView(IconProvider.ICON_PAGE_EDIT));
		updateMenuItem.setOnAction(e -> update(loan));
		removeMenuItem = new MenuItem(I18n.get(I18n.CONTEXT_MENU_REMOVE) + " " + loan.getName(), IconProvider.createImageView(IconProvider.ICON_PAGE_DELETE));
		removeMenuItem.setOnAction(e -> remove(loan));
		compareMenuItem = new MenuItem(I18n.get(I18n.CONTEXT_MENU_COMPARE) + " " + loan.getName(),
				IconProvider.createImageView(IconProvider.ICON_PAGE_COMPONENT));
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
				final String name = dialog.getName();
				final double amount = dialog.getAmount();
				final double paymentRate = dialog.getPaymentRate();
				final double fixedDebitInterest = dialog.getFixedDebitInterest();
				final int fixedInterestPeriod = dialog.getFixedInterestPeriod();
				final double estimatedDebitInterest = dialog.getEstimatedDebitInterest();

				listener.requestUpdateAnnuityLoan(loanId, name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest);
			}
		} else if (loan instanceof BuildingLoanAgreement) {
			final ModifyBuildingLoanAgreementDialog dialog = new ModifyBuildingLoanAgreementDialog((BuildingLoanAgreement) loan);
			final Optional<ButtonType> type = dialog.showAndWait();
			if (type.isPresent() && type.get() == ButtonType.OK) {
				final long loanId = loan.getId();
				final String name = dialog.getName();
				final double amount = dialog.getAmount();
				final double creditInterest = dialog.getCreditInterest();
				final double regularSavingAmount = dialog.getRegularSavingAmount();
				final double minimumSavings = dialog.getMinimumSavings();
				final int savingDuration = dialog.getSavingDuration();
				final double savingPhaseInterest = dialog.getSavingPhaseInterest();
				final double debitInterest = dialog.getDebitInterest();
				final double contribution = dialog.getContribution();
				final double aquisitonFee = dialog.getAquisitionFee();
				listener.requestUpdateBuildingLoanAgreement(loanId, name, amount, creditInterest, regularSavingAmount, minimumSavings, savingDuration,
						savingPhaseInterest, debitInterest, contribution, aquisitonFee);
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
