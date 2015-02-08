/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.tree.contextmenu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import de.sambalmueslie.loan_calculator.backend.common.BusinessObject;
import de.sambalmueslie.loan_calculator.backend.compare_mgt.Comparison;
import de.sambalmueslie.loan_calculator.backend.founding_mgt.Founding;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;
import de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener;
import de.sambalmueslie.loan_calculator.frontend.i18n.I18n;

/**
 * The context menu for a loan list cell loan.
 *
 * @author sambalmueslie 2015
 */
public class ComparisonContextMenu extends BaseContextMenu {

	/**
	 * Constructor.
	 *
	 * @param comparison
	 *            the {@link Loan}
	 * @param listener
	 *            {@link #listener}
	 */
	public ComparisonContextMenu(final Comparison<?> comparison, final ViewActionListener listener) {
		super(listener);

		getItems().add(new SeparatorMenuItem());

		final long comparisonId = comparison.getId();
		final Menu remove = new Menu(I18n.get(I18n.CONTEXT_MENU_REMOVE_ELEMENT));
		for (final BusinessObject entry : comparison.getElements()) {
			final MenuItem menuItem = MenuItemFactory.createComparisonRemoveEntryItem(entry);
			final long entryId = entry.getId();
			if (comparison.getType().equals(Founding.class)) {
				menuItem.setOnAction(e -> listener.requestComparisonRemoveFounding(comparisonId, entryId));
			} else if (comparison.getType().equals(Loan.class)) {
				menuItem.setOnAction(e -> listener.requestComparisonRemoveLoan(comparisonId, entryId));
			}
			remove.getItems().add(menuItem);
		}
		getItems().add(remove);

		final MenuItem close = MenuItemFactory.createRemoveComparisonItem(comparison);
		close.setOnAction(e -> listener.requestRemoveComparison(comparisonId));
		getItems().add(close);
	}

}
