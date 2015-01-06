/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import de.sambalmueslie.loan_calculator.model.Loan;
import de.sambalmueslie.loan_calculator.model.Redemption;

/**
 * The annuity plan chart.
 *
 * @author sambalmueslie 2015
 */
public class AnnuityPlanChart extends StackedBarChart<String, Number> implements LoanChart {
	/**
	 * Constructor.
	 *
	 * @param loan
	 *            the {@link Loan} to show
	 */
	AnnuityPlanChart() {
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
	 * @see de.sambalmueslie.loan_calculator.view.chart.LoanChart#add(de.sambalmueslie.loan_calculator.model.Loan)
	 */
	@Override
	public void add(final Loan loan) {
		setTitle("Annuity " + loan.getName());
		final ObservableList<Data<String, Number>> interestData = FXCollections.observableArrayList();
		final ObservableList<Data<String, Number>> redemptionData = FXCollections.observableArrayList();
		final List<Redemption> redemptionPlan = loan.getRedemptionPlan();
		for (int i = 1; i < redemptionPlan.size(); i++) {
			final String name = i + "";
			final Redemption redemption = redemptionPlan.get(i);
			interestData.add(new Data<String, Number>(name, redemption.getInterest()));
			redemptionData.add(new Data<String, Number>(name, redemption.getRedemption()));
		}

		interestSeries.setData(interestData);
		redemptionSeries.setData(redemptionData);

		getData().set(0, interestSeries);
		getData().set(1, redemptionSeries);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.chart.LoanChart#getChart()
	 */
	@Override
	public Chart getChart() {
		return this;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.chart.LoanChart#remove(de.sambalmueslie.loan_calculator.model.Loan)
	 */
	@Override
	public void remove(final Loan loan) {
		interestSeries.setData(FXCollections.emptyObservableList());
		redemptionSeries.setData(FXCollections.emptyObservableList());
	}

	/** the interest {@link Series}. */
	private final Series<String, Number> interestSeries;
	/** the redemption {@link Series}. */
	private final Series<String, Number> redemptionSeries;

}
