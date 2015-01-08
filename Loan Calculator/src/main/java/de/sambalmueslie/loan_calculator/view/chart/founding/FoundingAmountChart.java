/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart.founding;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.Constants;
import de.sambalmueslie.loan_calculator.view.chart.Chart;

/**
 * The founding amount {@link PieChart}.
 *
 * @author sambalmueslie 2015
 */
public class FoundingAmountChart extends TilePane implements Chart<Founding> {

	/**
	 * The chart for a single {@link Founding}.
	 *
	 * @author sambalmueslie 2015
	 */
	private class Chart extends PieChart {
		/**
		 * Constructor.
		 */
		public Chart(final Founding founding) {
			this.founding = founding;

			setTitle("Total Amount");
			setAnimated(false);
			setLegendVisible(true);
			setLegendSide(Side.BOTTOM);

			final ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
			for (final Node node : lookupAll("Text.chart-pie-label")) {
				if (node instanceof Text) {
					for (final PieChart.Data data : pieChartData) {
						if (data.getName().equals(((Text) node).getText())) {
							((Text) node).setText(String.format("%,.0f", data.getPieValue()));
						}
					}
				}
			}

			for (final Loan loan : founding.getLoans()) {
				final String name = loan.getName();
				final double value = loan.getTotalPayment();
				pieChartData.add(new PieChart.Data(name, value));
			}

			setData(pieChartData);
		}

		/** the {@link Loan}. */
		private final Founding founding;
	}

	/**
	 * Constructor.
	 */
	public FoundingAmountChart() {
		setHgap(Constants.DEFAULT_SPACING);
		setVgap(Constants.DEFAULT_SPACING);
		setPrefColumns(2);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.chart.Chart#add(java.lang.Object)
	 */
	@Override
	public void add(final Founding founding) {
		getChildren().add(new Chart(founding));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.chart.Chart#getChart()
	 */
	@Override
	public Node getChart() {
		return this;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.chart.Chart#remove(java.lang.Object)
	 */
	@Override
	public void remove(final Founding founding) {
		getChildren().removeIf(c -> ((Chart) c).founding.getId() == founding.getId());
	}

}
