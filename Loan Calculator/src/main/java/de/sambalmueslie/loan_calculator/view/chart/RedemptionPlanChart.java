package de.sambalmueslie.loan_calculator.view.chart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import de.sambalmueslie.loan_calculator.model.Loan;
import de.sambalmueslie.loan_calculator.model.Redemption;

/**
 * The chart for the redemption plan.
 *
 * @author sambalmueslie 2015
 */
public class RedemptionPlanChart extends AreaChart<Number, Number> {

	/**
	 * Constructor.
	 */
	public RedemptionPlanChart() {
		super(new NumberAxis(), new NumberAxis());
		setTitle("Redemption plan");
		setAnimated(false);
		setLegendSide(Side.BOTTOM);
	}

	/**
	 * Add a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	public void add(final Loan loan) {
		if (loan == null || data.containsKey(loan)) return;
		final ObservableList<Data<Number, Number>> values = FXCollections.observableArrayList();
		final List<Redemption> redemptionPlan = loan.getRedemptionPlan();
		for (int i = 0; i < redemptionPlan.size(); i++) {
			final Redemption redemption = redemptionPlan.get(i);
			values.add(new Data<Number, Number>(i, redemption.getResidualDebt()));
		}
		final Series<Number, Number> series = new Series<>(values);
		final String name = loan.getName();
		series.setName(name);
		data.put(loan, series);
		getData().add(series);
	}

	/**
	 * Remove a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	public void remove(final Loan loan) {
		final Series<Number, Number> series = data.remove(loan);
		if (series != null) {
			getData().remove(series);
		}
	}

	/** the {@link Series} by {@link Loan}. */
	private final Map<Loan, Series<Number, Number>> data = new HashMap<>();

}
