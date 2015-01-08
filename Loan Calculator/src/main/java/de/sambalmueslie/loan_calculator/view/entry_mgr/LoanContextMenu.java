/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr;

import java.util.Optional;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.loan_calculator.model.loan.AnnuityLoan;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;
import de.sambalmueslie.loan_calculator.view.dialog.ModifyAnnuityLoanDialog;

/**
 * The context menu for a loan list cell entry.
 *
 * @author sambalmueslie 2015
 */
public class LoanContextMenu extends ContextMenu {
	/** the logger. */
	private static final Logger logger = LogManager.getLogger(LoanContextMenu.class);

	/**
	 * Constructor.
	 */
	LoanContextMenu() {
		addAnnuitiyLoanMenuItem = new MenuItem("Add annuity loan");
		updateMenuItem = new MenuItem("Update");
		removeMenuItem = new MenuItem("Remove");
		getItems().addAll(addAnnuitiyLoanMenuItem, updateMenuItem, removeMenuItem);
		set(null);
	}

	/**
	 * Set the {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	void set(final Loan loan) {
		addAnnuitiyLoanMenuItem.setOnAction(e -> addAnnuitiyLoan());
		if (loan == null) {
			updateMenuItem.setOnAction(null);
			removeMenuItem.setOnAction(null);
		} else {
			updateMenuItem.setOnAction(e -> update(loan));
			removeMenuItem.setOnAction(e -> remove(loan));
		}
	}

	/**
	 * @param listener
	 *            the listener to set
	 */
	void setListener(final ViewActionListener listener) {
		this.listener = listener;
	}

	/**
	 * Add a new loan.
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
	 * Request to remove a {@link Loan}.
	 *
	 * @param entry
	 *            the entry
	 */
	private void remove(final Loan entry) {
		if (logger.isDebugEnabled()) {
			logger.debug("Request remove " + entry);
		}
		final long loanId = entry.getId();
		listener.requestRemoveLoan(loanId);
	}

	/**
	 * Request to update a {@link Loan}.
	 *
	 * @param entry
	 *            the entry
	 */
	private void update(final Loan entry) {
		if (logger.isDebugEnabled()) {
			logger.debug("Request update " + entry);
		}
		if (entry instanceof AnnuityLoan) {
			final ModifyAnnuityLoanDialog dialog = new ModifyAnnuityLoanDialog((AnnuityLoan) entry);
			final Optional<ButtonType> type = dialog.showAndWait();
			if (type.isPresent() && type.get() == ButtonType.OK) {

				final long loanId = entry.getId();
				final String name = dialog.getName();
				final double amount = dialog.getAmount();
				final double paymentRate = dialog.getPaymentRate();
				final double fixedDebitInterest = dialog.getFixedDebitInterest();
				final int fixedInterestPeriod = dialog.getFixedInterestPeriod();
				final double estimatedDebitInterest = dialog.getEstimatedDebitInterest();

				listener.requestUpdateAnnuityLoan(loanId, name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest);
			}
		}
	}

	/** the add {@link MenuItem}. */
	private final MenuItem addAnnuitiyLoanMenuItem;
	/** the {@link ViewActionListener}. */
	private ViewActionListener listener;
	/** the remove {@link MenuItem}. */
	private final MenuItem removeMenuItem;
	/** the update {@link MenuItem}. */
	private final MenuItem updateMenuItem;
}
