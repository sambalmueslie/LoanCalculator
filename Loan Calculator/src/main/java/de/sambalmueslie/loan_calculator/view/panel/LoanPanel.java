/**
 *
 */
package de.sambalmueslie.loan_calculator.view.panel;

import static de.sambalmueslie.loan_calculator.view.Constants.CLASS_HEADLINE_LABEL;
import static de.sambalmueslie.loan_calculator.view.Constants.CLASS_PANEL;
import static de.sambalmueslie.loan_calculator.view.Constants.CLASS_PANEL_BORDER;
import static de.sambalmueslie.loan_calculator.view.Constants.CLASS_PANEL_EMPTY;
import javafx.scene.Node;
import javafx.scene.chart.Chart;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.component.TextFieldType;

/**
 * A abstract {@link Loan} panel.
 *
 * @author sambalmueslie 2015
 * @param <T>
 *            the {@link Loan} type
 */
public abstract class LoanPanel<T extends Loan> extends VBox {

	/**
	 * Constructor.
	 *
	 * @param loan
	 *            {@link #loan}
	 */
	@SuppressWarnings("unchecked")
	protected LoanPanel(final T loan) {
		this.loan = loan;
		this.loan.register(l -> update((T) l));

		getStyleClass().add(CLASS_PANEL);

		setupHeadline();

		final HBox content = new HBox();
		content.getStyleClass().add(CLASS_PANEL_EMPTY);

		infoPanel = new InfoPanel();
		infoPanel.add("Amount", loan.getAmount(), TextFieldType.CURRENCY);
		content.getChildren().add(infoPanel);

		chartPane = new TilePane();
		chartPane.setPrefColumns(1);
		chartPane.getStyleClass().add(CLASS_PANEL_BORDER);

		content.getChildren().add(chartPane);

		getChildren().add(content);
	}

	/**
	 * Add a {@link Chart}.
	 *
	 * @param chart
	 *            the chart
	 */
	protected void addChart(final Node chart) {
		chartPane.getChildren().add(chart);
	}

	/**
	 * Add a info.
	 *
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @param type
	 *            the {@link TextFieldType}
	 */
	protected void addInfo(final String name, final Object value, final TextFieldType type) {
		infoPanel.add(name, value, type);
	}

	/**
	 * @return the {@link #loan}
	 */
	protected T getLoan() {
		return loan;
	}

	/**
	 * Update.
	 */
	protected abstract void update();

	/**
	 * Update a info.
	 *
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 */
	protected void updateInfo(final String name, final Object value) {
		infoPanel.update(name, value);
	}

	/**
	 * Setup the headline.
	 */
	private void setupHeadline() {
		final Label nameLabel = new Label(loan.getName());
		nameLabel.getStyleClass().add(CLASS_HEADLINE_LABEL);
		final HBox headline = new HBox(nameLabel);
		getChildren().add(headline);
	}

	/**
	 * Update.
	 *
	 * @param loan
	 *            the {@link Loan}.
	 */
	private void update(final T loan) {
		chartPane.getChildren().clear();
		updateInfo("Amount", loan.getAmount());
		update();
	}

	private final TilePane chartPane;
	private final InfoPanel infoPanel;
	/** the loan. */
	private final T loan;

}
