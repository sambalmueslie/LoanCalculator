/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.chart;

import java.util.function.Function;

/**
 * A series definition.
 *
 * @author sambalmueslie 2015
 */
public class SeriesDefinition<T, R> {
	/**
	 * Constructor.
	 *
	 * @param title
	 *            {@link #title}
	 * @param function
	 *            {@link #function}
	 */
	public SeriesDefinition(final String title, final Function<T, R> function) {
		this.title = title;
		this.function = function;
	}

	/**
	 * @return the {@link #function}
	 */
	public Function<T, R> getFunction() {
		return function;
	}

	/**
	 * @return the {@link #title}
	 */
	public String getTitle() {
		return title;
	}

	/** the function to use for the chart. */
	private final Function<T, R> function;
	/** the series title. */
	private final String title;
}
