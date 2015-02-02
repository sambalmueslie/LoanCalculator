/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.chart.generic;

import java.util.*;

import javafx.geometry.Side;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import de.sambalmueslie.loan_calculator.backend.common.BusinessObject;
import de.sambalmueslie.loan_calculator.frontend.chart.LineChartSeriesDefinition;

/**
 * A generic {@link StackedBarChart}.
 *
 * @author sambalmueslie 2015
 */
public class GenericStackedBarChart<ENTRY extends BusinessObject, DATA> extends StackedBarChart<String, Number> {

	/**
	 * Constructor.
	 *
	 * @param title
	 *            the title
	 * @param seriesDefinition
	 *            the {@link LineChartSeriesDefinition}
	 * @param ignoreFirstElement
	 *            {@link #ignoreFirstElement}
	 */
	@SafeVarargs
	public GenericStackedBarChart(final String title, final boolean ignoreFirstElement, final LineChartSeriesDefinition<ENTRY, DATA>... seriesDefinition) {
		super(new CategoryAxis(), new NumberAxis());
		seriesDefinitions = new LinkedList<LineChartSeriesDefinition<ENTRY, DATA>>(Arrays.asList(seriesDefinition));
		this.ignoreFirstElement = ignoreFirstElement;

		setTitle(title);
		setAnimated(false);
		setLegendVisible(true);
		setLegendSide(Side.BOTTOM);

		series = new LinkedHashMap<>();
		for (final LineChartSeriesDefinition<ENTRY, DATA> definition : seriesDefinitions) {
			final Series<String, Number> s = new Series<>();
			s.setName(definition.getTitle());
			series.put(definition, s);
		}
		// add series
		getData().addAll(series.values());
	}

	/**
	 * Constructor.
	 *
	 * @param title
	 *            the title
	 * @param seriesDefinition
	 *            the {@link LineChartSeriesDefinition}
	 */
	@SafeVarargs
	public GenericStackedBarChart(final String title, final LineChartSeriesDefinition<ENTRY, DATA>... seriesDefinition) {
		this(title, false, seriesDefinition);
	}

	/**
	 * Add a entry.
	 *
	 * @param entry
	 *            the entry
	 */
	public void add(final ENTRY entry) {
		if (entry == null) return;

		for (final LineChartSeriesDefinition<ENTRY, DATA> definition : seriesDefinitions) {
			final List<DATA> datas = new LinkedList<>(definition.getDataGetterFunction().apply(entry));
			final int offset = (ignoreFirstElement) ? 1 : 0;
			for (int i = offset; i < datas.size(); i++) {
				final String name = i + "";
				final DATA data = datas.get(i);
				final Number value = definition.getFunction().apply(data);
				series.get(definition).getData().add(new Data<String, Number>(name, value));
			}
		}
	}

	/** ignore the first data element. */
	private final boolean ignoreFirstElement;
	/** the series. */
	private final Map<LineChartSeriesDefinition<ENTRY, DATA>, Series<String, Number>> series;
	/** the series definitions. */
	private final List<LineChartSeriesDefinition<ENTRY, DATA>> seriesDefinitions;
}
