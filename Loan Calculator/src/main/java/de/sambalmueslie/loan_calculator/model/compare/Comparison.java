/**
 *
 */
package de.sambalmueslie.loan_calculator.model.compare;

import java.util.Set;

import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;

/**
 * A comparison of several {@link GenericModelEntry}.
 *
 * @author sambalmueslie 2015
 */
public interface Comparison<T extends GenericModelEntry<T>> extends GenericModelEntry<Comparison<T>> {

	/**
	 * @return the compared elements.
	 */
	Set<T> getElements();

	/**
	 * @return the compared type.
	 */
	Class<T> getType();

}
