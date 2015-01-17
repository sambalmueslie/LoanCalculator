/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr.contextmenu;

import java.util.Optional;

import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.loan_calculator.model.loan.AnnuityLoan;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;
import de.sambalmueslie.loan_calculator.view.dialog.ModifyAnnuityLoanDialog;
import de.sambalmueslie.loan_calculator.view.entry_mgr.tree.IconProvider;

/**
 * The context menu for a loan list cell loan.
 *
 * @author sambalmueslie 2015
 */
public class LoanContextMenu extends EntryTreeContextMenu {
	/** the logger. */
	private static final Logger logger = LogManager.getLogger(LoanContextMenu.class);

	/**
	 * Constructor.
	 */
	public LoanContextMenu() {
		super();
		updateMenuItem = new MenuItem("Update", IconProvider.createImageView(IconProvider.ICON_PAGE_EDIT));
		removeMenuItem = new MenuItem("Remove", IconProvider.createImageView(IconProvider.ICON_PAGE_DELETE));
		compareMenuItem = new MenuItem("Compare", IconProvider.createImageView(IconProvider.ICON_PAGE_COMPONENT));
		getItems().addAll(new SeparatorMenuItem(), updateMenuItem, removeMenuItem, compareMenuItem);
		set(null);
	}

	/**
	 * Set the {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	public void set(final Loan loan) {
		if (loan == null) {
			updateMenuItem.setOnAction(null);
			updateMenuItem.setText("Update");
			removeMenuItem.setOnAction(null);
			removeMenuItem.setText("Remove");
			compareMenuItem.setOnAction(null);
			compareMenuItem.setText("Compare");
		} else {
			updateMenuItem.setOnAction(e -> update(loan));
			updateMenuItem.setText("Update " + loan.getName());
			removeMenuItem.setOnAction(e -> remove(loan));
			removeMenuItem.setText("Remove " + loan.getName());
			compareMenuItem.setOnAction(e -> compare(loan));
			compareMenuItem.setText("Compare " + loan.getName());
		}
	}

	/**
	 * @param listener
	 *            the listener to set
	 */
	@Override
	public void setListener(final ViewActionListener listener) {
		this.listener = listener;
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
		}
	}

	/** the compare {@link MenuItem}. */
	private final MenuItem compareMenuItem;
	/** the {@link ViewActionListener}. */
	private ViewActionListener listener;
	/** the remove {@link MenuItem}. */
	private final MenuItem removeMenuItem;
	/** the update {@link MenuItem}. */
	private final MenuItem updateMenuItem;
}
