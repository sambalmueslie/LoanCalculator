/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.compare;

import javafx.scene.Node;
import de.sambalmueslie.loan_calculator.backend.common.BusinessObject;

/**
 * A compare panel.
 *
 * @author sambalmueslie 2015
 */
public interface ComparePanel<T extends BusinessObject> {

	/**
	 * @return the content {@link Node}.
	 */
	Node getContent();

}
