/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart.loan;

import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.model.loan.RedemptionPlanEntry;
import de.sambalmueslie.loan_calculator.view.chart.Chart;

/**
 * The annuity {@link BarChart}.
 *
 * @author sambalmueslie 2015
 */
public class AnnuityCart extends StackedBarChart<String, Number> implements Chart<Loan> {

	/**
	 * Constructor.
	 */
	AnnuityCart() {
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

	@Override
	public void add(final Loan loan) {
		if (loan == null) return;

		final String name = loan.getName();
		final RedemptionPlanEntry redemption = loan.getRedemptionPlan().get(1);
		interestSeries.getData().add(new Data<String, Number>(name, redemption.getInterest()));
		redemptionSeries.getData().add(new Data<String, Number>(name, redemption.getRedemption()));

	}

	@Override
	public Node getChart() {
		return this;
	}

	@Override
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
