/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.compare;

import de.sambalmueslie.loan_calculator.backend.compare_mgt.Comparison;
import de.sambalmueslie.loan_calculator.backend.founding_mgt.Founding;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;
import de.sambalmueslie.loan_calculator.backend.model.Model;
import de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener;

/**
 * The {@link ComparePanel} factory.
 *
 * @author sambalmueslie 2015
 */
public final class ComparePanelFactory {

	/**
	 * Create a compare panel for a {@link Comparison}.
	 *
	 * @param comparison
	 *            the comparison
	 * @return the panel.
	 */
	@SuppressWarnings("unchecked")
	public static ComparePanel<?> createComparePanel(final Comparison<?> comparison, final ViewActionListener actionListener, final Model model) {
		if (comparison.getType().equals(Founding.class)) {
			return new ComparePanelFounding((Comparison<Founding>) comparison, actionListener, model);
		}
		if (comparison.getType().equals(Loan.class)) {
			return new ComparePanelLoan((Comparison<Loan>) comparison, actionListener, model);
		}
		return null;
	}

	/**
	 * Constructor.
	 */
	private ComparePanelFactory() {
		// intentionally left empty
	}
}
