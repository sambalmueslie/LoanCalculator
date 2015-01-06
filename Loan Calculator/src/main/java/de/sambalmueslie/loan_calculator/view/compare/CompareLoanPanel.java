/**
 *
 */
package de.sambalmueslie.loan_calculator.view.compare;

import java.util.List;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import de.sambalmueslie.loan_calculator.model.Loan;
import de.sambalmueslie.loan_calculator.view.Constants;
import de.sambalmueslie.loan_calculator.view.chart.LoanChart;
import de.sambalmueslie.loan_calculator.view.chart.LoanChartFactory;

/**
 * The compare {@link Loan} panel.
 *
 * @author sambalmueslie 2015
 */
public class CompareLoanPanel extends BorderPane {

	/**
	 * Constructor.
	 */
	public CompareLoanPanel(final List<Loan> loans) {

		final LoanChart residualDebtChart = LoanChartFactory.createResidualDebtChart();
		loans.forEach(l -> residualDebtChart.add(l));

		final TilePane box = new TilePane();
		box.setHgap(Constants.DEFAULT_SPACING);
		box.setVgap(Constants.DEFAULT_SPACING);
		box.setPrefColumns(2);

		for (final Loan loan : loans) {
			final LoanChart chart = LoanChartFactory.createAnnuityPlanChart();
			chart.add(loan);
			box.getChildren().add(chart.getChart());
		}

		setCenter(residualDebtChart.getChart());
		setBottom(box);
	}

}
