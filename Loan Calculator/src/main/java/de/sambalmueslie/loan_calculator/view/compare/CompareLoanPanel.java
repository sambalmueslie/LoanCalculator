/**
 *
 */
package de.sambalmueslie.loan_calculator.view.compare;

import java.util.List;

import javafx.scene.layout.BorderPane;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
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

		final LoanChart anuityPlanChart = LoanChartFactory.createAnnuityPlanChart();
		loans.forEach(l -> anuityPlanChart.add(l));

		setCenter(residualDebtChart.getChart());
		setBottom(anuityPlanChart.getChart());
	}

}
