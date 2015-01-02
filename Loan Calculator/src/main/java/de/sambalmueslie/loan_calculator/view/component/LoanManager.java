/**
 *
 */
package de.sambalmueslie.loan_calculator.view.component;

import static de.sambalmueslie.loan_calculator.view.Constants.DEFAULT_SPACING;
import static de.sambalmueslie.loan_calculator.view.Constants.TITLE_FONT;

import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.loan_calculator.model.Loan;

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
			if (item != null) {
				setGraphic(new Label(item.getTitle()));
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

		HBox title = new HBox(DEFAULT_SPACING);
		title.setAlignment(Pos.CENTER);
		Label overview = new Label("Overview");
		overview.setFont(TITLE_FONT);
		title.getChildren().add(overview);

		getChildren().add(title);

		HBox ctrl = new HBox(DEFAULT_SPACING);
		ctrl.setAlignment(Pos.CENTER);
		Button addBtn = new Button("Add");
		addBtn.setOnAction(e -> requestAddLoan());
		Button updBtn = new Button("Update");
		updBtn.setOnAction(e -> requestUpdateLoan());
		Button remBtn = new Button("Remove");
		remBtn.setOnAction(e -> requestRemoveLoan());
		ctrl.getChildren().addAll(addBtn, updBtn, remBtn);
		getChildren().add(ctrl);

		data = FXCollections.observableArrayList();
		view = new ListView<>(data);
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

		ModifyAnnuityLoanDialog dialog = new ModifyAnnuityLoanDialog(null);
		dialog.showAndWait();

		String name = dialog.getName();
		double amount = dialog.getAmount();
		double paymentRate = dialog.getPaymentRate();
		double fixedDebitInterest = dialog.getFixedDebitInterest();
		int fixedInterestPeriod = dialog.getFixedInterestPeriod();
		double estimatedDebitInterest = dialog.getEstimatedDebitInterest();

		for (LoanManagerChangeListener listener : listeners) {
			listener.requestAddLoan(this, name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest);
		}

	}

	/**
	 * Request to remove a loan.
	 */
	private void requestRemoveLoan() {
		Loan selectedLoan = view.getSelectionModel().getSelectedItem();
		if (selectedLoan == null) {
			return;
		}
		// TODO Auto-generated method stub
		logger.info("Request to remove loan: " + selectedLoan.getTitle());
	}

	/**
	 * Request to update a loan.
	 */
	private void requestUpdateLoan() {
		// TODO Auto-generated method stub
		Loan selectedLoan = view.getSelectionModel().getSelectedItem();
		if (selectedLoan == null) {
			return;
		}
		// TODO Auto-generated method stub
		logger.info("Request to update loan: " + selectedLoan.getTitle());
	}

	/** the currend managed {@link Loan}s. */
	private final ObservableList<Loan> data;
	/** the {@link List} of {@link LoanManagerChangeListener}. */
	private final List<LoanManagerChangeListener> listeners = new LinkedList<>();
	/** the {@link ListView} for the {@link Loan}s. */
	private final ListView<Loan> view;

}
