/**
 *
 */
package de.sambalmueslie.loan_calculator.view.panel;

import de.sambalmueslie.loan_calculator.model.loan.AnnuityLoan;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.model.loan.RedemptionPlanEntry;
import de.sambalmueslie.loan_calculator.view.chart.Chart;
import de.sambalmueslie.loan_calculator.view.chart.loan.LoanChartFactory;
import de.sambalmueslie.loan_calculator.view.component.TextFieldType;

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

		addInfo("Payment rate", loan.getPaymentRate(), TextFieldType.PERCENTAGE);
		addInfo("Fixed debit interest", loan.getFixedDebitInterest(), TextFieldType.PERCENTAGE);
		addInfo("Fixed interest period", String.format("%d", loan.getFixedInterestPeriod()), TextFieldType.TEXT);
		addInfo("Estimated debit interest", loan.getEstimatedDebitInterest(), TextFieldType.PERCENTAGE);

		final RedemptionPlanEntry redemption = loan.getRedemptionPlan().get(1);
		addInfo("Annuity interest", redemption.getInterest(), TextFieldType.CURRENCY);
		addInfo("Annuity redemption", redemption.getRedemption(), TextFieldType.CURRENCY);
		addInfo("Annuity total", redemption.getInterest() + redemption.getRedemption(), TextFieldType.CURRENCY);

		addInfo("Duration", String.format("%d", loan.getRedemptionPlan().size() - 1), TextFieldType.TEXT);

		addInfo("Total amount", loan.getAmount(), TextFieldType.CURRENCY);
		addInfo("Total interest", loan.getTotalInterest(), TextFieldType.CURRENCY);
		addInfo("Total payment", loan.getTotalPayment(), TextFieldType.CURRENCY);

		final Chart<Loan> residualDebtChart = LoanChartFactory.createResidualDebtChart();
		residualDebtChart.add(loan);
		addChart(residualDebtChart.getChart(), 0, 0);

		final Chart<Loan> annuityPlanChart = LoanChartFactory.createAnnuityPlanChart();
		annuityPlanChart.add(loan);
		addChart(annuityPlanChart.getChart(), 0, 1);
	}

	@Override
	protected void update(final AnnuityLoan loan) {
		super.update(loan);
		updateInfo("Payment rate", loan.getPaymentRate());
		updateInfo("Fixed debit interest", loan.getFixedDebitInterest());
		updateInfo("Fixed interest period", String.format("%d", loan.getFixedInterestPeriod()));
		updateInfo("Estimated debit interest", loan.getEstimatedDebitInterest());

		final RedemptionPlanEntry redemption = loan.getRedemptionPlan().get(1);
		updateInfo("Annuity interest", redemption.getInterest());
		updateInfo("Annuity redemption", redemption.getRedemption());
		updateInfo("Annuity total", redemption.getInterest() + redemption.getRedemption());

		updateInfo("Duration", String.format("%d", loan.getRedemptionPlan().size() - 1));

		updateInfo("Total amount", loan.getAmount());
		updateInfo("Total interest", loan.getTotalInterest());
		updateInfo("Total payment", loan.getTotalPayment());
	}
}
