/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart.founding;

import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.view.Constants;
import de.sambalmueslie.loan_calculator.view.chart.Chart;

/**
 * A generic {@link Founding} chart.
 *
 * @author sambalmueslie 2015
 */
public abstract class FoundingChart extends TilePane implements Chart<Founding> {

	/**
	 * Constructor.
	 */
	public FoundingChart() {
		setHgap(Constants.DEFAULT_SPACING);
		setVgap(Constants.DEFAULT_SPACING);
		setPrefColumns(2);

		setStyle("-fx-border-color: lightgray;");
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.chart.Chart#add(java.lang.Object)
	 */
	@Override
	public final void add(final Founding founding) {
		getChildren().add(createChart(founding));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.chart.Chart#getChart()
	 */
	@Override
	public final Node getChart() {
		return this;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.chart.Chart#remove(java.lang.Object)
	 */
	@Override
	public final void remove(final Founding founding) {
		getChildren().removeIf(c -> equals(c, founding));
	}

	/**
	 * Create a chart for a {@link Founding}.
	 *
	 * @param founding
	 *            the founding
	 * @return the chart
	 */
	protected abstract Node createChart(final Founding founding);

	/**
	 * Check if the {@link Node} is equals to the {@link Founding}.
	 *
	 * @param node
	 *            the node to check
	 * @param founding
	 *            the {@link Founding}
	 * @return <code>true</code> if so,otherwise <code>false</code>
	 */
	protected abstract boolean equals(Node node, Founding founding);

}
