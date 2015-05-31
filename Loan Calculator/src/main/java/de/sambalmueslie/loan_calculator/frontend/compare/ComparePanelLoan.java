/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.compare;

import static de.sambalmueslie.loan_calculator.frontend.Constants.CLASS_HEADLINE_LABEL;
import static de.sambalmueslie.loan_calculator.frontend.Constants.CLASS_PANEL;
import static de.sambalmueslie.loan_calculator.frontend.Constants.CLASS_PANEL_BORDER;

import java.util.Set;
import java.util.function.Function;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import de.sambalmueslie.loan_calculator.backend.compare_mgt.Comparison;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;
import de.sambalmueslie.loan_calculator.backend.model.Model;
import de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlan;
import de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlanEntry;
import de.sambalmueslie.loan_calculator.frontend.chart.LineChartSeriesDefinition;
import de.sambalmueslie.loan_calculator.frontend.chart.SeriesDefinition;
import de.sambalmueslie.loan_calculator.frontend.chart.generic.GenericBarChart;
import de.sambalmueslie.loan_calculator.frontend.chart.generic.GenericLineChart;
import de.sambalmueslie.loan_calculator.frontend.chart.generic.GenericStackedBarChart;
import de.sambalmueslie.loan_calculator.frontend.chart.loan.GenericAnnuityChart;
import de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener;
import de.sambalmueslie.loan_calculator.frontend.i18n.I18n;

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
	 * @see de.sambalmueslie.loan_calculator.frontend.compare.BaseComparePanel#get(long)
	 */
	@Override
	protected Loan get(final long entryId) {
		return getModel().getLoan(entryId);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.compare.BaseComparePanel#getContextMenu()
	 */
	@Override
	protected ContextMenu getContextMenu() {
		return new LoanCompareContextMenu(getActionListener(), getComparison());
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.compare.BaseComparePanel#requestComparisonAddEntry(long)
	 */
	@Override
	protected void requestComparisonAddEntry(final long entryId) {
		getActionListener().requestComparisonAddLoan(getComparison().getId(), entryId);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.compare.BaseComparePanel#setup(java.util.Set)
	 */
	@Override
	protected void setup(final Set<Loan> loans) {
		final TilePane comparePane = new TilePane();
		comparePane.getStyleClass().add(CLASS_PANEL);

		comparePane.getChildren().add(addCompareFunction(I18n.get(I18n.TEXT_TOTAL_PAYMENT), t -> t.getRedemptionPlan().getTotalPayment()));
		comparePane.getChildren().add(addCompareFunction(I18n.get(I18n.TEXT_TOTAL_INTEREST), t -> t.getRedemptionPlan().getTotalInterest()));
		comparePane.getChildren().add(addCompareFunction(I18n.get(I18n.TEXT_TOTAL_AMOUNT), Loan::getAmount));
		comparePane.getChildren().add(addCompareFunction(I18n.get(I18n.TEXT_TERM), t -> t.getRedemptionPlan().getTerm()));
		comparePane.getChildren().add(addCompareFunction(I18n.get(I18n.TEXT_RISK_CAPITAL), t -> t.getRedemptionPlan().getRiskCapital()));

		final Function<Loan, RedemptionPlanEntry> dataGetterFunction = (l -> l.getRedemptionPlan().getEntries().get(1));
		final SeriesDefinition<RedemptionPlanEntry, Double> sd1 = new SeriesDefinition<>(I18n.get(I18n.TEXT_INTEREST), RedemptionPlanEntry::getInterest);
		final SeriesDefinition<RedemptionPlanEntry, Double> sd2 = new SeriesDefinition<>(I18n.get(I18n.TEXT_REDEMPTION), RedemptionPlanEntry::getRedemption);
		final GenericAnnuityChart<Loan, RedemptionPlanEntry, Double> chart =
				new GenericAnnuityChart<>(I18n.get(I18n.ANNUITY_PLAN_CHART_TITLE), dataGetterFunction, loans, sd1, sd2);
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

		final RedemptionPlan redemptionPlan = loan.getRedemptionPlan();
		final LineChartSeriesDefinition<RedemptionPlan, RedemptionPlanEntry> s1 =
				new LineChartSeriesDefinition<>(I18n.get(I18n.TEXT_RESIDUAL_DEBT), RedemptionPlanEntry::getResidualDebt, RedemptionPlan::getEntries);
		final GenericLineChart<RedemptionPlan, RedemptionPlanEntry> residualDebtChart = new GenericLineChart<>(I18n.get(I18n.REDEMPTION_PLAN_CHART_TITLE), s1);
		residualDebtChart.setLegendVisible(false);
		residualDebtChart.add(redemptionPlan);
		detailsPane.add(residualDebtChart, 0, 1);

		final LineChartSeriesDefinition<RedemptionPlan, RedemptionPlanEntry> s3 =
				new LineChartSeriesDefinition<>(I18n.get(I18n.TEXT_INTEREST), RedemptionPlanEntry::getInterest, RedemptionPlan::getEntries);
		final LineChartSeriesDefinition<RedemptionPlan, RedemptionPlanEntry> s4 =
				new LineChartSeriesDefinition<>(I18n.get(I18n.TEXT_REDEMPTION), RedemptionPlanEntry::getRedemption, RedemptionPlan::getEntries);
		final GenericStackedBarChart<RedemptionPlan, RedemptionPlanEntry> annuityPlanChart =
				new GenericStackedBarChart<>(I18n.get(I18n.ANNUITY_PLAN_CHART_TITLE), true, s3, s4);
		annuityPlanChart.add(redemptionPlan);
		detailsPane.add(annuityPlanChart, 0, 2);

		return detailsPane;
	}
}
