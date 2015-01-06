/**
 *
 */
package de.sambalmueslie.loan_calculator.view.loan_mgr;

import static de.sambalmueslie.loan_calculator.view.Constants.DEFAULT_SPACING;
import static de.sambalmueslie.loan_calculator.view.Constants.TITLE_FONT;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.loan_calculator.model.loan.AnnuityLoan;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.dialog.ModifyAnnuityLoanDialog;

/**
 * A list for the overview of all {@link Loan}s.
 *
 * @author sambalmueslie 2015
 */
public class LoanManager extends VBox {
	/**
	 * A {@link ListCell} for a {@link Loan}.
	 *
	 * @author sambalmueslie 2015
	 */
	static class LoanCell extends ListCell<Loan> {
		@Override
		public void updateItem(final Loan item, final boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setText(null);
				setGraphic(null);
			} else if (item != null) {
				setGraphic(new Label(item.getName()));
			}

		}
	}

	/** the logger. */
	private static final Logger logger = LogManager.getLogger(LoanManager.class);

	/**
	 * Constructor.
	 */
	public LoanManager() {
		setPadding(new Insets(DEFAULT_SPACING));
		setSpacing(DEFAULT_SPACING);
		setAlignment(Pos.TOP_CENTER);

		final HBox title = new HBox(DEFAULT_SPACING);
		title.setAlignment(Pos.CENTER);
		final Label overview = new Label("Overview");
		overview.setFont(TITLE_FONT);
		title.getChildren().add(overview);

		getChildren().add(title);

		final HBox ctrl = new HBox(DEFAULT_SPACING);
		ctrl.setAlignment(Pos.CENTER);
		final Button addBtn = new Button("Add");
		addBtn.setOnAction(e -> requestAddLoan());
		final Button updBtn = new Button("Update");
		updBtn.setOnAction(e -> requestUpdateLoan());
		final Button remBtn = new Button("Remove");
		remBtn.setOnAction(e -> requestRemoveLoan());
		final Button compBtn = new Button("Compare");
		compBtn.setOnAction(e -> requestCompareSelectedLoan());
		ctrl.getChildren().addAll(addBtn, updBtn, remBtn, compBtn);
		getChildren().add(ctrl);

		data = FXCollections.observableArrayList();
		view = new ListView<>(data);
		view.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		view.setCellFactory(list -> new LoanCell());
		getChildren().add(view);
	}

	/**
	 * Add a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	public void add(final Loan loan) {
		data.add(loan);
		loan.register(this::update);
	}

	/**
	 * Register a {@link LoanManagerChangeListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	public void register(final LoanManagerChangeListener listener) {
		if (listener != null && !listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	/**
	 * Remove a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	public void remove(final Loan loan) {
		data.remove(loan);
		loan.unregister(this::update);
	}

	/**
	 * Unregister a {@link LoanManagerChangeListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	public void unregister(final LoanManagerChangeListener listener) {
		if (listener != null) {
			listeners.remove(listener);
		}
	}

	/**
	 * Request to add a loan.
	 */
	private void requestAddLoan() {
		logger.info("Request to add a loan");

		final ModifyAnnuityLoanDialog dialog = new ModifyAnnuityLoanDialog(null);
		final Optional<ButtonType> type = dialog.showAndWait();
		if (type.isPresent() && type.get() == ButtonType.OK) {

			final String name = dialog.getName();
			final double amount = dialog.getAmount();
			final double paymentRate = dialog.getPaymentRate();
			final double fixedDebitInterest = dialog.getFixedDebitInterest();
			final int fixedInterestPeriod = dialog.getFixedInterestPeriod();
			final double estimatedDebitInterest = dialog.getEstimatedDebitInterest();

			listeners.forEach(l -> l.requestAddAnnuityLoan(this, name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest));
		}
	}

	/**
	 * Request to compare the selected loans.
	 */
	private void requestCompareSelectedLoan() {
		final ObservableList<Loan> selectedLoans = view.getSelectionModel().getSelectedItems();
		if (selectedLoans == null || selectedLoans.isEmpty()) return;
		listeners.forEach(l -> l.requestCompareLoans(this, new LinkedList<Loan>(selectedLoans)));
	}

	/**
	 * Request to remove a loan.
	 */
	private void requestRemoveLoan() {
		final Loan selectedLoan = view.getSelectionModel().getSelectedItem();
		if (selectedLoan == null) return;

		final long loanId = selectedLoan.getId();
		listeners.forEach(l -> l.requestRemoveLoan(this, loanId));
	}

	/**
	 * Request to update a loan.
	 */
	private void requestUpdateLoan() {
		final Loan selectedLoan = view.getSelectionModel().getSelectedItem();
		if (selectedLoan == null) return;

		if (selectedLoan instanceof AnnuityLoan) {
			final ModifyAnnuityLoanDialog dialog = new ModifyAnnuityLoanDialog((AnnuityLoan) selectedLoan);
			final Optional<ButtonType> type = dialog.showAndWait();
			if (type.isPresent() && type.get() == ButtonType.OK) {

				final long loanId = selectedLoan.getId();
				final String name = dialog.getName();
				final double amount = dialog.getAmount();
				final double paymentRate = dialog.getPaymentRate();
				final double fixedDebitInterest = dialog.getFixedDebitInterest();
				final int fixedInterestPeriod = dialog.getFixedInterestPeriod();
				final double estimatedDebitInterest = dialog.getEstimatedDebitInterest();

				listeners.forEach(l -> l.requestUpdateAnnuityLoan(this, loanId, name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod,
						estimatedDebitInterest));
			}
		}
	}

	/**
	 * Update.
	 *
	 * @param loan
	 *            the {@link Loan}
	 */
	private void update(final Loan loan) {
		final int index = data.indexOf(loan);
		data.set(index, loan);
	}

	/** the current managed {@link Loan}s. */
	private final ObservableList<Loan> data;
	/** the {@link List} of {@link LoanManagerChangeListener}. */
	private final List<LoanManagerChangeListener> listeners = new LinkedList<>();
	/** the {@link ListView} for the {@link Loan}s. */
	private final ListView<Loan> view;

}
