/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart;

import javafx.geometry.Side;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import de.sambalmueslie.loan_calculator.model.Loan;
import de.sambalmueslie.loan_calculator.model.Redemption;

/**
 * The annuity plan chart.
 *
 * @author sambalmueslie 2015
 */
public class AnnuityPlanChart extends StackedBarChart<String, Number> {
	/**
	 * Constructor.
	 *
	 * @param loan
	 *            the {@link Loan} to show
	 */
	public AnnuityPlanChart(final Loan loan) {
		super(new CategoryAxis(), new NumberAxis());
		setTitle("Annuity " + loan.getName());
		setAnimated(false);
		setLegendVisible(true);
		setLegendSide(Side.BOTTOM);

		final Series<String, Number> interestSeries = new Series<>();
		interestSeries.setName("interest");
		final Series<String, Number> redemptionSeries = new Series<>();
		redemptionSeries.setName("redemption");

		for (int i = 0; i < loan.getRedemptionPlan().size(); i++) {
			final Redemption redemption = loan.getRedemptionPlan().get(i);
			final String name = i + "";
			interestSeries.getData().add(new Data<String, Number>(name, redemption.getInterest()));
			redemptionSeries.getData().add(new Data<String, Number>(name, redemption.getRedemption()));
		}

		getData().add(interestSeries);
		getData().add(redemptionSeries);
	}

}
