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

	protected abstract Node createChart(final Founding founding);

	protected abstract boolean equals(Node node, Founding founding);

}
