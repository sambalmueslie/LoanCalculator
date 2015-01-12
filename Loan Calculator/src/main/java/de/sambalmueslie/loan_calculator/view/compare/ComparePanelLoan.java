/**
 *
 */
package de.sambalmueslie.loan_calculator.view.compare;

import java.util.Set;

import de.sambalmueslie.loan_calculator.model.Model;
import de.sambalmueslie.loan_calculator.model.compare.Comparison;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;
import de.sambalmueslie.loan_calculator.view.chart.Chart;
import de.sambalmueslie.loan_calculator.view.chart.loan.LoanChartFactory;

/**
 * The compare panel for {@link Loan}s.
 *
 * @author sambalmueslie 2015
 */
class ComparePanelLoan extends BaseComparePanel<Loan> {
	/**
	 * Constructor.
	 *
	 * @param comparison
	 *            the {@link Comparison}
	 * @param actionListener
	 *            the {@link ViewActionListener}
	 * @param model
	 *            the {@link Model}
	 */
	ComparePanelLoan(final Comparison<Loan> comparison, final ViewActionListener actionListener, final Model model) {
		super(comparison, actionListener, model);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.compare.BaseComparePanel#get(long)
	 */
	@Override
	protected Loan get(final long entryId) {
		return getModel().getLoan(entryId);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.compare.BaseComparePanel#requestComparisonAddEntry(long)
	 */
	@Override
	protected void requestComparisonAddEntry(final long entryId) {
		getActionListener().requestComparisonAddLoan(getComparison().getId(), entryId);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.compare.BaseComparePanel#setup(java.util.Set)
	 */
	@Override
	protected void setup(final Set<Loan> loans) {
		final Chart<Loan> residualDebtChart = LoanChartFactory.createResidualDebtChart();
		loans.forEach(l -> residualDebtChart.add(l));

		final Chart<Loan> anuityPlanChart = LoanChartFactory.createAnnuityPlanChart();
		loans.forEach(l -> anuityPlanChart.add(l));

		setCenter(residualDebtChart.getChart());
		setBottom(anuityPlanChart.getChart());

	}

}
