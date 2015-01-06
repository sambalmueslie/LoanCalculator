/**
 *
 */
package de.sambalmueslie.loan_calculator.view.chart;

import javafx.scene.chart.Chart;
import de.sambalmueslie.loan_calculator.model.Loan;

/**
 * A chart to display {@link Loan}s.
 *
 * @author sambalmueslie 2015
 */
public interface LoanChart {
	/**
	 * Add a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	void add(Loan loan);

	/**
	 * @return the {@link Chart}.
	 */
	Chart getChart();

	/**
	 * Remove a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	void remove(Loan loan);
}
