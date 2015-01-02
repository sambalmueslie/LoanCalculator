package de.sambalmueslie.loan_calculator.view.chart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import de.sambalmueslie.loan_calculator.model.Loan;

public class LoanProcessChart extends LineChart<Number, Number> {

	/**
	 * Constructor.
	 */
	public LoanProcessChart() {
		super(new NumberAxis(), new NumberAxis());
		setTitle("Loan process");
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
		if (loan == null || data.containsKey(loan)) {
			return;
		}
		ObservableList<Data<Number, Number>> values = FXCollections.observableArrayList();
		List<Double> redemptionPlan = loan.getMonthlyPayment();
		for (int i = 0; i < redemptionPlan.size(); i++) {
			values.add(new Data<Number, Number>(i, redemptionPlan.get(i)));
		}
		Series<Number, Number> series = new Series<>(values);
		String name = loan.getName();
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
		Series<Number, Number> series = data.remove(loan);
		if (series != null) {
			getData().remove(series);
		}
	}

	/** the {@link Series} by {@link Loan}. */
	private final Map<Loan, Series<Number, Number>> data = new HashMap<>();

}
