/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart;

/**
 * The factory for the {@link LoanChart}.
 *
 * @author sambalmueslie 2015
 */
public class LoanChartFactory {

	/**
	 * @return a annuity {@link LoanChart}.
	 */
	public static LoanChart createAnnuityCart() {
		return new LoanChartController(new AnnuityCart());
	}

	/**
	 * @return the annuity plan {@link LoanChart}.
	 */
	public static LoanChart createAnnuityPlanChart() {
		return new LoanChartController(new AnnuityPlanChart());
	}

	/**
	 * @return a residual debt {@link LoanChart}.
	 */
	public static LoanChart createResidualDebtChart() {
		return new LoanChartController(new ResidualDebtChart());
	}

	/**
	 * @return a total amount {@link LoanChart}.
	 */
	public static LoanChart createTotalAmountChart() {
		return new LoanChartController(new TotalAmountChart());
	}
}
