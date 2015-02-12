/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.common;

import java.util.LinkedList;
import java.util.List;

/**
 * Base implementatin for a {@link BusinessObject}.
 *
 * @author sambalmueslie 2015
 */
public abstract class BaseBusinessObject implements BusinessObject {

	/**
	 * Constructor.
	 *
	 * @param id
	 *            {@link #id}
	 * @param name
	 *            {@link #name}
	 */
	protected BaseBusinessObject(final long id, final String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.common.BusinessObject#getId()
	 */
	@Override
	public final long getId() {
		return id;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.common.BusinessObject#getName()
	 */
	@Override
	public final String getName() {
		return name;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.common.BusinessObject#register(de.sambalmueslie.loan_calculator.backend.common.BusinessObjectChangeListener)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public final void register(final BusinessObjectChangeListener<?> listener) {
		if (listener == null || listeners.contains(listener)) return;
		listeners.add((BusinessObjectChangeListener<BusinessObject>) listener);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.common.BusinessObject#unregister(de.sambalmueslie.loan_calculator.backend.common.BusinessObjectChangeListener)
	 */
	@Override
	public final void unregister(final BusinessObjectChangeListener<?> listener) {
		if (listener == null) return;
		listeners.remove(listener);
	}

	/**
	 * Notify that the {@link BusinessObject} has changed.
	 */
	protected void notifyChanged() {
		new LinkedList<>(listeners).forEach(l -> l.businessObjectChanged(this));
	}

	/**
	 * @param name
	 *            the name to set
	 */
	protected void setName(final String name) {
		this.name = name;
	}

	/** the id. */
	private final long id;
	/** the {@link GenericModelEntryChangeListener}s. */
	private final List<BusinessObjectChangeListener<BusinessObject>> listeners = new LinkedList<>();
	/** the name. */
	private String name;

}
