/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.panel;

import java.time.LocalDate;

import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoanSettings;
import de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlan;
import de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlanEntry;
import de.sambalmueslie.loan_calculator.frontend.chart.LineChartSeriesDefinition;
import de.sambalmueslie.loan_calculator.frontend.chart.generic.GenericLineChart;
import de.sambalmueslie.loan_calculator.frontend.chart.loan.LoanChartFactory;
import de.sambalmueslie.loan_calculator.frontend.component.TextFieldType;
import de.sambalmueslie.loan_calculator.frontend.external.LoanActionListener;
import de.sambalmueslie.loan_calculator.frontend.i18n.I18n;

/**
 * The {@link LoanPanel} for an {@link AnnuityLoan}.
 *
 * @author sambalmueslie 2015
 */
public class AnnuityLoanPanel extends LoanPanel<AnnuityLoan> {

	/**
	 * Constructor.
	 */
	public AnnuityLoanPanel(final AnnuityLoan loan, final LoanActionListener actionListener) {
		super(loan);
		this.actionListener = actionListener;

		addInputInfo(I18n.get(I18n.TEXT_PAYMENT_RATE), loan.getPaymentRate(), TextFieldType.PERCENTAGE);
		addInputInfo(I18n.get(I18n.TEXT_FIXED_DEBIT_INTEREST), loan.getFixedDebitInterest(), TextFieldType.PERCENTAGE);
		addInputInfo(I18n.get(I18n.TEXT_FIXED_INTEREST_PERIOD), loan.getFixedInterestPeriod(), TextFieldType.NUMBER);
		addInputInfo(I18n.get(I18n.TEXT_ESTIMATED_DEBIT_INTEREST), loan.getEstimatedDebitInterest(), TextFieldType.PERCENTAGE);
		addInputInfo(I18n.get(I18n.TEXT_UNSCHEDULED_REPAYMENT), loan.getUnscheduledRepayment(), TextFieldType.PERCENTAGE);

		final RedemptionPlan redemptionPlan = loan.getRedemptionPlan();
		final RedemptionPlanEntry redemption = redemptionPlan.getEntries().get(1);
		addInfo(I18n.get(I18n.TEXT_ANNUITY_INTEREST), redemption.getInterest(), TextFieldType.CURRENCY);
		addInfo(I18n.get(I18n.TEXT_ANNUITY_REDEMPTION), redemption.getRedemption(), TextFieldType.CURRENCY);
		addInfo(I18n.get(I18n.TEXT_ANNUITY_TOTAL), redemption.getInterest() + redemption.getRedemption(), TextFieldType.CURRENCY);

		addInfo(I18n.get(I18n.TEXT_TERM), String.format("%d", redemptionPlan.getTerm()), TextFieldType.TEXT);

		addInfo(I18n.get(I18n.TEXT_TOTAL_AMOUNT), loan.getAmount(), TextFieldType.CURRENCY);
		addInfo(I18n.get(I18n.TEXT_TOTAL_INTEREST), redemptionPlan.getTotalInterest(), TextFieldType.CURRENCY);
		addInfo(I18n.get(I18n.TEXT_TOTAL_PAYMENT), redemptionPlan.getTotalPayment(), TextFieldType.CURRENCY);

		addInfo(I18n.get(I18n.TEXT_START_DATE), loan.getStartDate(), TextFieldType.DATE);
		addInfo(I18n.get(I18n.TEXT_END_DATE), loan.getEndDate(), TextFieldType.DATE);

		final LineChartSeriesDefinition<RedemptionPlan, RedemptionPlanEntry> s1 = new LineChartSeriesDefinition<>(I18n.get(I18n.TEXT_RESIDUAL_DEBT),
				RedemptionPlanEntry::getResidualDebt, RedemptionPlan::getEntries);
		final GenericLineChart<RedemptionPlan, RedemptionPlanEntry> residualDebtChart = new GenericLineChart<>(I18n.get(I18n.REDEMPTION_PLAN_CHART_TITLE), s1);
		residualDebtChart.setLegendVisible(false);
		residualDebtChart.add(redemptionPlan);
		addChart(residualDebtChart);

		addChart(LoanChartFactory.createAnnuityPlanChart(loan));
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
		final double paymentRate = getInfoValue(I18n.get(I18n.TEXT_PAYMENT_RATE));
		final double fixedDebitInterest = getInfoValue(I18n.get(I18n.TEXT_FIXED_DEBIT_INTEREST));
		final int fixedInterestPeriod = getInfoValue(I18n.get(I18n.TEXT_FIXED_INTEREST_PERIOD));
		final double estimatedDebitInterest = getInfoValue(I18n.get(I18n.TEXT_ESTIMATED_DEBIT_INTEREST));
		final double unscheduledRepayment = getInfoValue(I18n.get(I18n.TEXT_UNSCHEDULED_REPAYMENT));
		final AnnuityLoanSettings settings = new AnnuityLoanSettings(name, amount, startDate, paymentRate, fixedDebitInterest, fixedInterestPeriod,
				estimatedDebitInterest, unscheduledRepayment);
		actionListener.requestUpdateAnnuityLoan(loanId, settings);
	}

