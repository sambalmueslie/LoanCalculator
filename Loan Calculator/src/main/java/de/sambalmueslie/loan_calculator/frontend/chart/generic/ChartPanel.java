/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.chart.generic;

import static de.sambalmueslie.loan_calculator.frontend.Constants.CLASS_PANEL_BORDER;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;

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
		getStyleClass().add(CLASS_PANEL_BORDER);
		setPrefColumns(2);
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
