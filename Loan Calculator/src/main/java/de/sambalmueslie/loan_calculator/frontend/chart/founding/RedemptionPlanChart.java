package de.sambalmueslie.loan_calculator.frontend.chart.founding;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import de.sambalmueslie.loan_calculator.backend.founding_mgt.Founding;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;
import de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlanEntry;
import de.sambalmueslie.loan_calculator.frontend.i18n.I18n;

/**
 * The redemption plan {@link StackedAreaChart}.
 *
 * @author sambalmueslie 2015
 */
public class RedemptionPlanChart extends StackedAreaChart<Number, Number> {

	/**
	 * Constructor.
	 *
	 * @param founding
	 *            {@link #founding}
	 */
	public RedemptionPlanChart(final Founding founding) {
		super(new NumberAxis(), new NumberAxis());

		setStyle("-fx-border-color: lightgray;");

		setTitle(I18n.get(I18n.REDEMPTION_PLAN_CHART_TITLE));
		setAnimated(false);
		setLegendVisible(true);
		setLegendSide(Side.BOTTOM);

		founding.getLoans().forEach(this::add);
	}

	/**
	 * Add a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	private void add(final Loan loan) {
		final ObservableList<Data<Number, Number>> values = FXCollections.observableArrayList();
		final List<RedemptionPlanEntry> redemptionPlan = loan.getRedemptionPlan().getEntries();
		for (int i = 0; i < redemptionPlan.size(); i++) {
			final RedemptionPlanEntry redemption = redemptionPlan.get(i);
			values.add(new Data<Number, Number>(i, redemption.getResidualDebt()));
		}
		final Series<Number, Number> series = new Series<>(values);
		final String name = loan.getName();
		series.setName(name);
		getData().add(series);
	}

}