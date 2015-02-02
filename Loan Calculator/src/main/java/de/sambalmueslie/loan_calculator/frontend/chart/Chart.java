/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.chart;

import javafx.scene.Node;

/**
 * A chart.
 *
 * @author sambalmueslie 2015
 * @param <T>
 *            the type to display
 */
public interface Chart<T> {
	/**
	 * Add a entry.
	 *
	 * @param entry
	 *            the entry
	 */
	void add(T entry);

	/**
	 * @return the {@link Node}.
	 */
	Node getChart();

	/**
	 * Remove a entry.
	 *
	 * @param entry
	 *            the entry
	 */
	void remove(T entry);
}
