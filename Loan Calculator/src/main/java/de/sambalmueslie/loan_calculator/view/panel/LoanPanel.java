/**
 *
 */
package de.sambalmueslie.loan_calculator.view.panel;

import javafx.geometry.Pos;
import javafx.scene.chart.Chart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import de.sambalmueslie.loan_calculator.model.Loan;
import de.sambalmueslie.loan_calculator.view.Constants;

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
	protected LoanPanel(final T loan) {
		this.loan = loan;
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
	protected void addChart(final Chart chart, final int col, final int row) {
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
	protected void addChart(final Chart chart, final int col, final int row, final int colspan, final int rowspan) {
		chartPane.add(chart, col, row, colspan, rowspan);
	}

	/**
	 * Add a info.
	 *
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @param format
	 *            the format string @see {@link String#format(String, Object...)}
	 */
	protected void addInfo(final String name, final Object value, final String format) {
		final int row = infoPane.getChildren().size() / 2;
		infoPane.add(new Label(name), 0, row);
		final String strVal = String.format(format, value);
		final TextField textField = new TextField(strVal);
		textField.setEditable(false);
		infoPane.add(textField, 1, row);
	}

	/**
	 * @return the {@link #loan}
	 */
	protected T getLoan() {
		return loan;
	}

	/**
	 * Setup the charts.
	 */
	private void setupCharts() {
		chartPane = new GridPane();
		chartPane.setVgap(Constants.DEFAULT_SPACING);
		chartPane.setHgap(Constants.DEFAULT_SPACING);
		borderPane.setCenter(chartPane);
	}

	/**
	 * Setup headline.
	 */
	private void setupHeadline() {
		final Label nameLabel = new Label(loan.getName());
		nameLabel.setFont(Constants.TITLE_FONT);
		nameLabel.setAlignment(Pos.CENTER);
		BorderPane.setAlignment(nameLabel, Pos.CENTER);
		borderPane.setTop(nameLabel);
	}

	/**
	 * Setup the info.
	 */
	private void setupInfo() {
		infoPane = new GridPane();
		infoPane.setVgap(Constants.DEFAULT_SPACING);
		infoPane.setHgap(Constants.DEFAULT_SPACING);
		addInfo("Amount", loan.getAmount(), "%,.2f €");
		borderPane.setLeft(infoPane);
	}

	/** the {@link BorderPane}. */
	private final BorderPane borderPane;
	/** the chart {@link TilePane}. */
	private GridPane chartPane;
	/** the info {@link GridPane}. */
	private GridPane infoPane;
	/** the loan. */
	private final T loan;

}
