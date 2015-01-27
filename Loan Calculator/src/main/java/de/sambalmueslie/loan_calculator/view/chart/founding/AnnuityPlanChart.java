/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart.founding;

import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.loan.RedemptionPlanEntry;
import de.sambalmueslie.loan_calculator.view.chart.LineChartSeriesDefinition;
import de.sambalmueslie.loan_calculator.view.chart.generic.GenericStackedBarChart;
import de.sambalmueslie.loan_calculator.view.i18n.I18nPropertiesHandler;

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
		super(I18nPropertiesHandler.getString(I18nPropertiesHandler.ANNUITY_PLAN_CHART_TITLE), true, new LineChartSeriesDefinition<>(
				I18nPropertiesHandler.getString(I18nPropertiesHandler.TEXT_INTEREST), RedemptionPlanEntry::getInterest, Founding::getRedemptionPlan),
				new LineChartSeriesDefinition<>(I18nPropertiesHandler.getString(I18nPropertiesHandler.TEXT_REDEMPTION), RedemptionPlanEntry::getRedemption,
						Founding::getRedemptionPlan));
		add(founding);
	}
}
