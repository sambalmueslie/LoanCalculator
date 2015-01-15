/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;

/**
 * A generic {@link BarChart}.
 *
 * @author sambalmueslie 2015
 */
public class GenericBarChart<T extends GenericModelEntry> extends BarChart<String, Number> {
	/**
	 * Constructor.
	 *
	 * @param function
	 *            the function to call on every {@link Founding}
	 * @param title
	 *            the title
	 */
	public GenericBarChart(final Function<T, Number> function, final String title) {
		super(new CategoryAxis(), new NumberAxis());
		this.function = function;

		setTitle(title);
		setAnimated(false);
		getXAxis().setVisible(false);
		setLegendVisible(true);
		setLegendSide(Side.BOTTOM);
		setPrefWidth(250);

		setStyle("-fx-border-color: lightgray;");
	}

	/**
	 * Add a entry.
	 *
	 * @param entry
	 *            the entry
	 */
	@SuppressWarnings("unchecked")
	public void add(final T entry) {
		if (entry == null) return;
		final String name = entry.getName();
		final Number value = function.apply(entry);

		final ObservableList<Data<String, Number>> data = FXCollections.observableArrayList();
		data.add(new Data<String, Number>(getTitle(), value));
		final Series<String, Number> series = new Series<>(data);
		series.setName(name);
		getData().add(series);
		seriesMap.put(entry.getId(), series);

		entry.register(e -> update((T) e));
	}

	/**
	 * Update.
	 *
	 * @param entry
	 *            the entry
	 */
	public void update(final T entry) {
		final Number value = function.apply(entry);

		final Series<String, Number> series = seriesMap.get(entry.getId());
		final ObservableList<Data<String, Number>> data = FXCollections.observableArrayList();
		data.add(new Data<String, Number>(getTitle(), value));
		series.setData(data);
	}

	/** the function to use for the chart. */
	private final Function<T, Number> function;
	/** the {@link Series} by entry id. */
	private final Map<Long, Series<String, Number>> seriesMap = new HashMap<>();
}
