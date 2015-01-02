/**
 *
 */
package de.sambalmueslie.loan_calculator.view.component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import de.sambalmueslie.loan_calculator.model.Loan;

/**
 * A list for the overview of all {@link Loan}s.
 *
 * @author sambalmueslie 2015
 */
public class LoanOverviewList extends VBox {

	static class LoanCell extends ListCell<Loan> {
		@Override
		public void updateItem(final Loan item, final boolean empty) {
			super.updateItem(item, empty);
			if (item != null) {
				setGraphic(new Label(item.getTitle()));
			}
		}
	}

	/**
	 * Constructor.
	 */
	public LoanOverviewList() {
		getChildren().add(new Label("Overview"));

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
	 * Remove a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	public void remove(final Loan loan) {
		data.remove(loan);
	}

	private final ObservableList<Loan> data;
	private final ListView<Loan> view;

}
