/**
 *
 */
package de.sambalmueslie.loan_calculator.view.panel;

import static de.sambalmueslie.loan_calculator.view.Constants.CLASS_HEADLINE_LABEL;
import static de.sambalmueslie.loan_calculator.view.Constants.CLASS_PANEL;

import java.util.function.Function;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.chart.founding.AnnuityPlanChart;
import de.sambalmueslie.loan_calculator.view.chart.founding.RedemptionPlanChart;
import de.sambalmueslie.loan_calculator.view.chart.generic.ChartPanel;
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

		setupHeadline();
		setupCharts();
		setupInfo();

		founding.register(this::update);
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
		return new GenericPieChart<>(Founding::getLoans, function, title, founding);
	}

	/**
	 * Setup the charts.
	 */
	private void setupCharts() {
		final ChartPanel chartPane = new ChartPanel();

		chartPane.getChildren().add(new RedemptionPlanChart(founding));
		chartPane.getChildren().add(new AnnuityPlanChart(founding));
		chartPane.getChildren().add(addPieChartFunction("Total amount", Loan::getAmount));
		chartPane.getChildren().add(addPieChartFunction("Total interest", Loan::getTotalInterest));

		setCenter(chartPane);

	}

	/**
	 * Setup the headline.
	 */
	private void setupHeadline() {
		final Label bankLabel = new Label(founding.getBankName());
		bankLabel.getStyleClass().add(CLASS_HEADLINE_LABEL);
		final Label nameLabel = new Label(founding.getName());
		nameLabel.getStyleClass().add(CLASS_HEADLINE_LABEL);

		final HBox box = new HBox(bankLabel, nameLabel);
		box.getStyleClass().add(CLASS_PANEL);
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

		setupCharts();
	}

	/** the {@link Founding}. */
	private final Founding founding;
	/** the {@link InfoPanel}. */
	private InfoPanel infoPanel;

}
