/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr;

import java.util.Optional;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.loan_calculator.controller.Controller;
import de.sambalmueslie.loan_calculator.model.loan.AnnuityLoan;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.Constants;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;
import de.sambalmueslie.loan_calculator.view.dialog.ModifyAnnuityLoanDialog;

/**
 * The {@link Loan} entry {@link ListCell}.
 *
 * @author sambalmueslie 2015
 */
public class LoanEntryListCell extends GridPane implements EntryListCellContent<Loan> {
	/** the logger. */
	private static final Logger logger = LogManager.getLogger(Controller.class);

	/**
	 * Constructor.
	 */
	public LoanEntryListCell() {
		setHgap(Constants.DEFAULT_SPACING);
		setVgap(Constants.DEFAULT_SPACING);

		add(name, 0, 0);

		addAnnuitiyLoanMenuItem = new MenuItem("Add annuity loan");
		addAnnuitiyLoanMenuItem.setOnAction(e -> addAnnuitiyLoan());
		updateMenuItem = new MenuItem("Update");
		removeMenuItem = new MenuItem("Remove");
		contextMenu.getItems().addAll(addAnnuitiyLoanMenuItem, updateMenuItem, removeMenuItem);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.entry_mgr.EntryListCellContent#getContextMenu(de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry)
	 */
	@Override
	public ContextMenu getContextMenu(final Loan entry) {
		updateMenuItem.setOnAction(e -> update(entry));
		removeMenuItem.setOnAction(e -> remove(entry));
		return contextMenu;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.entry_mgr.EntryListCellContent#getGrapic(de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry)
	 */
	@Override
	public Node getGrapic(final Loan entry) {
		name.setText(entry.getName());
		return this;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.entry_mgr.EntryListCellContent#set(de.sambalmueslie.loan_calculator.view.ViewActionListener)
	 */
	@Override
	public void set(final ViewActionListener listener) {
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
	/** the {@link ContextMenu}. */
	private final ContextMenu contextMenu = new ContextMenu();
	/** the {@link ViewActionListener}. */
	private ViewActionListener listener;
	/** the name {@link Label}. */
	private final Label name = new Label();
	/** the remove {@link MenuItem}. */
	private final MenuItem removeMenuItem;
	/** the update {@link MenuItem}. */
	private final MenuItem updateMenuItem;

}
