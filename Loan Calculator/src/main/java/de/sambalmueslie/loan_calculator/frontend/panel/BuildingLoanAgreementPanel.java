/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.panel;

import java.time.LocalDate;

import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreement;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreementSettings;
import de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlan;
import de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlanEntry;
import de.sambalmueslie.loan_calculator.frontend.chart.LineChartSeriesDefinition;
import de.sambalmueslie.loan_calculator.frontend.chart.generic.GenericLineChart;
import de.sambalmueslie.loan_calculator.frontend.chart.loan.LoanChartFactory;
import de.sambalmueslie.loan_calculator.frontend.component.TextFieldType;
import de.sambalmueslie.loan_calculator.frontend.external.LoanActionListener;
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
	public BuildingLoanAgreementPanel(final BuildingLoanAgreement buildingLoanAgreement, final LoanActionListener actionListener) {
		super(buildingLoanAgreement);
		this.actionListener = actionListener;

		addInputInfo(I18n.get(I18n.TEXT_CREDIT_INTERST), buildingLoanAgreement.getCreditInterest(), TextFieldType.PERCENTAGE);
		addInputInfo(I18n.get(I18n.TEXT_REGULAR_SAVING_AMOUNT), buildingLoanAgreement.getRegularSavingAmount(), TextFieldType.PERCENTAGE);
		addInputInfo(I18n.get(I18n.TEXT_MINIMUM_SAVINGS), buildingLoanAgreement.getMinimumSavings(), TextFieldType.PERCENTAGE);
		addInputInfo(I18n.get(I18n.TEXT_SAVING_DURATION), buildingLoanAgreement.getSavingDuration(), TextFieldType.NUMBER);
		addInputInfo(I18n.get(I18n.TEXT_SAVING_PHASE_INTEREST), buildingLoanAgreement.getSavingPhaseInterest(), TextFieldType.PERCENTAGE);
		addInputInfo(I18n.get(I18n.TEXT_DEBIT_INTEREST), buildingLoanAgreement.getDebitInterest(), TextFieldType.PERCENTAGE);
		addInputInfo(I18n.get(I18n.TEXT_CONTRIBUTION), buildingLoanAgreement.getContribution(), TextFieldType.PERCENTAGE);
		addInputInfo(I18n.get(I18n.TEXT_AQUISITION_FEE), buildingLoanAgreement.getAquisitonFee(), TextFieldType.PERCENTAGE);

		final RedemptionPlan redemptionPlan = buildingLoanAgreement.getRedemptionPlan();
		addInfo(I18n.get(I18n.TEXT_TERM), String.format("%d", redemptionPlan.getTerm()), TextFieldType.TEXT);
		addInfo(I18n.get(I18n.TEXT_TOTAL_AMOUNT), buildingLoanAgreement.getAmount(), TextFieldType.CURRENCY);
		addInfo(I18n.get(I18n.TEXT_TOTAL_INTEREST), redemptionPlan.getTotalInterest(), TextFieldType.CURRENCY);
		addInfo(I18n.get(I18n.TEXT_TOTAL_PAYMENT), redemptionPlan.getTotalPayment(), TextFieldType.CURRENCY);
		addInfo(I18n.get(I18n.TEXT_START_DATE), buildingLoanAgreement.getStartDate(), TextFieldType.DATE);
		addInfo(I18n.get(I18n.TEXT_END_DATE), buildingLoanAgreement.getEndDate(), TextFieldType.DATE);

		addRedemptionPlanChart();

		addChart(LoanChartFactory.createAnnuityPlanChart(buildingLoanAgreement));

	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.panel.LoanPanel#handleValueChanged()
	 */
	@Override
	protected void handleValueChanged() {
		final long loanId = getLoan().getId();
		final String name = getLoan().getName();
		final double amount = getInfoValue(I18n.get(I18n.TEXT_AMOUNT));
		final LocalDate startDate = getInfoValue(I18n.get(I18n.TEXT_START_DATE));
		final double creditInterest = getInfoValue(I18n.get(I18n.TEXT_CREDIT_INTERST));
		final double regularSavingAmount = getInfoValue(I18n.get(I18n.TEXT_REGULAR_SAVING_AMOUNT));
		final double minimumSavings = getInfoValue(I18n.get(I18n.TEXT_MINIMUM_SAVINGS));
		final int savingDuration = getInfoValue(I18n.get(I18n.TEXT_SAVING_DURATION));
		final double savingPhaseInterest = getInfoValue(I18n.get(I18n.TEXT_SAVING_PHASE_INTEREST));
		final double debitInterest = getInfoValue(I18n.get(I18n.TEXT_DEBIT_INTEREST));
		final double contribution = getInfoValue(I18n.get(I18n.TEXT_CONTRIBUTION));
		final double aquisitonFee = getInfoValue(I18n.get(I18n.TEXT_AQUISITION_FEE));

		final BuildingLoanAgreementSettings settings =
				new BuildingLoanAgreementSettings(name, amount, startDate, creditInterest, regularSavingAmount, minimumSavings, savingDuration,
						savingPhaseInterest, debitInterest, contribution, aquisitonFee);
		actionListener.requestUpdateBuildingLoanAgreement(loanId, settings);
	}

	@Override
	protected void update() {
		final BuildingLoanAgreement buildingLoanAgreement = getLoan();

		updateInfo(I18n.get(I18n.TEXT_AMOUNT), buildingLoanAgreement.getAmount());
		updateInfo(I18n.get(I18n.TEXT_CREDIT_INTERST), buildingLoanAgreement.getCreditInterest());
		updateInfo(I18n.get(I18n.TEXT_REGULAR_SAVING_AMOUNT), buildingLoanAgreement.getRegularSavingAmount());
		updateInfo(I18n.get(I18n.TEXT_MINIMUM_SAVINGS), buildingLoanAgreement.getMinimumSavings());
		updateInfo(I18n.get(I18n.TEXT_SAVING_DURATION), buildingLoanAgreement.getSavingDuration());
		updateInfo(I18n.get(I18n.TEXT_SAVING_PHASE_INTEREST), buildingLoanAgreement.getSavingPhaseInterest());

		updateInfo(I18n.get(I18n.TEXT_DEBIT_INTEREST), buildingLoanAgreement.getDebitInterest());
		updateInfo(I18n.get(I18n.TEXT_CONTRIBUTION), buildingLoanAgreement.getContribution());
		updateInfo(I18n.get(I18n.TEXT_AQUISITION_FEE), buildingLoanAgreement.getAquisitonFee());

		final RedemptionPlan redemptionPlan = buildingLoanAgreement.getRedemptionPlan();
		updateInfo(I18n.get(I18n.TEXT_TERM), String.format("%d", redemptionPlan.getTerm()));

		updateInfo(I18n.get(I18n.TEXT_TOTAL_AMOUNT), buildingLoanAgreement.getAmount());
		updateInfo(I18n.get(I18n.TEXT_TOTAL_INTEREST), redemptionPlan.getTotalInterest());
		updateInfo(I18n.get(I18n.TEXT_TOTAL_PAYMENT), redemptionPlan.getTotalPayment());

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
		final LineChartSeriesDefinition<BuildingLoanAgreement, RedemptionPlanEntry> s1 =
				new LineChartSeriesDefinition<>(I18n.get(I18n.TEXT_RESIDUAL_DEBT), RedemptionPlanEntry::getResidualDebt, t -> t.getRedemptionPlan()
						.getEntries());
		final LineChartSeriesDefinition<BuildingLoanAgreement, RedemptionPlanEntry> s2 =
				new LineChartSeriesDefinition<>(I18n.get(I18n.TEXT_SAVINGS), RedemptionPlanEntry::getResidualDebt, t -> t.getSavingRedemptionPlan()
						.getEntries());
		final GenericLineChart<BuildingLoanAgreement, RedemptionPlanEntry> planChart =
				new GenericLineChart<>(I18n.get(I18n.REDEMPTION_PLAN_CHART_TITLE), s1, s2);
		planChart.add(buildingLoanAgreement);
		addChart(planChart);
	}

	/** the {@link LoanActionListener}. */
	private final LoanActionListener actionListener;
}
