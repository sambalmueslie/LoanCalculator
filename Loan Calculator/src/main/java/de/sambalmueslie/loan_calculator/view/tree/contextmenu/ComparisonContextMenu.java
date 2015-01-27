/**
 *
 */
package de.sambalmueslie.loan_calculator.view.tree.contextmenu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import de.sambalmueslie.loan_calculator.model.compare.Comparison;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;
import de.sambalmueslie.loan_calculator.view.i18n.I18n;

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
		for (final GenericModelEntry entry : comparison.getElements()) {
			final MenuItem menuItem = new MenuItem(entry.getName());
			final long entryId = entry.getId();
			if (comparison.getType().equals(Founding.class)) {
				menuItem.setOnAction(e -> listener.requestComparisonRemoveFounding(comparisonId, entryId));
			} else if (comparison.getType().equals(Loan.class)) {
				menuItem.setOnAction(e -> listener.requestComparisonRemoveLoan(comparisonId, entryId));
			}
			remove.getItems().add(menuItem);
		}
		getItems().add(remove);

		final MenuItem close = new MenuItem(I18n.get(I18n.CONTEXT_MENU_REMOVE_COMPARISON));
		close.setOnAction(e -> listener.requestRemoveComparison(comparisonId));
		getItems().add(close);
	}

}
