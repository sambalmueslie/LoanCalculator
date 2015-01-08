/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart.loan;

import java.util.List;

import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.layout.TilePane;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.model.loan.RedemptionPlanEntry;
import de.sambalmueslie.loan_calculator.view.Constants;
import de.sambalmueslie.loan_calculator.view.chart.Chart;

/**
 * The annuity plan chart.
 *
 * @author sambalmueslie 2015
 */
public class AnnuityPlanChart extends TilePane implements Chart<Loan> {

	/**
	 * The chart for a single {@link Loan}.
	 *
	 * @author sambalmueslie 2015
	 */
	private class Chart extends StackedBarChart<String, Number> {
		/**
		 * Constructor.
		 */
		public Chart(final Loan loan) {
			super(new CategoryAxis(), new NumberAxis());
			this.loan = loan;
			setTitle("Annuity " + loan.getName());
			setAnimated(false);
			setLegendVisible(true);
			setLegendSide(Side.BOTTOM);

			final Series<String, Number> interestSeries = new Series<>();
			interestSeries.setName("interest");
			final Series<String, Number> redemptionSeries = new Series<>();
			redemptionSeries.setName("redemption");

			final List<RedemptionPlanEntry> redemptionPlan = loan.getRedemptionPlan();
			for (int i = 1; i < redemptionPlan.size(); i++) {
				final String name = i + "";
				final RedemptionPlanEntry redemption = redemptionPlan.get(i);
				interestSeries.getData().add(new Data<String, Number>(name, redemption.getInterest()));
				redemptionSeries.getData().add(new Data<String, Number>(name, redemption.getRedemption()));
			}

			getData().add(interestSeries);
			getData().add(redemptionSeries);
		}

		/** the {@link Loan}. */
		private final Loan loan;
	}

	/**
	 * Constructor.
	 *
	 * @param loan
	 *            the {@link Loan} to show
	 */
	AnnuityPlanChart() {
		setHgap(Constants.DEFAULT_SPACING);
		setVgap(Constants.DEFAULT_SPACING);
		setPrefColumns(2);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.chart.LoanChart#add(de.sambalmueslie.loan_calculator.model.loan.Loan)
	 */
	@Override
	public void add(final Loan loan) {
		getChildren().add(new Chart(loan));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.chart.LoanChart#getChart()
	 */
	@Override
	public Node getChart() {
		return this;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.chart.LoanChart#remove(de.sambalmueslie.loan_calculator.model.loan.Loan)
	 */
	@Override
	public void remove(final Loan loan) {
		getChildren().removeIf(c -> ((Chart) c).loan.getId() == loan.getId());
	}

}
