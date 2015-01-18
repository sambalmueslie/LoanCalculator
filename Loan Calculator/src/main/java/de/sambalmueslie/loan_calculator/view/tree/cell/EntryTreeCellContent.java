/**
 *
 */
package de.sambalmueslie.loan_calculator.view.tree.cell;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;

/**
 * The entry tree cell content.
 *
 * @author sambalmueslie 2015
 */
public interface EntryTreeCellContent<T extends GenericModelEntry> {
	/**
	 * @return the {@link ContextMenu}.
	 */
	ContextMenu getContextMenu();

	/**
	 * Get the content for a entry.
	 *
	 * @return the content {@link Node}
	 */
	Node getGrapic();
}
