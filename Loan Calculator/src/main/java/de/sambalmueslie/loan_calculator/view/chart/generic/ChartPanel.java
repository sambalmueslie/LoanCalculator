/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart.generic;

import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import de.sambalmueslie.loan_calculator.view.Constants;

/**
 * A chart panel.
 *
 * @author sambalmueslie 2015
 */
public class ChartPanel extends TilePane {

	/**
	 * Constructor.
	 */
	public ChartPanel() {
		super();
		setHgap(Constants.DEFAULT_SPACING);
		setVgap(Constants.DEFAULT_SPACING);
		setPrefColumns(2);

		setStyle("-fx-border-color: lightgray;");
	}

	/**
	 * Constructor.
	 * 
	 * @param nodes
	 *            the nodes
	 */
	public ChartPanel(final Node... nodes) {
		this();
		getChildren().addAll(nodes);
	}

}
