/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr.tree;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;

/**
 * The content for the entry tree item.
 *
 * @author sambalmueslie 2015
 */
public interface EntryTreeItemContent<T extends GenericModelEntry<T>> {
	/**
	 * @param entry
	 *            the entry
	 * @return the {@link ContextMenu}.
	 */
	ContextMenu getContextMenu(T entry);

	/**
	 * Get the content for a entry.
	 *
	 * @param entry
	 *            the entry
	 * @return the content {@link Node}
	 */
	Node getGrapic(T entry);

	/**
	 * Set the {@link ViewActionListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	void set(ViewActionListener listener);
}
