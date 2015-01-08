/**
 *
 */
package de.sambalmueslie.loan_calculator.view.compare;

import java.util.List;

import javafx.scene.layout.BorderPane;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.chart.Chart;
import de.sambalmueslie.loan_calculator.view.chart.loan.LoanChartFactory;

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

		final Chart<Loan> residualDebtChart = LoanChartFactory.createResidualDebtChart();
		loans.forEach(l -> residualDebtChart.add(l));

		final Chart<Loan> anuityPlanChart = LoanChartFactory.createAnnuityPlanChart();
		loans.forEach(l -> anuityPlanChart.add(l));

		setCenter(residualDebtChart.getChart());
		setBottom(anuityPlanChart.getChart());
	}

}
