/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.compare_mgt;

import java.util.Set;

import de.sambalmueslie.loan_calculator.backend.common.BusinessObject;

/**
 * A comparison of several {@link BusinessObject}.
 *
 * @author sambalmueslie 2015
 */
public interface Comparison<T extends BusinessObject> extends BusinessObject {

	/**
	 * @return the compared elements.
	 */
	Set<T> getElements();

	/**
	 * @return the compared type.
	 */
	Class<T> getType();

}
