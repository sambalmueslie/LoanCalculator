/**
 *
 */
package de.sambalmueslie.loan_calculator.view.panel;

import static de.sambalmueslie.loan_calculator.view.Constants.CLASS_HEADLINE_LABEL;
import static de.sambalmueslie.loan_calculator.view.Constants.CLASS_PANEL;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.Chart;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.component.TextFieldType;

/**
 * A abstract {@link Loan} panel.
 *
 * @author sambalmueslie 2015
 * @param <T>
 *            the {@link Loan} type
 */
public abstract class LoanPanel<T extends Loan> extends Pane {

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
		borderPane = new BorderPane();
		getChildren().add(borderPane);

		setupHeadline();
		setupCharts();
		setupInfo();
	}

	/**
	 * Add a {@link Chart}.
	 *
	 * @param chart
	 *            the chart
	 * @param col
	 *            the column index position for the child within the gridpane
	 * @param row
	 *            the row index position for the child within the gridpane
	 */
	protected void addChart(final Node chart, final int col, final int row) {
		chartPane.add(chart, col, row);
	}

	/**
	 * Add a {@link Chart}.
	 *
	 * @param chart
	 *            the chart
	 * @param col
	 *            the column index position for the child within the gridpane
	 * @param row
	 *            the row index position for the child within the gridpane
	 * @param colspan
	 *            the number of columns the child's layout area should span
	 * @param rowspan
	 *            the number of rows the child's layout area should span
	 */
	protected void addChart(final Node chart, final int col, final int row, final int colspan, final int rowspan) {
		chartPane.add(chart, col, row, colspan, rowspan);
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
	 *
	 * @param loan
	 *            the {@link Loan}.
	 */
	protected void update(final T loan) {
		updateInfo("Amount", loan.getAmount());
	}

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
	 * Setup the charts.
	 */
	private void setupCharts() {
		chartPane = new GridPane();
		chartPane.getStyleClass().add(CLASS_PANEL);
		chartPane.setPrefWidth(800);
		borderPane.setCenter(chartPane);
	}

	/**
	 * Setup headline.
	 */
	private void setupHeadline() {
		final Label nameLabel = new Label(loan.getName());
		nameLabel.getStyleClass().add(CLASS_HEADLINE_LABEL);
		BorderPane.setAlignment(nameLabel, Pos.CENTER);
		borderPane.setTop(nameLabel);
	}

	/**
	 * Setup the info.
	 */
	private void setupInfo() {
		infoPanel = new InfoPanel();
		infoPanel.add("Amount", loan.getAmount(), TextFieldType.CURRENCY);
		borderPane.setLeft(infoPanel);
	}

	/** the {@link BorderPane}. */
	private final BorderPane borderPane;
	/** the chart {@link TilePane}. */
	private GridPane chartPane;
	/** the {@link InfoPanel}. */
	private InfoPanel infoPanel;
	/** the loan. */
	private final T loan;

}
