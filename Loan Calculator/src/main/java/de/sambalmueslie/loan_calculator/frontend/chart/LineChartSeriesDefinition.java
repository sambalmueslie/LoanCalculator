package de.sambalmueslie.loan_calculator.frontend.chart;

import java.util.Collection;
import java.util.function.Function;

import javafx.scene.chart.LineChart;

/**
 * The {@link SeriesDefinition} for a {@link LineChart}.
 *
 * @param <ENTRY>
 *            the entry type
 * @param <DATA>
 *            the data type
 * @author sambalmueslie 2015
 */
public class LineChartSeriesDefinition<ENTRY, DATA> extends SeriesDefinition<DATA, Number> {

	/**
	 * Constructor.
	 *
	 * @param title
	 *            the series title
	 * @param valueGetterFunction
	 *            the value getter {@link Function}
	 * @param dataGetterFunction
	 *            the data getter {@link Function}
	 */
	public LineChartSeriesDefinition(final String title, final Function<DATA, Number> valueGetterFunction,
			final Function<ENTRY, Collection<DATA>> dataGetterFunction) {
		super(title, valueGetterFunction);
		this.dataGetterFunction = dataGetterFunction;
	}

	/**
	 * @return the {@link #dataGetterFunction}
	 */
	public Function<ENTRY, Collection<DATA>> getDataGetterFunction() {
		return dataGetterFunction;
	}

	/** the data getter {@link Function}. */
	private final Function<ENTRY, Collection<DATA>> dataGetterFunction;

}