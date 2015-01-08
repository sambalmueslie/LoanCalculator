/**
 *
 */
package de.sambalmueslie.loan_calculator.view.panel;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.Constants;
import de.sambalmueslie.loan_calculator.view.chart.Chart;
import de.sambalmueslie.loan_calculator.view.chart.founding.FoundingChartFactory;
import de.sambalmueslie.loan_calculator.view.chart.loan.LoanChartFactory;
import de.sambalmueslie.loan_calculator.view.component.TextFieldType;

/**
 * The {@link Pane} to display a {@link Founding}.
 *
 * @author sambalmueslie 2015
 */
public class FoundingPanel extends Pane {

	/**
	 * Constructor.
	 *
	 * @param founding
	 *            {@link #founding}
	 */
	public FoundingPanel(final Founding founding) {
		this.founding = founding;
		borderPane = new BorderPane();
		getChildren().add(borderPane);

		setupHeadline();
		setupCharts();
		setupInfo();
	}

	/**
	 * Setup the charts.
	 */
	private void setupCharts() {
		chartPane = new GridPane();
		chartPane.setVgap(Constants.DEFAULT_SPACING);
		chartPane.setHgap(Constants.DEFAULT_SPACING);
		chartPane.setPrefWidth(800);

		final Chart<Loan> residualDebtChart = LoanChartFactory.createResidualDebtChart();
		founding.getLoans().forEach(l -> residualDebtChart.add(l));
		chartPane.add(residualDebtChart.getChart(), 0, 0);

		final Chart<Founding> amountChart = FoundingChartFactory.createFoundingAmountChart();
		chartPane.add(amountChart.getChart(), 1, 0);

		borderPane.setCenter(chartPane);

	}

	/**
	 * Setup the headline.
	 */
	private void setupHeadline() {
		final Label bankLabel = new Label(founding.getBankName());
		bankLabel.setFont(Constants.TITLE_FONT);
		bankLabel.setAlignment(Pos.CENTER);
		final Label nameLabel = new Label(founding.getName());
		nameLabel.setFont(Constants.TITLE_FONT);
		nameLabel.setAlignment(Pos.CENTER);

		final HBox box = new HBox(Constants.DEFAULT_SPACING, bankLabel, nameLabel);
		box.setAlignment(Pos.CENTER);
		borderPane.setTop(box);
	}

	/**
	 * Setup the info panel.
	 */
	private void setupInfo() {
		infoPanel = new InfoPanel();
		infoPanel.add("Name", founding.getName(), TextFieldType.TEXT);
		infoPanel.add("Bank Name", founding.getName(), TextFieldType.TEXT);
		infoPanel.add("Total Amount", founding.getAmount(), TextFieldType.CURRENCY);
		infoPanel.add("Total interest", founding.getTotalInterest(), TextFieldType.CURRENCY);
		infoPanel.add("Total Payment", founding.getTotalPayment(), TextFieldType.CURRENCY);
		infoPanel.add("Term", founding.getTerm(), TextFieldType.NUMBER);
		borderPane.setLeft(infoPanel);
	}

	/** the {@link BorderPane}. */
	private final BorderPane borderPane;
	/** the chart {@link TilePane}. */
	private GridPane chartPane;
	/** the {@link Founding}. */
	private final Founding founding;
	/** the {@link InfoPanel}. */
	private InfoPanel infoPanel;

}
