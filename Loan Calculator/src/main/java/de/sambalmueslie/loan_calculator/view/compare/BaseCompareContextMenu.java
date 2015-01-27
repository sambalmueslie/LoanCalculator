/**
 *
 */
package de.sambalmueslie.loan_calculator.view.compare;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import de.sambalmueslie.loan_calculator.model.compare.Comparison;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;
import de.sambalmueslie.loan_calculator.view.i18n.I18n;

/**
 * The base compare {@link ContextMenu}.
 *
 * @author sambalmueslie 2015
 */
public abstract class BaseCompareContextMenu<T extends GenericModelEntry> extends ContextMenu {

	/**
	 * Constructor.
	 *
	 * @param listener
	 *            the {@link ViewActionListener}
	 * @param comparison
	 *            the {@link Comparison}
	 */
	public BaseCompareContextMenu(final ViewActionListener listener, final Comparison<T> comparison) {
		final long comparisonId = comparison.getId();
		final Menu remove = new Menu(I18n.get(I18n.CONTEXT_MENU_REMOVE_ELEMENT));
		for (final T entry : comparison.getElements()) {
			final MenuItem menuItem = new MenuItem(entry.getName());
			final long entryId = entry.getId();
			menuItem.setOnAction(e -> handleElementRemoveAction(listener, comparisonId, entryId));
			remove.getItems().add(menuItem);
		}
		getItems().add(remove);

		final MenuItem close = new MenuItem(I18n.get(I18n.CONTEXT_MENU_REMOVE_COMPARISON));
		close.setOnAction(e -> listener.requestRemoveComparison(comparisonId));
		getItems().add(close);
	}

	/**
	 * Handle a element remove action.
	 *
	 * @param listener
	 *            the {@link ViewActionListener}
	 * @param comparisonId
	 *            the comparison id
	 * @param entryId
	 *            the entry id
	 */
	protected abstract void handleElementRemoveAction(ViewActionListener listener, long comparisonId, long entryId);
}
