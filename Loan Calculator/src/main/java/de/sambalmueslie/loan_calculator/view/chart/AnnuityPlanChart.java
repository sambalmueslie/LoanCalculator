/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart;

import javafx.geometry.Side;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart.Series;
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
	 */
	public AnnuityPlanChart() {
		super(new CategoryAxis(), new NumberAxis());
		setTitle("Annuity");
		setAnimated(false);
		setLegendVisible(true);
		setLegendSide(Side.BOTTOM);

		interestSeries = new Series<>();
		interestSeries.setName("interest");
		redemptionSeries = new Series<>();
		redemptionSeries.setName("redemption");

		getData().add(interestSeries);
		getData().add(redemptionSeries);
	}

	/**
	 * Show a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	public void show(final Loan loan) {
		if (loan == null) return;
		for (int i = 0; i < loan.getRedemptionPlan().size(); i++) {
			final Redemption redemption = loan.getRedemptionPlan().get(i);
			interestSeries.getData().add(new Data<String, Number>(i + "", redemption.getInterest()));
			redemptionSeries.getData().add(new Data<String, Number>(i + "", redemption.getRedemption()));
		}
	}

	/** the interest {@link Series}. */
	private final Series<String, Number> interestSeries;
	/** the redemption {@link Series}. */
	private final Series<String, Number> redemptionSeries;
}
