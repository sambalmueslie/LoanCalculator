/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.chart.loan;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.geometry.Side;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import de.sambalmueslie.loan_calculator.backend.common.BusinessObject;
import de.sambalmueslie.loan_calculator.frontend.chart.SeriesDefinition;

/**
 * A generic {@link StackedBarChart} for the annuity.
 *
 * @author sambalmueslie 2015
 * @param <T>
 *            the entry type
 * @param <D>
 *            the data type
 * @param <R>
 *            the data value
 */
public class GenericAnnuityChart<T extends BusinessObject, D, R extends Number> extends StackedBarChart<String, Number> {

	/**
	 * Constructor.
	 *
	 * @param title
	 *            the title
	 * @param dataGetterFunction
	 *            the getter for the data
	 * @param entries
	 *            the entries
	 * @param seriesDefinitions
	 *            the {@link SeriesDefinition}
	 */
	@SafeVarargs
	public GenericAnnuityChart(final String title, final Function<T, D> dataGetterFunction, final Collection<T> entries,
			final SeriesDefinition<D, R>... seriesDefinitions) {
		super(new CategoryAxis(), new NumberAxis());
		setTitle(title);
		setAnimated(false);
		setLegendVisible(true);
		setLegendSide(Side.BOTTOM);
		setPrefWidth(200);

		setStyle("-fx-border-color: lightgray;");

		this.dataGetterFunction = dataGetterFunction;

		definitions = Arrays.asList(seriesDefinitions);
		final Stream<SeriesDefinition<D, R>> stream = definitions.stream();
		stream.map(sd -> new Series<String, Number>(sd.getTitle(), FXCollections.observableArrayList())).forEach(s -> getData().add(s));
		entries.forEach(this::add);
	}

	/**
	 * Add a entry.
	 *
	 * @param entry
	 *            the entry
	 */
	private void add(final T entry) {
		final String name = entry.getName();
		final D data = dataGetterFunction.apply(entry);

		for (final SeriesDefinition<D, R> definition : definitions) {
			final Optional<Series<String, Number>> optional = getData().stream().filter(s -> s.getName().equals(definition.getTitle())).findFirst();
			final Series<String, Number> series = optional.get();
			final Number value = definition.getFunction().apply(data);
			series.getData().add(new Data<String, Number>(name, value));
		}

	}

	/** the data getter {@link Function}. */
	private final Function<T, D> dataGetterFunction;
	/** the {@link SeriesDefinition}s. */
	private final List<SeriesDefinition<D, R>> definitions;
}
