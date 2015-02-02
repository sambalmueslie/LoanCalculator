/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.compare_mgt;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import de.sambalmueslie.loan_calculator.backend.common.BaseBusinessObject;
import de.sambalmueslie.loan_calculator.backend.common.BusinessObject;

/**
 * A base {@link Comparison}.
 *
 * @author sambalmueslie 2015
 * @param <T>
 *            the {@link BusinessObject} type
 */
public class BaseComparison<T extends BusinessObject> extends BaseBusinessObject implements Comparison<T> {

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
	}

	/**
	 * Add a element.
	 *
	 * @param element
	 *            the element
	 */
	public final void add(final T element) {
		if (element == null) return;
		elements.add(element);
		notifyChanged();
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
		if (element == null) return;
		elements.remove(element);
		notifyChanged();
	}

	/** the elements. */
	private final Set<T> elements = new LinkedHashSet<>();
	/** the type. */
	private final Class<T> type;
}
