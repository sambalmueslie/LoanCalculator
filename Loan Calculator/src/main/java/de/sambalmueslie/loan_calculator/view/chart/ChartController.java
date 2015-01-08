/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;

/**
 * The chart controller.
 *
 * @author sambalmueslie 2015
 */
public class ChartController<T extends GenericModelEntry<T>> implements Chart<T> {

	/**
	 * Constructor.
	 *
	 * @param {@link #chart}
	 */
	public ChartController(final Chart<T> chart) {
		this.chart = chart;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.chart.Chart#add(java.lang.Object)
	 */
	@Override
	public void add(final T entry) {
		if (entry == null || entries.containsKey(entry.getId())) return;
		entries.put(entry.getId(), entry);
		entry.register(this::update);
		chart.add(entry);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.chart.Chart#getChart()
	 */
	@Override
	public Node getChart() {
		return chart.getChart();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.chart.Chart#remove(java.lang.Object)
	 */
	@Override
	public void remove(final T entry) {
		if (entry == null || !entries.containsKey(entry.getId())) return;
		entries.remove(entry.getId());
		entry.unregister(this::update);
		chart.remove(entry);
	}

	/**
	 * Handle the update of an entry.
	 *
	 * @param entry
	 */
	private void update(final T entry) {
		chart.remove(entry);
		chart.add(entry);
	}

	/** the wrapped chart. */
	private final Chart<T> chart;
	/** the currently displayed entries by id. */
	private final Map<Long, T> entries = new HashMap<>();

}