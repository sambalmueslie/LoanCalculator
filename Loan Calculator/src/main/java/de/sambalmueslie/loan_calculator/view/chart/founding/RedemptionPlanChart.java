/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart.founding;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.model.loan.RedemptionPlanEntry;

/**
 * The redemption plan chart for a {@link Founding}.
 *
 * @author sambalmueslie 2015
 */
class RedemptionPlanChart extends FoundingChart {

	/**
	 * The chart.
	 *
	 * @author sambalmueslie 2015
	 */
	private class Chart extends StackedAreaChart<Number, Number> {

		/**
		 * Constructor.
		 *
		 * @param founding
		 *            {@link #founding}
		 */
		public Chart(final Founding founding) {
			super(new NumberAxis(), new NumberAxis());
			this.founding = founding;

			setTitle("Redemption Plan");
			setAnimated(false);
			setLegendVisible(true);
			setLegendSide(Side.BOTTOM);

			founding.getLoans().forEach(this::add);

		}

		private void add(final Loan loan) {
			final ObservableList<Data<Number, Number>> values = FXCollections.observableArrayList();
			final List<RedemptionPlanEntry> redemptionPlan = loan.getRedemptionPlan();
			for (int i = 0; i < redemptionPlan.size(); i++) {
				final RedemptionPlanEntry redemption = redemptionPlan.get(i);
				values.add(new Data<Number, Number>(i, redemption.getResidualDebt()));
			}
			final Series<Number, Number> series = new Series<>(values);
			final String name = loan.getName();
			series.setName(name);
			getData().add(series);
		}

		/** the {@link Loan}. */
		private final Founding founding;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.chart.founding.FoundingChart#createChart(de.sambalmueslie.loan_calculator.model.founding.Founding)
	 */
	@Override
	protected Node createChart(final Founding founding) {
		return new Chart(founding);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.chart.founding.FoundingChart#equals(javafx.scene.Node,
	 *      de.sambalmueslie.loan_calculator.model.founding.Founding)
	 */
	@Override
	protected boolean equals(final Node node, final Founding founding) {
		if (node instanceof Chart) return ((Chart) node).founding.getId() == founding.getId();
		return false;
	}
}
