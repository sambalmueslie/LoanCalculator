/**
 *
 */
package de.sambalmueslie.loan_calculator.view.panel;

import de.sambalmueslie.loan_calculator.model.loan.AnnuityLoan;
import de.sambalmueslie.loan_calculator.model.loan.RedemptionPlanEntry;
import de.sambalmueslie.loan_calculator.view.chart.LineChartSeriesDefinition;
import de.sambalmueslie.loan_calculator.view.chart.generic.GenericLineChart;
import de.sambalmueslie.loan_calculator.view.chart.loan.LoanChartFactory;
import de.sambalmueslie.loan_calculator.view.component.TextFieldType;
import de.sambalmueslie.loan_calculator.view.i18n.I18n;

/**
 * The {@link LoanPanel} for an {@link AnnuityLoan}.
 *
 * @author sambalmueslie 2015
 */
public class AnnuityLoanPanel extends LoanPanel<AnnuityLoan> {

	/**
	 * Constructor.
	 */
	public AnnuityLoanPanel(final AnnuityLoan loan) {
		super(loan);

		addInfo(I18n.get(I18n.TEXT_PAYMENT_RATE), loan.getPaymentRate(), TextFieldType.PERCENTAGE);
		addInfo(I18n.get(I18n.TEXT_FIXED_DEBIT_INTEREST), loan.getFixedDebitInterest(), TextFieldType.PERCENTAGE);
		addInfo(I18n.get(I18n.TEXT_FIXED_INTEREST_PERIOD), String.format("%d", loan.getFixedInterestPeriod()), TextFieldType.TEXT);
		addInfo(I18n.get(I18n.TEXT_ESTIMATED_DEBIT_INTEREST), loan.getEstimatedDebitInterest(), TextFieldType.PERCENTAGE);

		final RedemptionPlanEntry redemption = loan.getRedemptionPlan().get(1);
		addInfo(I18n.get(I18n.TEXT_ANNUITY_INTEREST), redemption.getInterest(), TextFieldType.CURRENCY);
		addInfo(I18n.get(I18n.TEXT_ANNUITY_REDEMPTION), redemption.getRedemption(), TextFieldType.CURRENCY);
		addInfo(I18n.get(I18n.TEXT_ANNUITY_TOTAL), redemption.getInterest() + redemption.getRedemption(), TextFieldType.CURRENCY);

		addInfo(I18n.get(I18n.TEXT_TERM), String.format("%d", loan.getRedemptionPlan().size() - 1), TextFieldType.TEXT);

		addInfo(I18n.get(I18n.TEXT_TOTAL_AMOUNT), loan.getAmount(), TextFieldType.CURRENCY);
		addInfo(I18n.get(I18n.TEXT_TOTAL_INTEREST), loan.getTotalInterest(), TextFieldType.CURRENCY);
		addInfo(I18n.get(I18n.TEXT_TOTAL_PAYMENT), loan.getTotalPayment(), TextFieldType.CURRENCY);

		final LineChartSeriesDefinition<AnnuityLoan, RedemptionPlanEntry> s1 = new LineChartSeriesDefinition<>(I18n.get(I18n.TEXT_RESIDUAL_DEBT),
				RedemptionPlanEntry::getResidualDebt, AnnuityLoan::getRedemptionPlan);
		final GenericLineChart<AnnuityLoan, RedemptionPlanEntry> residualDebtChart = new GenericLineChart<>(I18n.get(I18n.REDEMPTION_PLAN_CHART_TITLE), s1);
		residualDebtChart.setLegendVisible(false);
		residualDebtChart.add(loan);
		addChart(residualDebtChart);

		addChart(LoanChartFactory.createAnnuityPlanChart(loan));
	}

	@Override
	protected void update() {
		final AnnuityLoan loan = getLoan();
		updateInfo(I18n.get(I18n.TEXT_PAYMENT_RATE), loan.getPaymentRate());
		updateInfo(I18n.get(I18n.TEXT_FIXED_DEBIT_INTEREST), loan.getFixedDebitInterest());
		updateInfo(I18n.get(I18n.TEXT_FIXED_INTEREST_PERIOD), String.format("%d", loan.getFixedInterestPeriod()));
		updateInfo(I18n.get(I18n.TEXT_ESTIMATED_DEBIT_INTEREST), loan.getEstimatedDebitInterest());

		final RedemptionPlanEntry redemption = loan.getRedemptionPlan().get(1);
		updateInfo(I18n.get(I18n.TEXT_ANNUITY_INTEREST), redemption.getInterest());
		updateInfo(I18n.get(I18n.TEXT_ANNUITY_REDEMPTION), redemption.getRedemption());
		updateInfo(I18n.get(I18n.TEXT_ANNUITY_TOTAL), redemption.getInterest() + redemption.getRedemption());

		updateInfo(I18n.get(I18n.TEXT_TERM), String.format("%d", loan.getRedemptionPlan().size() - 1));

		updateInfo(I18n.get(I18n.TEXT_TOTAL_AMOUNT), loan.getAmount());
		updateInfo(I18n.get(I18n.TEXT_TOTAL_INTEREST), loan.getTotalInterest());
		updateInfo(I18n.get(I18n.TEXT_TOTAL_PAYMENT), loan.getTotalPayment());

		final LineChartSeriesDefinition<AnnuityLoan, RedemptionPlanEntry> s1 = new LineChartSeriesDefinition<>(I18n.get(I18n.TEXT_RESIDUAL_DEBT),
				RedemptionPlanEntry::getResidualDebt, AnnuityLoan::getRedemptionPlan);
		final GenericLineChart<AnnuityLoan, RedemptionPlanEntry> residualDebtChart = new GenericLineChart<>(I18n.get(I18n.REDEMPTION_PLAN_CHART_TITLE), s1);
		residualDebtChart.setLegendVisible(false);
		residualDebtChart.add(loan);
		addChart(residualDebtChart);

		addChart(LoanChartFactory.createAnnuityPlanChart(loan));
	}
}