	@Override
	protected void update() {
		final AnnuityLoan loan = getLoan();
		updateInfo(I18n.get(I18n.TEXT_PAYMENT_RATE), loan.getPaymentRate());
		updateInfo(I18n.get(I18n.TEXT_FIXED_DEBIT_INTEREST), loan.getFixedDebitInterest());
		updateInfo(I18n.get(I18n.TEXT_FIXED_INTEREST_PERIOD), loan.getFixedInterestPeriod());
		updateInfo(I18n.get(I18n.TEXT_ESTIMATED_DEBIT_INTEREST), loan.getEstimatedDebitInterest());
		updateInfo(I18n.get(I18n.TEXT_UNSCHEDULED_REPAYMENT), loan.getUnscheduledRepayment());

		final RedemptionPlan redemptionPlan = loan.getRedemptionPlan();
		final RedemptionPlanEntry redemption = redemptionPlan.getEntries().get(1);
		updateInfo(I18n.get(I18n.TEXT_ANNUITY_INTEREST), redemption.getInterest());
		updateInfo(I18n.get(I18n.TEXT_ANNUITY_REDEMPTION), redemption.getRedemption());
		updateInfo(I18n.get(I18n.TEXT_ANNUITY_TOTAL), redemption.getInterest() + redemption.getRedemption());

		updateInfo(I18n.get(I18n.TEXT_TERM), String.format("%d", redemptionPlan.getTerm()));

		updateInfo(I18n.get(I18n.TEXT_TOTAL_AMOUNT), loan.getAmount());
		updateInfo(I18n.get(I18n.TEXT_TOTAL_INTEREST), redemptionPlan.getTotalInterest());
		updateInfo(I18n.get(I18n.TEXT_TOTAL_PAYMENT), redemptionPlan.getTotalPayment());

		updateInfo(I18n.get(I18n.TEXT_START_DATE), loan.getStartDate());
		updateInfo(I18n.get(I18n.TEXT_END_DATE), loan.getEndDate());

		final LineChartSeriesDefinition<RedemptionPlan, RedemptionPlanEntry> s1 = new LineChartSeriesDefinition<>(I18n.get(I18n.TEXT_RESIDUAL_DEBT),
				RedemptionPlanEntry::getResidualDebt, RedemptionPlan::getEntries);
		final GenericLineChart<RedemptionPlan, RedemptionPlanEntry> residualDebtChart = new GenericLineChart<>(I18n.get(I18n.REDEMPTION_PLAN_CHART_TITLE), s1);
		residualDebtChart.setLegendVisible(false);
		residualDebtChart.add(redemptionPlan);
		addChart(residualDebtChart);

		addChart(LoanChartFactory.createAnnuityPlanChart(loan));
	}

	/** the {@link LoanActionListener}. */
	private final LoanActionListener actionListener;
}
