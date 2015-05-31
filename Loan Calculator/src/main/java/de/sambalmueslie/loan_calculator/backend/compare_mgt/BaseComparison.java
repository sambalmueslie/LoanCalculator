/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.compare_mgt;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import de.sambalmueslie.loan_calculator.backend.common.BaseBusinessObject;
import de.sambalmueslie.loan_calculator.backend.common.BusinessObject;
import de.sambalmueslie.loan_calculator.backend.common.BusinessObjectChangeListener;

/**
 * A base {@link Comparison}.
 *
 * @author sambalmueslie 2015
 * @param <T>
 *            the {@link BusinessObject} type
 */
public class BaseComparison<T extends BusinessObject> extends BaseBusinessObject implements Comparison<T> {

	/**
	 * The handler for the {@link BusinessObjectChangeListener}.
	 *
	 * @author sambalmueslie 2015
	 */
	private class BusinessObjectChangeHandler implements BusinessObjectChangeListener<T> {
		/**
		 * @see de.sambalmueslie.loan_calculator.backend.common.BusinessObjectChangeListener#businessObjectChanged(de.sambalmueslie.loan_calculator.backend.common.BusinessObject)
		 */
		@Override
		public void businessObjectChanged(final T businessObject) {
			notifyChanged();
		}
	}

	/**
	 * Constructor.
	 *
	 * @param id
	 *            {@link #id}
	 * @param name
	 *            {@link #name}
	 * @param type
	 *            {@link #type}
	 */
	public BaseComparison(final long id, final String name, final Class<T> type) {
		super(id, name);
		this.type = type;
		changeHandler = new BusinessObjectChangeHandler();
	}

	/**
	 * Add a element.
	 *
	 * @param element
	 *            the element
	 */
	public final void add(final T element) {
		if (element == null) {
			return;
		}
		elements.add(element);
		notifyChanged();
		element.register(changeHandler);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.compare.Comparison#getElements()
	 */
	@Override
	public final Set<T> getElements() {
		return Collections.unmodifiableSet(elements);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.compare.Comparison#getType()
	 */
	@Override
	public Class<T> getType() {
		return type;
	}

	/**
	 * Remove a element.
	 *
	 * @param element
	 *            the element
	 */
	public final void remove(final T element) {
		if (element == null) {
			return;
		}
		elements.remove(element);
		notifyChanged();
		element.unregister(changeHandler);
	}

	/** the {@link BusinessObjectChangeHandler}. */
	private final BusinessObjectChangeHandler changeHandler;
	/** the elements. */
	private final Set<T> elements = new LinkedHashSet<>();
	/** the type. */
	private final Class<T> type;
}
