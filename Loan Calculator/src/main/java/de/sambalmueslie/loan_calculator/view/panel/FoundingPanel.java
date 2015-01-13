/**
 *
 */
package de.sambalmueslie.loan_calculator.view.panel;

import java.util.function.Function;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.Constants;
import de.sambalmueslie.loan_calculator.view.chart.Chart;
import de.sambalmueslie.loan_calculator.view.chart.founding.FoundingChartFactory;
import de.sambalmueslie.loan_calculator.view.chart.generic.GenericPieChart;
import de.sambalmueslie.loan_calculator.view.component.TextFieldType;

/**
 * The {@link Pane} to display a {@link Founding}.
 *
 * @author sambalmueslie 2015
 */
public class FoundingPanel extends BorderPane {

	/**
	 * Constructor.
	 *
	 * @param founding
	 *            {@link #founding}
	 */
	public FoundingPanel(final Founding founding) {
		this.founding = founding;
		getStyleClass().add("founding-panel");

		setupHeadline();
		setupCharts();
		setupInfo();

		founding.register(this::update);
	}

	/**
	 * Add a {@link Chart}.
	 *
	 * @param chart
	 *            the chart
	 */
	private void addChart(final Chart<Founding> chart) {
		chart.add(founding);
		chartPane.getChildren().add(chart.getChart());
	}

	/**
	 * Add a pie chart function.
	 *
	 * @param title
	 *            the title
	 * @param function
	 *            the function
	 * @return the chart {@link Node}
	 */
	private Node addPieChartFunction(final String title, final Function<Loan, Double> function) {
		final GenericPieChart<Founding, Loan> chart = new GenericPieChart<>(Founding::getLoans, function, title);
		chart.add(founding);
		return chart;
	}

	/**
	 * Setup the charts.
	 */
	private void setupCharts() {
		chartPane = new TilePane();
		chartPane.setVgap(Constants.DEFAULT_SPACING);
		chartPane.setHgap(Constants.DEFAULT_SPACING);
		chartPane.setPrefColumns(2);

		addChart(FoundingChartFactory.createRedemptionPlanChart());
		addChart(FoundingChartFactory.createAnnuityPlanChart());
		chartPane.getChildren().add(addPieChartFunction("Total amount", Loan::getAmount));
		chartPane.getChildren().add(addPieChartFunction("Total interest", Loan::getTotalInterest));

		setCenter(chartPane);

	}

	/**
	 * Setup the headline.
	 */
	private void setupHeadline() {
		final Label bankLabel = new Label(founding.getBankName());
		bankLabel.getStyleClass().add("headline-label");
		final Label nameLabel = new Label(founding.getName());
		nameLabel.getStyleClass().add("headline-label");

		final HBox box = new HBox(bankLabel, nameLabel);
		box.getStyleClass().add("founding-panel-headline");
		setTop(box);
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
		setLeft(infoPanel);
	}

	/**
	 * Handle a update.
	 *
	 * @param founding
	 *            the {@link Founding}
	 */
	private void update(final Founding founding) {
		infoPanel.update("Name", founding.getName());
		infoPanel.update("Bank Name", founding.getName());
		infoPanel.update("Total Amount", founding.getAmount());
		infoPanel.update("Total interest", founding.getTotalInterest());
		infoPanel.update("Total Payment", founding.getTotalPayment());
		infoPanel.update("Term", founding.getTerm());
	}

	/** the chart {@link TilePane}. */
	private TilePane chartPane;
	/** the {@link Founding}. */
	private final Founding founding;
	/** the {@link InfoPanel}. */
	private InfoPanel infoPanel;

}
