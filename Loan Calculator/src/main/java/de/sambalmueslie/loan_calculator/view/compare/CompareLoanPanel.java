/**
 *
 */
package de.sambalmueslie.loan_calculator.view.compare;

import java.util.List;

import javafx.scene.layout.BorderPane;
import de.sambalmueslie.loan_calculator.model.Loan;
import de.sambalmueslie.loan_calculator.view.chart.ResidualDebtChart;

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

		final ResidualDebtChart residualDebtChart = new ResidualDebtChart();
		loans.forEach(l -> residualDebtChart.add(l));

		setCenter(residualDebtChart);

	}

}
