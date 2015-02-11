/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.tree.contextmenu;

import java.time.LocalDate;
import java.util.Optional;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.loan_calculator.backend.founding_mgt.Founding;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreement;
import de.sambalmueslie.loan_calculator.frontend.dialog.ModifyAnnuityLoanDialog;
import de.sambalmueslie.loan_calculator.frontend.dialog.ModifyBuildingLoanAgreementDialog;
import de.sambalmueslie.loan_calculator.frontend.dialog.ModifyFoundingDialog;
import de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener;

/**
 * The context menu for the entry tree view.
 *
 * @author sambalmueslie 2015
 */
public class BaseContextMenu extends ContextMenu {
	/** the logger. */
	private static final Logger logger = LogManager.getLogger(BaseContextMenu.class);

	/**
	 * Constructor.
	 *
	 * @param listener
	 *            {@link #listener}
	 */
	public BaseContextMenu(final ViewActionListener listener) {
		this.listener = listener;
		addAnnuitiyLoanMenuItem = MenuItemFactory.createNewAnnuityLoanItem();
		addAnnuitiyLoanMenuItem.setOnAction(e -> addAnnuitiyLoan());
		addBuildingLoanAgreementItem = MenuItemFactory.createNewBuildingLoanAgreementItem();
		addBuildingLoanAgreementItem.setOnAction(e -> addBuildingLoanAgreement());
		addFoundingMenuItem = MenuItemFactory.createNewFoundingItem();
		addFoundingMenuItem.setOnAction(e -> addFounding());
		getItems().addAll(addAnnuitiyLoanMenuItem, addBuildingLoanAgreementItem, addFoundingMenuItem);
	}

	/**
	 * Add a new {@link Loan}.
	 */
	private void addAnnuitiyLoan() {
		if (logger.isDebugEnabled()) {
			logger.debug("Request add new loan");
		}
		final ModifyAnnuityLoanDialog dialog = new ModifyAnnuityLoanDialog(null);
		final Optional<ButtonType> type = dialog.showAndWait();
		if (type.isPresent() && type.get() == ButtonType.OK) {

			final String name = dialog.getName();
			final double amount = dialog.getAmount();
			final double paymentRate = dialog.getPaymentRate();
			final double fixedDebitInterest = dialog.getFixedDebitInterest();
			final int fixedInterestPeriod = dialog.getFixedInterestPeriod();
			final double estimatedDebitInterest = dialog.getEstimatedDebitInterest();
			final LocalDate startDate = dialog.getStartDate();

			listener.requestAddAnnuityLoan(name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest, startDate);
		}
	}

	/**
	 * Add a {@link BuildingLoanAgreement}.
	 */
	private void addBuildingLoanAgreement() {
		if (logger.isDebugEnabled()) {
			logger.debug("Request add new building loan agreement");
		}
		final ModifyBuildingLoanAgreementDialog dialog = new ModifyBuildingLoanAgreementDialog(null);
		final Optional<ButtonType> type = dialog.showAndWait();
		if (type.isPresent() && type.get() == ButtonType.OK) {
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
			final LocalDate startDate = dialog.getStartDate();

			listener.requestAddBuildingLoanAgreement(name, amount, creditInterest, regularSavingAmount, minimumSavings, savingDuration, savingPhaseInterest,
					debitInterest, contribution, aquisitonFee, startDate);
		}
	}

	/**
	 * Add a {@link Founding}.
	 */
	private void addFounding() {
		if (logger.isDebugEnabled()) {
			logger.debug("Request add new founding");
		}

		final ModifyFoundingDialog dialog = new ModifyFoundingDialog(null);
		final Optional<ButtonType> type = dialog.showAndWait();
		if (type.isPresent() && type.get() == ButtonType.OK) {
			final String name = dialog.getName();
			final String bankName = dialog.getBankName();

			listener.requestAddFounding(name, bankName);
		}
	}

	/** the add {@link MenuItem}. */
	private final MenuItem addAnnuitiyLoanMenuItem;
	/** the add {@link MenuItem}. */
	private final MenuItem addBuildingLoanAgreementItem;
	/** the add {@link MenuItem}. */
	private final MenuItem addFoundingMenuItem;
	/** the {@link ViewActionListener}. */
	private final ViewActionListener listener;
}
