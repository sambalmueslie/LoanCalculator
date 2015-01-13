/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart.generic;

import java.util.Collection;
import java.util.function.Function;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.text.Text;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;
import de.sambalmueslie.loan_calculator.view.Constants;

/**
 * A generic {@link PieChart}.
 *
 * @author sambalmueslie 2015
 */
public class GenericPieChart<T extends GenericModelEntry<T>, R extends GenericModelEntry<R>> extends PieChart {
	/**
	 * Constructor.
	 *
	 * @param dataGetterFunction
	 *            the data getter {@link Function}
	 * @param valueFunction
	 *            the function to call on every {@link T}
	 * @param title
	 *            the title
	 */
	public GenericPieChart(final Function<T, Collection<R>> dataGetterFunction, final Function<R, Double> valueFunction, final String title) {
		this.dataGetterFunction = dataGetterFunction;
		this.valueFunction = valueFunction;

		setTitle(title);
		setAnimated(false);
		setLegendVisible(true);
		setLegendSide(Side.BOTTOM);
		setPrefWidth(250);
		setData(data);

		setStyle("-fx-border-color: lightgray;");
	}

	/**
	 * Add a {@link T}.
	 *
	 * @param entry
	 *            the entry
	 */
	public void add(final T entry) {
		if (entry == null) return;
		for (final R loan : dataGetterFunction.apply(entry)) {
			final String name = loan.getName();
			final Double value = valueFunction.apply(loan);
			data.add(new PieChart.Data(name, value));
		}

		for (final Node node : lookupAll("Text.chart-pie-label")) {
			if (node instanceof Text) {
				for (final PieChart.Data d : data) {
					if (d.getName().equals(((Text) node).getText())) {
						((Text) node).setText(String.format("%,.2f " + Constants.DEFAULT_CURRENCY, d.getPieValue()));
					}
				}
			}
		}
		entry.register(this::update);
	}

	/**
	 * Update.
	 *
	 * @param entry
	 *            the {@link T}.
	 */
	private void update(final T entry) {
		data.clear();
		for (final R loan : dataGetterFunction.apply(entry)) {
			final String name = loan.getName();
			final Double value = valueFunction.apply(loan);
			data.add(new PieChart.Data(name, value));
		}

		for (final Node node : lookupAll("Text.chart-pie-label")) {
			if (node instanceof Text) {
				for (final PieChart.Data d : data) {
					if (d.getName().equals(((Text) node).getText())) {
						((Text) node).setText(String.format("%,.2f " + Constants.DEFAULT_CURRENCY, d.getPieValue()));
					}
				}
			}
		}

	}

	/** the data. */
	private final ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
	/** the data getter function. */
	private final Function<T, Collection<R>> dataGetterFunction;
	/** the function to use for the chart. */
	private final Function<R, Double> valueFunction;
}
