/**
 *
 */
package de.sambalmueslie.loan_calculator.view.panel;

import de.sambalmueslie.loan_calculator.model.loan.BuildingLoanAgreement;
import de.sambalmueslie.loan_calculator.model.loan.RedemptionPlanEntry;
import de.sambalmueslie.loan_calculator.view.chart.LineChartSeriesDefinition;
import de.sambalmueslie.loan_calculator.view.chart.generic.GenericLineChart;
import de.sambalmueslie.loan_calculator.view.chart.loan.LoanChartFactory;
import de.sambalmueslie.loan_calculator.view.component.TextFieldType;

/**
 * The {@link LoanPanel} for an {@link BuildingLoanAgreement}.
 *
 * @author sambalmueslie 2015
 */
public class BuildingLoanAgreementPanel extends LoanPanel<BuildingLoanAgreement> {

	/**
	 * Constructor.
	 *
	 * @param buildingLoanAgreement
	 */
	public BuildingLoanAgreementPanel(final BuildingLoanAgreement buildingLoanAgreement) {
		super(buildingLoanAgreement);

		addInfo("Amount", buildingLoanAgreement.getAmount(), TextFieldType.CURRENCY);
		addInfo("Credit interest", buildingLoanAgreement.getCreditInterest(), TextFieldType.PERCENTAGE);
		addInfo("Regular saving amoung", buildingLoanAgreement.getRegularSavingAmount(), TextFieldType.PERCENTAGE);
		addInfo("Minimum savings", buildingLoanAgreement.getMinimumSavings(), TextFieldType.PERCENTAGE);
		addInfo("Saving duration", String.format("%d", buildingLoanAgreement.getSavingDuration()), TextFieldType.TEXT);
		addInfo("Debit interest", buildingLoanAgreement.getDebitInterest(), TextFieldType.PERCENTAGE);
		addInfo("Contribution", buildingLoanAgreement.getContribution(), TextFieldType.PERCENTAGE);
		addInfo("Aquisiton fee", buildingLoanAgreement.getAquisitonFee(), TextFieldType.PERCENTAGE);
		addInfo("Duration", String.format("%d", buildingLoanAgreement.getTerm()), TextFieldType.TEXT);
		addInfo("Total amount", buildingLoanAgreement.getAmount(), TextFieldType.CURRENCY);
		addInfo("Total interest", buildingLoanAgreement.getTotalInterest(), TextFieldType.CURRENCY);
		addInfo("Total payment", buildingLoanAgreement.getTotalPayment(), TextFieldType.CURRENCY);

		addRedemptionPlanChart();

		addChart(LoanChartFactory.createAnnuityPlanChart(buildingLoanAgreement));

	}

	@Override
	protected void update() {
		final BuildingLoanAgreement buildingLoanAgreement = getLoan();

		updateInfo("Credit interest", buildingLoanAgreement.getCreditInterest());
		updateInfo("Regular saving amoung", buildingLoanAgreement.getRegularSavingAmount());
		updateInfo("Minimum savings", buildingLoanAgreement.getMinimumSavings());
		updateInfo("Saving duration", String.format("%d", buildingLoanAgreement.getSavingDuration()));

		updateInfo("Debit interest", buildingLoanAgreement.getDebitInterest());
		updateInfo("Contribution", buildingLoanAgreement.getContribution());
		updateInfo("Aquisiton fee", buildingLoanAgreement.getAquisitonFee());

		updateInfo("Duration", String.format("%d", buildingLoanAgreement.getTerm()));

		updateInfo("Total amount", buildingLoanAgreement.getAmount());
		updateInfo("Total interest", buildingLoanAgreement.getTotalInterest());
		updateInfo("Total payment", buildingLoanAgreement.getTotalPayment());

		addRedemptionPlanChart();

		addChart(LoanChartFactory.createAnnuityPlanChart(buildingLoanAgreement));

	}

	/**
	 * Add a redemption plan chart.
	 */
	private void addRedemptionPlanChart() {
		final BuildingLoanAgreement buildingLoanAgreement = getLoan();
		final LineChartSeriesDefinition<BuildingLoanAgreement, RedemptionPlanEntry> s1 = new LineChartSeriesDefinition<>("residual debt",
				RedemptionPlanEntry::getResidualDebt, BuildingLoanAgreement::getRedemptionPlan);
		final LineChartSeriesDefinition<BuildingLoanAgreement, RedemptionPlanEntry> s2 = new LineChartSeriesDefinition<>("savings",
				RedemptionPlanEntry::getResidualDebt, BuildingLoanAgreement::getSavingPhasePlan);
		final GenericLineChart<BuildingLoanAgreement, RedemptionPlanEntry> planChart = new GenericLineChart<>("Plan", s1, s2);
		planChart.add(buildingLoanAgreement);
		addChart(planChart);
	}
}
