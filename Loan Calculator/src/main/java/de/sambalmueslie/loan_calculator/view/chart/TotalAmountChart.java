/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart;

import javafx.geometry.Side;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import de.sambalmueslie.loan_calculator.model.Loan;

/**
 * The chart for the total amount of the loan.
 *
 * @author sambalmueslie 2015
 */
public class TotalAmountChart extends StackedBarChart<String, Number> {

	/**
	 * Constructor.
	 */
	public TotalAmountChart() {
		super(new CategoryAxis(), new NumberAxis());
		setTitle("Total Amount");
		setAnimated(false);
		setLegendVisible(true);
		setLegendSide(Side.BOTTOM);
		setPrefWidth(250);

		amountSeries = new Series<>();
		amountSeries.setName("amount");
		interestSeries = new Series<>();
		interestSeries.setName("interest");

		getData().add(amountSeries);
		getData().add(interestSeries);
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
		amountSeries.getData().add(new Data<String, Number>(name, loan.getAmount()));
		interestSeries.getData().add(new Data<String, Number>(name, loan.getTotalInterest()));
	}

	/**
	 * Remove a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
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
