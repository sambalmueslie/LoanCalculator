/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart.loan;

import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.chart.Chart;
import de.sambalmueslie.loan_calculator.view.chart.ChartController;

/**
 * The factory for the {@link Chart<Loan>}.
 *
 * @author sambalmueslie 2015
 */
public final class LoanChartFactory {

	/**
	 * @return the annuity plan {@link Chart<Loan>}.
	 */
	public static Chart<Loan> createAnnuityPlanChart() {
		return new ChartController<Loan>(new AnnuityPlanChart());
	}

	/**
	 * @return a residual debt {@link Chart<Loan>}.
	 */
	public static Chart<Loan> createResidualDebtChart() {
		return new ChartController<Loan>(new ResidualDebtChart());
	}

	/**
	 * Constructor.
	 */
	private LoanChartFactory() {
		// intentionally left empty
	}
}
