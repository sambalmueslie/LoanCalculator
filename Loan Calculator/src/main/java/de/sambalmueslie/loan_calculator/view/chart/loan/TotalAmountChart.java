/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart.loan;

import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.chart.Chart;

/**
 * The chart for the total amount of the loan.
 *
 * @author sambalmueslie 2015
 */
public class TotalAmountChart extends StackedBarChart<String, Number> implements Chart<Loan> {

	/**
	 * Constructor.
	 */
	TotalAmountChart() {
		super(new CategoryAxis(), new NumberAxis());
		setTitle("Total Amount");
		setAnimated(false);
		setLegendVisible(true);
		setLegendSide(Side.BOTTOM);
		setPrefWidth(200);

		amountSeries = new Series<>();
		amountSeries.setName("amount");
		interestSeries = new Series<>();
		interestSeries.setName("interest");

		getData().add(amountSeries);
		getData().add(interestSeries);
	}

	@Override
	public void add(final Loan loan) {
		if (loan == null) return;

		final String name = loan.getName();
		amountSeries.getData().add(new Data<String, Number>(name, loan.getAmount()));
		interestSeries.getData().add(new Data<String, Number>(name, loan.getTotalInterest()));
	}

	@Override
	public Node getChart() {
		return this;
	}

	@Override
	public void remove(final Loan loan) {
		if (loan == null) return;
		amountSeries.getData().removeIf(d -> d.getXValue().equals(loan.getName()));
		interestSeries.getData().removeIf(d -> d.getXValue().equals(loan.getName()));
	}

	/** the amount {@link Series}. */
	private final Series<String, Number> amountSeries;
	/** the interest {@link Series}. */
	private final Series<String, Number> interestSeries;
}
