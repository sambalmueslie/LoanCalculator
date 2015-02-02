/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.panel;

import static de.sambalmueslie.loan_calculator.frontend.Constants.CLASS_HEADLINE_LABEL;
import static de.sambalmueslie.loan_calculator.frontend.Constants.CLASS_PANEL;

import java.util.function.Function;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import de.sambalmueslie.loan_calculator.backend.founding_mgt.Founding;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;
import de.sambalmueslie.loan_calculator.frontend.chart.founding.AnnuityPlanChart;
import de.sambalmueslie.loan_calculator.frontend.chart.founding.RedemptionPlanChart;
import de.sambalmueslie.loan_calculator.frontend.chart.generic.ChartPanel;
import de.sambalmueslie.loan_calculator.frontend.chart.generic.GenericPieChart;
import de.sambalmueslie.loan_calculator.frontend.component.TextFieldType;
import de.sambalmueslie.loan_calculator.frontend.i18n.I18n;

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

		founding.register(f -> update((Founding) f));
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
		chartPane.getChildren().add(addPieChartFunction(I18n.get(I18n.TEXT_TOTAL_AMOUNT), Loan::getAmount));
		chartPane.getChildren().add(addPieChartFunction(I18n.get(I18n.TEXT_TOTAL_INTEREST), Loan::getTotalInterest));

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
		infoPanel.add(I18n.get(I18n.TEXT_NAME), founding.getName(), TextFieldType.TEXT);
		infoPanel.add(I18n.get(I18n.TEXT_BANK_NAME), founding.getName(), TextFieldType.TEXT);
		infoPanel.add(I18n.get(I18n.TEXT_TOTAL_AMOUNT), founding.getAmount(), TextFieldType.CURRENCY);
		infoPanel.add(I18n.get(I18n.TEXT_TOTAL_INTEREST), founding.getTotalInterest(), TextFieldType.CURRENCY);
		infoPanel.add(I18n.get(I18n.TEXT_TOTAL_PAYMENT), founding.getTotalPayment(), TextFieldType.CURRENCY);
		infoPanel.add(I18n.get(I18n.TEXT_TERM), founding.getTerm(), TextFieldType.NUMBER);
		setLeft(infoPanel);
	}

	/**
	 * Handle a update.
	 *
	 * @param founding
	 *            the {@link Founding}
	 */
	private void update(final Founding founding) {
		infoPanel.update(I18n.get(I18n.TEXT_NAME), founding.getName());
		infoPanel.update(I18n.get(I18n.TEXT_BANK_NAME), founding.getName());
		infoPanel.update(I18n.get(I18n.TEXT_TOTAL_AMOUNT), founding.getAmount());
		infoPanel.update(I18n.get(I18n.TEXT_TOTAL_INTEREST), founding.getTotalInterest());
		infoPanel.update(I18n.get(I18n.TEXT_TOTAL_PAYMENT), founding.getTotalPayment());
		infoPanel.update(I18n.get(I18n.TEXT_TERM), founding.getTerm());

		setupCharts();
	}

	/** the {@link Founding}. */
	private final Founding founding;
	/** the {@link InfoPanel}. */
	private InfoPanel infoPanel;

}
