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
	 * @return a new founding amount {@link Chart}.
	 */
	public static Chart<Founding> createFoundingAmountChart() {
		return new ChartController<Founding>(new FoundingAmountChart());
	}

	/**
	 * Constructor.
	 */
	private FoundingChartFactory() {
		// intentionally left empty
	}

}
