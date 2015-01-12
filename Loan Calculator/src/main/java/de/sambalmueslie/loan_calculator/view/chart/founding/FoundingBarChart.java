/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart.founding;

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

/**
 * The bar chart for the {@link Founding}.
 *
 * @author sambalmueslie 2015
 */
public class FoundingBarChart extends BarChart<String, Number> {

	/**
	 * Constructor.
	 *
	 * @param function
	 *            the function to call on every {@link Founding}
	 * @param title
	 *            the title
	 */
	public FoundingBarChart(final Function<Founding, Number> function, final String title) {
		super(new CategoryAxis(), new NumberAxis());
		this.function = function;

		setTitle(title);
		setAnimated(false);
		getXAxis().setVisible(false);
		setLegendVisible(true);
		setLegendSide(Side.BOTTOM);
		setPrefWidth(250);
	}

	/**
	 * Add a {@link Founding}.
	 *
	 * @param founding
	 *            the founding
	 */
	public void add(final Founding founding) {
		if (founding == null) return;
		final String name = founding.getName();
		final Number value = function.apply(founding);

		final ObservableList<Data<String, Number>> data = FXCollections.observableArrayList();
		data.add(new Data<String, Number>(getTitle(), value));
		final Series<String, Number> series = new Series<>(data);
		series.setName(name);
		getData().add(series);
		seriesMap.put(founding.getId(), series);

		founding.register(this::update);
	}

	/**
	 * Update.
	 *
	 * @param founding
	 *            the {@link Founding}.
	 */
	private void update(final Founding founding) {
		final Number value = function.apply(founding);

		final Series<String, Number> series = seriesMap.get(founding.getId());

		final ObservableList<Data<String, Number>> data = FXCollections.observableArrayList();
		data.add(new Data<String, Number>(getTitle(), value));
		series.setData(data);

	}

	/** the function to use for the chart. */
	private final Function<Founding, Number> function;
	/** the {@link Series} by founding id. */
	private final Map<Long, Series<String, Number>> seriesMap = new HashMap<>();

}
