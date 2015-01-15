/**
 *
 */
package de.sambalmueslie.loan_calculator.view.compare;

import javafx.scene.control.ContextMenu;
import de.sambalmueslie.loan_calculator.model.compare.Comparison;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;

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
	 * @see de.sambalmueslie.loan_calculator.view.compare.BaseCompareContextMenu#handleElementRemoveAction(de.sambalmueslie.loan_calculator.view.ViewActionListener,
	 *      long, long)
	 */
	@Override
	protected void handleElementRemoveAction(final ViewActionListener listener, final long comparisonId, final long entryId) {
		listener.requestComparisonRemoveLoan(comparisonId, entryId);
	}
}
