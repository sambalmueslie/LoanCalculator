/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart.loan;

import java.util.Collection;
import java.util.function.Function;

import de.sambalmueslie.loan_calculator.model.loan.AnnuityLoan;
import de.sambalmueslie.loan_calculator.model.loan.BuildingLoanAgreement;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.model.loan.RedemptionPlanEntry;
import de.sambalmueslie.loan_calculator.view.chart.LineChartSeriesDefinition;
import de.sambalmueslie.loan_calculator.view.chart.generic.GenericStackedBarChart;
import de.sambalmueslie.loan_calculator.view.i18n.I18n;

/**
 * A loan chart factory.
 *
 * @author sambalmueslie 2015
 */
public class LoanChartFactory {
	/**
	 * Create a annuity plan chart for a {@link AnnuityLoan}.
	 *
	 * @param loan
	 *            the {@link AnnuityLoan}
	 * @return the chart
	 */
	public static final GenericStackedBarChart<AnnuityLoan, RedemptionPlanEntry> createAnnuityPlanChart(final AnnuityLoan loan) {
		return createLoanAnnuityPlanChart(loan);
	}

	/**
	 * Create a annuity plan chart for a {@link BuildingLoanAgreement}.
	 *
	 * @param loan
	 *            the {@link BuildingLoanAgreement}
	 * @return the chart
	 */
	public static final GenericStackedBarChart<BuildingLoanAgreement, RedemptionPlanEntry> createAnnuityPlanChart(final BuildingLoanAgreement loan) {
		return createLoanAnnuityPlanChart(loan);
	}

	/**
	 * Create a annuity plan chart.
	 *
	 * @param entry
	 *            the {@link Loan} entry
	 * @return the chart
	 */
	private static final <T extends Loan> GenericStackedBarChart<T, RedemptionPlanEntry> createLoanAnnuityPlanChart(final T entry) {
		final Function<T, Collection<RedemptionPlanEntry>> dataGetter = T::getRedemptionPlan;

		final Function<RedemptionPlanEntry, Number> interestGetter = RedemptionPlanEntry::getInterest;
		final LineChartSeriesDefinition<T, RedemptionPlanEntry> interest = new LineChartSeriesDefinition<>(
				I18n.get(I18n.TEXT_INTEREST), interestGetter, dataGetter);

		final Function<RedemptionPlanEntry, Number> redemptionGetter = RedemptionPlanEntry::getRedemption;
		final LineChartSeriesDefinition<T, RedemptionPlanEntry> redemption = new LineChartSeriesDefinition<>(
				I18n.get(I18n.TEXT_REDEMPTION), redemptionGetter, dataGetter);

		final GenericStackedBarChart<T, RedemptionPlanEntry> annuityPlanChart = new GenericStackedBarChart<>(
				I18n.get(I18n.ANNUITY_PLAN_CHART_TITLE), true, interest, redemption);
		annuityPlanChart.add(entry);
		return annuityPlanChart;
	}
}
