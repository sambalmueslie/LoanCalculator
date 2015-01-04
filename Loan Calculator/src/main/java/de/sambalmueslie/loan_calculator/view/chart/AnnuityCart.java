/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart;

import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import de.sambalmueslie.loan_calculator.model.Loan;
import de.sambalmueslie.loan_calculator.model.Redemption;

/**
 * The annuity {@link BarChart}.
 *
 * @author sambalmueslie 2015
 */
public class AnnuityCart extends StackedBarChart<String, Number> {

	/**
	 * Constructor.
	 */
	public AnnuityCart() {
		super(new CategoryAxis(), new NumberAxis());
		setTitle("Annuity");
		setAnimated(false);
		setLegendVisible(true);
		setLegendSide(Side.BOTTOM);
		setPrefWidth(200);

		interestSeries = new Series<>();
		interestSeries.setName("interest");
		redemptionSeries = new Series<>();
		redemptionSeries.setName("redemption");

		getData().add(interestSeries);
		getData().add(redemptionSeries);
	}

	/**
	 * Add a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	public void add(final Loan loan) {
		if (loan == null) return;

		final String name = loan.getName();
		final Redemption redemption = loan.getRedemptionPlan().get(1);
		interestSeries.getData().add(new Data<String, Number>(name, redemption.getInterest()));
		redemptionSeries.getData().add(new Data<String, Number>(name, redemption.getRedemption()));

	}

	/**
	 * Remove a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	public void remove(final Loan loan) {
		if (loan == null) return;
		interestSeries.getData().removeIf(d -> d.getXValue().equals(loan.getName()));
		redemptionSeries.getData().removeIf(d -> d.getXValue().equals(loan.getName()));
	}

	/** the interest {@link Series}. */
	private final Series<String, Number> interestSeries;
	/** the redemption {@link Series}. */
	private final Series<String, Number> redemptionSeries;
}
