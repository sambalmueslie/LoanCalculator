/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.chart.generic;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import de.sambalmueslie.loan_calculator.frontend.chart.LineChartSeriesDefinition;

/**
 * The generic line chart.
 *
 * @author sambalmueslie 2015
 */
public class GenericLineChart<ENTRY, DATA> extends LineChart<Number, Number> {

	/**
	 * Constructor.
	 *
	 * @param title
	 *            the title
	 * @param seriesDefinition
	 *            the {@link LineChartSeriesDefinition}
	 */
	@SafeVarargs
	public GenericLineChart(final String title, final LineChartSeriesDefinition<ENTRY, DATA>... seriesDefinition) {
		super(new NumberAxis(), new NumberAxis());
		this.seriesDefinitions = new LinkedList<LineChartSeriesDefinition<ENTRY, DATA>>(Arrays.asList(seriesDefinition));

		setTitle(title);
		setAnimated(false);
		setLegendSide(Side.BOTTOM);
	}

	/**
	 * Add a entry.
	 *
	 * @param entry
	 *            the entry
	 */
	public void add(final ENTRY entry) {
		if (entry == null) {
			return;
		}
		for (final LineChartSeriesDefinition<ENTRY, DATA> definition : seriesDefinitions) {
			final ObservableList<Data<Number, Number>> data = FXCollections.observableArrayList();
			final Function<ENTRY, Collection<DATA>> dataGetterFunction = definition.getDataGetterFunction();
			final List<Number> values = dataGetterFunction.apply(entry).stream().map(definition.getFunction()::apply).collect(Collectors.toList());
			for (int i = 0; i < values.size(); i++) {
				data.add(new Data<Number, Number>(i, values.get(i)));
			}
			final String name = definition.getTitle();
			final Series<Number, Number> series = new Series<>(name, data);
			getData().add(series);
		}
	}

	/** the series definitions. */
	private final List<LineChartSeriesDefinition<ENTRY, DATA>> seriesDefinitions;
}
