/**
 *
 */
package de.sambalmueslie.loan_calculator.view.compare;

import javafx.scene.Node;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;

/**
 * A compare panel.
 *
 * @author sambalmueslie 2015
 */
public interface ComparePanel<T extends GenericModelEntry> {

	/**
	 * @return the content {@link Node}.
	 */
	Node getContent();

}
