/**
 *
 */
package de.sambalmueslie.loan_calculator.view.compare;

import static de.sambalmueslie.loan_calculator.view.Constants.CLASS_HEADLINE_LABEL;
import static de.sambalmueslie.loan_calculator.view.Constants.CLASS_PANEL;
import static de.sambalmueslie.loan_calculator.view.Constants.CLASS_PANEL_BORDER;

import java.util.Set;
import java.util.function.Function;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import de.sambalmueslie.loan_calculator.model.Model;
import de.sambalmueslie.loan_calculator.model.compare.Comparison;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.model.loan.RedemptionPlanEntry;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;
import de.sambalmueslie.loan_calculator.view.chart.LineChartSeriesDefinition;
import de.sambalmueslie.loan_calculator.view.chart.SeriesDefinition;
import de.sambalmueslie.loan_calculator.view.chart.generic.GenericBarChart;
import de.sambalmueslie.loan_calculator.view.chart.generic.GenericLineChart;
import de.sambalmueslie.loan_calculator.view.chart.generic.GenericStackedBarChart;
import de.sambalmueslie.loan_calculator.view.chart.loan.GenericAnnuityChart;

/**
 * The compare panel for {@link Loan}s.
 *
 * @author sambalmueslie 2015
 */
class ComparePanelLoan extends BaseComparePanel<Loan> {
	/**
	 * Constructor.
	 *
	 * @param comparison
	 *            the {@link Comparison}
	 * @param actionListener
	 *            the {@link ViewActionListener}
	 * @param model
	 *            the {@link Model}
	 */
	ComparePanelLoan(final Comparison<Loan> comparison, final ViewActionListener actionListener, final Model model) {
		super(comparison, actionListener, model);

	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.compare.BaseComparePanel#get(long)
	 */
	@Override
	protected Loan get(final long entryId) {
		return getModel().getLoan(entryId);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.compare.BaseComparePanel#getContextMenu()
	 */
	@Override
	protected ContextMenu getContextMenu() {
		return new LoanCompareContextMenu(getActionListener(), getComparison());
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.compare.BaseComparePanel#requestComparisonAddEntry(long)
	 */
	@Override
	protected void requestComparisonAddEntry(final long entryId) {
		getActionListener().requestComparisonAddLoan(getComparison().getId(), entryId);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.compare.BaseComparePanel#setup(java.util.Set)
	 */
	@Override
	protected void setup(final Set<Loan> loans) {
		final TilePane comparePane = new TilePane();
		comparePane.getStyleClass().add(CLASS_PANEL);

		comparePane.getChildren().add(addCompareFunction("Total payment", Loan::getTotalPayment));
		comparePane.getChildren().add(addCompareFunction("Total interest", Loan::getTotalInterest));
		comparePane.getChildren().add(addCompareFunction("Total Amount", Loan::getAmount));
		comparePane.getChildren().add(addCompareFunction("Term", Loan::getTerm));
		comparePane.getChildren().add(addCompareFunction("Risk capital", Loan::getRiskCapital));

		final Function<Loan, RedemptionPlanEntry> dataGetterFunction = (l -> l.getRedemptionPlan().get(1));
		final SeriesDefinition<RedemptionPlanEntry, Double> sd1 = new SeriesDefinition<>("interest", RedemptionPlanEntry::getInterest);
		final SeriesDefinition<RedemptionPlanEntry, Double> sd2 = new SeriesDefinition<>("redemption", RedemptionPlanEntry::getRedemption);
		final GenericAnnuityChart<Loan, RedemptionPlanEntry, Double> chart = new GenericAnnuityChart<>("Annuitity", dataGetterFunction, loans, sd1, sd2);
		comparePane.getChildren().add(chart);
		setTop(comparePane);

		final TilePane detailsPane = new TilePane();
		detailsPane.getStyleClass().add(CLASS_PANEL);
		detailsPane.setPrefRows(1);

		loans.forEach(l -> detailsPane.getChildren().add(createDetailsPanel(l)));
		setCenter(detailsPane);

	}

	/**
	 * Add a compare function.
	 *
	 * @param title
	 *            the title
	 * @param function
	 *            the function
	 * @return the chart {@link Node}
	 */
	private Node addCompareFunction(final String title, final Function<Loan, Number> function) {
		final GenericBarChart<Loan> chart = new GenericBarChart<>(function, title);
		chart.setPrefWidth(120);
		getComparison().getElements().forEach(f -> chart.add(f));
		return chart;
	}

	/**
	 * Create the details panel for a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 * @return the {@link Node}
	 */
	private Node createDetailsPanel(final Loan loan) {
		final GridPane detailsPane = new GridPane();
		detailsPane.setPrefWidth(450);
		detailsPane.getStyleClass().add(CLASS_PANEL_BORDER);

		final Label title = new Label(loan.getName());
		title.getStyleClass().add(CLASS_HEADLINE_LABEL);
		title.setAlignment(Pos.CENTER);
		GridPane.setHalignment(title, HPos.CENTER);
		detailsPane.add(title, 0, 0, 2, 1);

		final LineChartSeriesDefinition<Loan, RedemptionPlanEntry> s1 = new LineChartSeriesDefinition<>("residual debt", RedemptionPlanEntry::getResidualDebt,
				Loan::getRedemptionPlan);
		final GenericLineChart<Loan, RedemptionPlanEntry> residualDebtChart = new GenericLineChart<>("Redemption plan", s1);
		residualDebtChart.setLegendVisible(false);
		residualDebtChart.add(loan);
		detailsPane.add(residualDebtChart, 0, 1);

		final LineChartSeriesDefinition<Loan, RedemptionPlanEntry> s3 = new LineChartSeriesDefinition<>("interest", RedemptionPlanEntry::getInterest,
				Loan::getRedemptionPlan);
		final LineChartSeriesDefinition<Loan, RedemptionPlanEntry> s4 = new LineChartSeriesDefinition<>("redemption", RedemptionPlanEntry::getRedemption,
				Loan::getRedemptionPlan);
		final GenericStackedBarChart<Loan, RedemptionPlanEntry> annuityPlanChart = new GenericStackedBarChart<>("Annuity plan", true, s3, s4);
		annuityPlanChart.add(loan);
		detailsPane.add(annuityPlanChart, 0, 2);

		return detailsPane;
	}
}
