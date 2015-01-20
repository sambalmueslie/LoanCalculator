/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart.founding;

import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.loan.RedemptionPlanEntry;
import de.sambalmueslie.loan_calculator.view.chart.LineChartSeriesDefinition;
import de.sambalmueslie.loan_calculator.view.chart.generic.GenericStackedBarChart;

/**
 * The annuity plan chart.
 *
 * @author sambalmueslie 2015
 */
public class AnnuityPlanChart extends GenericStackedBarChart<Founding, RedemptionPlanEntry> {

	/**
	 * Constructor.
	 */
	public AnnuityPlanChart(final Founding founding) {
		super("Annuity plan", true, new LineChartSeriesDefinition<>("interest", RedemptionPlanEntry::getInterest, Founding::getRedemptionPlan),
				new LineChartSeriesDefinition<>("redemption", RedemptionPlanEntry::getRedemption, Founding::getRedemptionPlan));
		add(founding);
	}
}
