/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.tree.cell;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import de.sambalmueslie.loan_calculator.backend.common.BusinessObject;

/**
 * The entry tree cell content.
 *
 * @author sambalmueslie 2015
 */
public interface EntryTreeCellContent<T extends BusinessObject> {
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
