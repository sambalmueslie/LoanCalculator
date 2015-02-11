/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.panel;

import de.sambalmueslie.loan_calculator.backend.loan_mgt.RedemptionPlanEntry;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreement;
import de.sambalmueslie.loan_calculator.frontend.chart.LineChartSeriesDefinition;
import de.sambalmueslie.loan_calculator.frontend.chart.generic.GenericLineChart;
import de.sambalmueslie.loan_calculator.frontend.chart.loan.LoanChartFactory;
import de.sambalmueslie.loan_calculator.frontend.component.TextFieldType;
import de.sambalmueslie.loan_calculator.frontend.i18n.I18n;

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

		addInfo(I18n.get(I18n.TEXT_AMOUNT), buildingLoanAgreement.getAmount(), TextFieldType.CURRENCY);
		addInfo(I18n.get(I18n.TEXT_CREDIT_INTERST), buildingLoanAgreement.getCreditInterest(), TextFieldType.PERCENTAGE);
		addInfo(I18n.get(I18n.TEXT_REGULAR_SAVING_AMOUNT), buildingLoanAgreement.getRegularSavingAmount(), TextFieldType.PERCENTAGE);
		addInfo(I18n.get(I18n.TEXT_MINIMUM_SAVINGS), buildingLoanAgreement.getMinimumSavings(), TextFieldType.PERCENTAGE);
		addInfo(I18n.get(I18n.TEXT_SAVING_DURATION), String.format("%d", buildingLoanAgreement.getSavingDuration()), TextFieldType.TEXT);
		addInfo(I18n.get(I18n.TEXT_DEBIT_INTEREST), buildingLoanAgreement.getDebitInterest(), TextFieldType.PERCENTAGE);
		addInfo(I18n.get(I18n.TEXT_CONTRIBUTION), buildingLoanAgreement.getContribution(), TextFieldType.PERCENTAGE);
		addInfo(I18n.get(I18n.TEXT_AQUISITION_FEE), buildingLoanAgreement.getAquisitonFee(), TextFieldType.PERCENTAGE);
		addInfo(I18n.get(I18n.TEXT_TERM), String.format("%d", buildingLoanAgreement.getTerm()), TextFieldType.TEXT);
		addInfo(I18n.get(I18n.TEXT_TOTAL_AMOUNT), buildingLoanAgreement.getAmount(), TextFieldType.CURRENCY);
		addInfo(I18n.get(I18n.TEXT_TOTAL_INTEREST), buildingLoanAgreement.getTotalInterest(), TextFieldType.CURRENCY);
		addInfo(I18n.get(I18n.TEXT_TOTAL_PAYMENT), buildingLoanAgreement.getTotalPayment(), TextFieldType.CURRENCY);
		addInfo(I18n.get(I18n.TEXT_START_DATE), buildingLoanAgreement.getStartDate(), TextFieldType.DATE);
		addInfo(I18n.get(I18n.TEXT_END_DATE), buildingLoanAgreement.getEndDate(), TextFieldType.DATE);

		addRedemptionPlanChart();

		addChart(LoanChartFactory.createAnnuityPlanChart(buildingLoanAgreement));

	}

	@Override
	protected void update() {
		final BuildingLoanAgreement buildingLoanAgreement = getLoan();

		updateInfo(I18n.get(I18n.TEXT_AMOUNT), buildingLoanAgreement.getAmount());
		updateInfo(I18n.get(I18n.TEXT_CREDIT_INTERST), buildingLoanAgreement.getCreditInterest());
		updateInfo(I18n.get(I18n.TEXT_REGULAR_SAVING_AMOUNT), buildingLoanAgreement.getRegularSavingAmount());
		updateInfo(I18n.get(I18n.TEXT_MINIMUM_SAVINGS), buildingLoanAgreement.getMinimumSavings());
		updateInfo(I18n.get(I18n.TEXT_SAVING_DURATION), String.format("%d", buildingLoanAgreement.getSavingDuration()));

		updateInfo(I18n.get(I18n.TEXT_DEBIT_INTEREST), buildingLoanAgreement.getDebitInterest());
		updateInfo(I18n.get(I18n.TEXT_CONTRIBUTION), buildingLoanAgreement.getContribution());
		updateInfo(I18n.get(I18n.TEXT_AQUISITION_FEE), buildingLoanAgreement.getAquisitonFee());

		updateInfo(I18n.get(I18n.TEXT_TERM), String.format("%d", buildingLoanAgreement.getTerm()));

		updateInfo(I18n.get(I18n.TEXT_TOTAL_AMOUNT), buildingLoanAgreement.getAmount());
		updateInfo(I18n.get(I18n.TEXT_TOTAL_INTEREST), buildingLoanAgreement.getTotalInterest());
		updateInfo(I18n.get(I18n.TEXT_TOTAL_PAYMENT), buildingLoanAgreement.getTotalPayment());

		updateInfo(I18n.get(I18n.TEXT_START_DATE), buildingLoanAgreement.getStartDate());
		updateInfo(I18n.get(I18n.TEXT_END_DATE), buildingLoanAgreement.getEndDate());

		addRedemptionPlanChart();

		addChart(LoanChartFactory.createAnnuityPlanChart(buildingLoanAgreement));

	}

	/**
	 * Add a redemption plan chart.
	 */
	private void addRedemptionPlanChart() {
		final BuildingLoanAgreement buildingLoanAgreement = getLoan();
		final LineChartSeriesDefinition<BuildingLoanAgreement, RedemptionPlanEntry> s1 = new LineChartSeriesDefinition<>(I18n.get(I18n.TEXT_RESIDUAL_DEBT),
				RedemptionPlanEntry::getResidualDebt, BuildingLoanAgreement::getRedemptionPlan);
		final LineChartSeriesDefinition<BuildingLoanAgreement, RedemptionPlanEntry> s2 = new LineChartSeriesDefinition<>(I18n.get(I18n.TEXT_SAVINGS),
				RedemptionPlanEntry::getResidualDebt, BuildingLoanAgreement::getSavingPhasePlan);
		final GenericLineChart<BuildingLoanAgreement, RedemptionPlanEntry> planChart = new GenericLineChart<>(I18n.get(I18n.REDEMPTION_PLAN_CHART_TITLE), s1,
				s2);
		planChart.add(buildingLoanAgreement);
		addChart(planChart);
	}
}
