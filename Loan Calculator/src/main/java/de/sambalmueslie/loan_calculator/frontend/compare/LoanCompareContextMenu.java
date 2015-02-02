/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.compare;

import javafx.scene.control.ContextMenu;
import de.sambalmueslie.loan_calculator.backend.compare_mgt.Comparison;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;
import de.sambalmueslie.loan_calculator.frontend.ViewActionListener;

/**
 * The compare {@link ContextMenu}.
 *
 * @author sambalmueslie 2015
 */
public class LoanCompareContextMenu extends BaseCompareContextMenu<Loan> {

	/**
	 * Constructor.
	 *
	 * @param listener
	 *            the {@link ViewActionListener}
	 * @param comparison
	 *            the {@link Comparison}
	 */
	public LoanCompareContextMenu(final ViewActionListener listener, final Comparison<Loan> comparison) {
		super(listener, comparison);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.compare.BaseCompareContextMenu#handleElementRemoveAction(de.sambalmueslie.loan_calculator.frontend.ViewActionListener,
	 *      long, long)
	 */
	@Override
	protected void handleElementRemoveAction(final ViewActionListener listener, final long comparisonId, final long entryId) {
		listener.requestComparisonRemoveLoan(comparisonId, entryId);
	}
}
