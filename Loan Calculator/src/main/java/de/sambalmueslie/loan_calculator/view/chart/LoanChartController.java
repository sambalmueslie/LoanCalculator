/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import de.sambalmueslie.loan_calculator.model.Loan;

/**
 * The controller for the {@link LoanChart}.
 *
 * @author sambalmueslie 2015
 */
public class LoanChartController implements LoanChart {

	/**
	 * Constructor.
	 *
	 * @param loanChart
	 *            {@link #loanChart}
	 */
	LoanChartController(final LoanChart loanChart) {
		this.loanChart = loanChart;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.chart.LoanChart#add(de.sambalmueslie.loan_calculator.model.Loan)
	 */
	@Override
	public void add(final Loan loan) {
		if (loan == null || loans.containsKey(loan.getId())) return;
		loans.put(loan.getId(), loan);
		loan.register(this::update);
		loanChart.add(loan);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.chart.LoanChart#getChart()
	 */
	@Override
	public Node getChart() {
		return loanChart.getChart();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.chart.LoanChart#remove(de.sambalmueslie.loan_calculator.model.Loan)
	 */
	@Override
	public void remove(final Loan loan) {
		if (loan == null || !loans.containsKey(loan.getId())) return;
		loans.remove(loan.getId());
		loan.unregister(this::update);
		loanChart.remove(loan);
	}

	/**
	 * Update a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	private void update(final Loan loan) {
		loanChart.remove(loan);
		loanChart.add(loan);
	}

	/** the {@link LoanChart}. */
	private final LoanChart loanChart;
	/** the currently displayed {@link Loan}s. */
	private final Map<Long, Loan> loans = new HashMap<>();
}
