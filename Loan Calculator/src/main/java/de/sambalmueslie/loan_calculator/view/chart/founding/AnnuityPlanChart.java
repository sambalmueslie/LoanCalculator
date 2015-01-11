/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart.founding;

import java.util.List;

import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.model.loan.RedemptionPlanEntry;

/**
 * The annuity plan chart.
 *
 * @author sambalmueslie 2015
 */
public class AnnuityPlanChart extends FoundingChart {

	/**
	 * The chart for a single {@link Loan}.
	 *
	 * @author sambalmueslie 2015
	 */
	private class Chart extends StackedBarChart<String, Number> {
		/**
		 * Constructor.
		 */
		public Chart(final Founding founding) {
			super(new CategoryAxis(), new NumberAxis());
			this.founding = founding;
			setTitle("Annuity " + founding.getName());
			setAnimated(false);
			setLegendVisible(true);
			setLegendSide(Side.BOTTOM);

			final Series<String, Number> interestSeries = new Series<>();
			interestSeries.setName("interest");
			final Series<String, Number> redemptionSeries = new Series<>();
			redemptionSeries.setName("redemption");

			final List<RedemptionPlanEntry> redemptionPlan = founding.getRedemptionPlan();
			for (int i = 1; i < redemptionPlan.size(); i++) {
				final String name = i + "";
				final RedemptionPlanEntry redemption = redemptionPlan.get(i);
				interestSeries.getData().add(new Data<String, Number>(name, redemption.getInterest()));
				redemptionSeries.getData().add(new Data<String, Number>(name, redemption.getRedemption()));
			}

			getData().add(interestSeries);
			getData().add(redemptionSeries);
		}

		/** the {@link Founding}. */
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
