/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;

/**
 * The {@link ContextMenu} for a {@link Founding}.
 *
 * @author sambalmueslie 2015
 */
public class FoundingContextMenu extends ContextMenu {
	/**
	 * Constructor.
	 */
	public FoundingContextMenu() {
		addMenuItem = new MenuItem("Add");
		updateMenuItem = new MenuItem("Update");
		removeMenuItem = new MenuItem("Remove");
		getItems().addAll(addMenuItem, updateMenuItem, removeMenuItem);
	}

	/**
	 * Set the {@link Founding}.
	 *
	 * @param loan
	 *            the loan
	 */
	public void set(final Founding founding) {
		addMenuItem.setOnAction(e -> add());
		if (founding == null) {
			updateMenuItem.setOnAction(null);
			removeMenuItem.setOnAction(null);
		} else {
			updateMenuItem.setOnAction(e -> update(founding));
			removeMenuItem.setOnAction(e -> remove(founding));
		}
	}

	/**
	 * @param listener
	 *            the listener to set
	 */
	public void setListener(final ViewActionListener listener) {
		this.listener = listener;
	}

	/**
	 * Add a new founding.
	 */
	private void add() {
		// TODO Auto-generated method stub
	}

	/**
	 * Remove a existing {@link Founding}.
	 *
	 * @param founding
	 *            the {@link Founding}
	 */
	private void remove(final Founding founding) {
		// TODO Auto-generated method stub
	}

	/**
	 * Update a existing {@link Founding}.
	 *
	 * @param founding
	 *            the {@link Founding}
	 */
	private void update(final Founding founding) {
		// TODO Auto-generated method stub
	}

	/** the add {@link MenuItem}. */
	private final MenuItem addMenuItem;
	/** the {@link ViewActionListener}. */
	private ViewActionListener listener;
	/** the remove {@link MenuItem}. */
	private final MenuItem removeMenuItem;
	/** the update {@link MenuItem}. */
	private final MenuItem updateMenuItem;
}
