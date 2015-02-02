/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.chart.founding;

import de.sambalmueslie.loan_calculator.backend.founding_mgt.Founding;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.RedemptionPlanEntry;
import de.sambalmueslie.loan_calculator.frontend.chart.LineChartSeriesDefinition;
import de.sambalmueslie.loan_calculator.frontend.chart.generic.GenericStackedBarChart;
import de.sambalmueslie.loan_calculator.frontend.i18n.I18n;

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
		super(I18n.get(I18n.ANNUITY_PLAN_CHART_TITLE), true, new LineChartSeriesDefinition<>(I18n.get(I18n.TEXT_INTEREST), RedemptionPlanEntry::getInterest,
				Founding::getRedemptionPlan), new LineChartSeriesDefinition<>(I18n.get(I18n.TEXT_REDEMPTION), RedemptionPlanEntry::getRedemption,
				Founding::getRedemptionPlan));
		add(founding);
	}

}
