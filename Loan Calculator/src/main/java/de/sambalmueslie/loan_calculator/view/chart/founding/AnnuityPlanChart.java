/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart.founding;

import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.loan.RedemptionPlanEntry;
import de.sambalmueslie.loan_calculator.view.chart.SeriesDefinition;
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
		super(founding, "Annuity plan", Founding::getRedemptionPlan, new SeriesDefinition<>("interest", RedemptionPlanEntry::getInterest),
				new SeriesDefinition<>("redemption", RedemptionPlanEntry::getRedemption));
	}
}
