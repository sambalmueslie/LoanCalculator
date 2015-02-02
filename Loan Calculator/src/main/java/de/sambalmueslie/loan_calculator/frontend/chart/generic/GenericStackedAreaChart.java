/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.chart.generic;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import javafx.geometry.Side;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.StackedBarChart;
import de.sambalmueslie.loan_calculator.backend.common.BusinessObject;
import de.sambalmueslie.loan_calculator.frontend.chart.SeriesDefinition;

/**
 * A generic {@link StackedBarChart}.
 *
 * @author sambalmueslie 2015
 */
public class GenericStackedAreaChart<T extends BusinessObject, R> extends StackedAreaChart<Number, Number> {

	/**
	 * Constructor.
	 *
	 * @param entry
	 *            the entry to get the data from
	 * @param title
	 *            the title
	 * @param dataGetterFunction
	 *            the data getter {@link Function}
	 * @param seriesDefinition
	 *            the {@link SeriesDefinition}
	 */
	@SafeVarargs
	public GenericStackedAreaChart(final T entry, final String title, final Function<T, List<R>> dataGetterFunction,
			final SeriesDefinition<R, Number>... seriesDefinition) {
		super(new NumberAxis(), new NumberAxis());

		setTitle(title);
		setAnimated(false);
		setLegendVisible(true);
		setLegendSide(Side.BOTTOM);

		// create the series
		final Map<SeriesDefinition<R, Number>, Series<Number, Number>> series = new LinkedHashMap<>();
		for (final SeriesDefinition<R, Number> definition : seriesDefinition) {
			final Series<Number, Number> s = new Series<>();
			s.setName(definition.getTitle());
			series.put(definition, s);
		}

		// setup data
		final List<R> dataList = dataGetterFunction.apply(entry);
		for (int i = 1; i < dataList.size(); i++) {
			final R data = dataList.get(i);
			for (final Entry<SeriesDefinition<R, Number>, Series<Number, Number>> e : series.entrySet()) {
				final Series<Number, Number> s = e.getValue();
				final SeriesDefinition<R, Number> d = e.getKey();
				final Number value = d.getFunction().apply(data);
				s.getData().add(new Data<Number, Number>(i, value));
			}
		}

		// add series
		getData().addAll(series.values());

	}
}
