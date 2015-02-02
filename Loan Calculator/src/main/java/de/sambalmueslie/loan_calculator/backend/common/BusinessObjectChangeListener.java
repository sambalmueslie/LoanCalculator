/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.common;

/**
 * The {@link BusinessObject} change listener.
 *
 * @author sambalmueslie 2015
 */
@FunctionalInterface
public interface BusinessObjectChangeListener {
	/**
	 * The {@link BusinessObject} has changed.
	 *
	 * @param businessObject
	 *            the affected businessObject.
	 */
	void businessObjectChanged(BusinessObject businessObject);

}
