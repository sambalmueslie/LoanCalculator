/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr.contextmenu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import de.sambalmueslie.loan_calculator.model.compare.Comparison;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;

/**
 * The context menu for the {@link Comparison}.
 *
 * @author sambalmueslie 2015
 */
public class ComparisonContextMenu extends EntryTreeContextMenu {

	/**
	 * Constructor.
	 */
	public ComparisonContextMenu() {
		super();
		getItems().add(new SeparatorMenuItem());

		removeMenu = new Menu("Remove Element");
		getItems().add(removeMenu);

		closeMenuItem = new MenuItem("Remove comparison");
		getItems().add(closeMenuItem);

	}

	/**
	 * Set the {@link Comparison}.
	 *
	 * @param comparison
	 *            the comparison
	 */
	public void set(final Comparison<?> comparison) {
		if (comparison == null) {
			removeMenu.getItems().clear();
			closeMenuItem.setOnAction(null);
		} else {
			final long comparisonId = comparison.getId();
			for (final GenericModelEntry entry : comparison.getElements()) {
				final MenuItem menuItem = new MenuItem(entry.getName());
				final long entryId = entry.getId();
				if (comparison.getType().equals(Founding.class)) {
					menuItem.setOnAction(e -> listener.requestComparisonRemoveFounding(comparisonId, entryId));
				} else if (comparison.getType().equals(Loan.class)) {
					menuItem.setOnAction(e -> listener.requestComparisonRemoveLoan(comparisonId, entryId));
				}
				removeMenu.getItems().add(menuItem);
			}
			closeMenuItem.setOnAction(e -> listener.requestRemoveComparison(comparisonId));
		}
	}

	/**
	 * Set the {@link ViewActionListener}.
	 *
	 * @param listener
	 *            the listener to set
	 */
	@Override
	public void setListener(final ViewActionListener listener) {
		this.listener = listener;
	}

	/** the close {@link MenuItem}. */
	private final MenuItem closeMenuItem;
	/** the {@link ViewActionListener}. */
	private ViewActionListener listener;
	/** the remove {@link Menu}. */
	private final Menu removeMenu;

}
