/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart.founding;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.text.Text;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.Constants;

/**
 * The founding amount {@link PieChart}.
 *
 * @author sambalmueslie 2015
 */
public class InterestChart extends FoundingChart {

	/**
	 * The chart for a single {@link Founding}.
	 *
	 * @author sambalmueslie 2015
	 */
	protected class Chart extends PieChart {
		/**
		 * Constructor.
		 */
		public Chart(final Founding founding) {
			this.founding = founding;

			setTitle("Total interest");
			setAnimated(false);
			setLegendVisible(true);
			setLegendSide(Side.BOTTOM);

			final ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
			for (final Loan loan : founding.getLoans()) {
				final String name = loan.getName();
				final double value = loan.getTotalInterest();
				pieChartData.add(new PieChart.Data(name, value));
			}
			setData(pieChartData);

			for (final Node node : lookupAll("Text.chart-pie-label")) {
				if (node instanceof Text) {
					for (final PieChart.Data data : pieChartData) {
						if (data.getName().equals(((Text) node).getText())) {
							((Text) node).setText(String.format("%,.2f " + Constants.DEFAULT_CURRENCY, data.getPieValue()));
						}
					}
				}
			}
		}

		/** the {@link Loan}. */
		private final Founding founding;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.chart.founding.FoundingChart#createChart(de.sambalmueslie.loan_calculator.model.founding.Founding)
	 */
	@Override
	protected Node createChart(final Founding founding) {
		return new Chart(founding);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.chart.founding.FoundingChart#equals(javafx.scene.Node,
	 *      de.sambalmueslie.loan_calculator.model.founding.Founding)
	 */
	@Override
	protected boolean equals(final Node node, final Founding founding) {
		if (node instanceof Chart) return ((Chart) node).founding.getId() == founding.getId();
		return false;
	}

}
