/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart.founding;

import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.view.chart.Chart;
import de.sambalmueslie.loan_calculator.view.chart.ChartController;

/**
 * The factory for the {@link FoundingChart}.
 *
 * @author sambalmueslie 2015
 */
public final class FoundingChartFactory {

	/**
	 * @return a new annuity plan {@link Chart}.
	 */
	public static Chart<Founding> createAnnuityPlanChart() {
		return new ChartController<Founding>(new AnnuityPlanChart());
	}

	/**
	 * @return a new redemption plan {@link Chart}.
	 */
	public static Chart<Founding> createRedemptionPlanChart() {
		return new ChartController<Founding>(new RedemptionPlanChart());
	}

	/**
	 * Constructor.
	 */
	private FoundingChartFactory() {
		// intentionally left empty
	}

}
