/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr.contextmenu;

import java.util.Optional;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;
import de.sambalmueslie.loan_calculator.view.dialog.ModifyAnnuityLoanDialog;
import de.sambalmueslie.loan_calculator.view.dialog.ModifyFoundingDialog;
import de.sambalmueslie.loan_calculator.view.entry_mgr.tree.IconProvider;

/**
 * The {@link ContextMenu} for the entry tree.
 *
 * @author sambalmueslie 2015
 */
public class EntryTreeContextMenu extends ContextMenu {
	/** the logger. */
	private static final Logger logger = LogManager.getLogger(EntryTreeContextMenu.class);

	/**
	 * Constructor.
	 */
	public EntryTreeContextMenu() {
		addAnnuitiyLoanMenuItem = new MenuItem("Add new annuity loan", IconProvider.createImageView(IconProvider.ICON_PAGE_NEW));
		addFoundingMenuItem = new MenuItem("Add new founding", IconProvider.createImageView(IconProvider.ICON_FOLDER_NEW));
		getItems().addAll(addAnnuitiyLoanMenuItem, addFoundingMenuItem);
	}

	/**
	 * Set the {@link ViewActionListener}.
	 *
	 * @param listener
	 *            the listener to set
	 */
	public void setListener(final ViewActionListener listener) {
		this.listener = listener;
		addAnnuitiyLoanMenuItem.setOnAction(e -> addAnnuitiyLoan());
		addFoundingMenuItem.setOnAction(e -> addFounding());
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

			listener.requestAddAnnuityLoan(name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest);
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
	private final MenuItem addFoundingMenuItem;
	/** the {@link ViewActionListener}. */
	private ViewActionListener listener;
}
