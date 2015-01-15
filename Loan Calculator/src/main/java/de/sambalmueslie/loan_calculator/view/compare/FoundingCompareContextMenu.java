/**
 *
 */
package de.sambalmueslie.loan_calculator.view.compare;

import javafx.scene.control.ContextMenu;
import de.sambalmueslie.loan_calculator.model.compare.Comparison;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;

/**
 * The compare {@link ContextMenu}.
 *
 * @author sambalmueslie 2015
 */
public class FoundingCompareContextMenu extends BaseCompareContextMenu<Founding> {

	/**
	 * Constructor.
	 *
	 * @param listener
	 *            the {@link ViewActionListener}
	 * @param comparison
	 *            the {@link Comparison}
	 */
	public FoundingCompareContextMenu(final ViewActionListener listener, final Comparison<Founding> comparison) {
		super(listener, comparison);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.compare.BaseCompareContextMenu#handleElementRemoveAction(de.sambalmueslie.loan_calculator.view.ViewActionListener,
	 *      long, long)
	 */
	@Override
	protected void handleElementRemoveAction(final ViewActionListener listener, final long comparisonId, final long entryId) {
		listener.requestComparisonRemoveFounding(comparisonId, entryId);
	}
}
