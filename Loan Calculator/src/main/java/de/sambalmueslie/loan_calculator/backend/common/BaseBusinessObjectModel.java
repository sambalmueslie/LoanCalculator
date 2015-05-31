/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.common;

import java.util.*;

/**
 * The base {@link BusinessObjectModel}.
 *
 * @author sambalmueslie 2015
 */
class BaseBusinessObjectModel<T extends BusinessObject> implements BusinessObjectModel<T> {

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.common.BusinessObjectModel#get(long)
	 */
	@Override
	public final T get(final long id) {
		return values.get(id);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.common.BusinessObjectModel#getAll()
	 */
	@Override
	public final Collection<T> getAll() {
		return Collections.unmodifiableCollection(values.values());
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.common.BusinessObjectModel#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return values.isEmpty();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.common.BusinessObjectModel#register(de.sambalmueslie.loan_calculator.backend.common.BusinessObjectModelListener)
	 */
	@Override
	public final void register(final BusinessObjectModelListener<T> listener) {
		if (listener == null || listeners.contains(listener)) {
			return;
		}
		listeners.add(listener);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.common.BusinessObjectModel#unregister(de.sambalmueslie.loan_calculator.backend.common.BusinessObjectModelListener)
	 */
	@Override
	public final void unregister(final BusinessObjectModelListener<T> listener) {
		if (listener == null) {
			return;
		}
		listeners.remove(listener);
	}

	/**
	 * Add a new {@link BusinessObject}.
	 *
	 * @param businessObject
	 *            the value
	 */
	void add(final T businessObject) {
		if (businessObject == null) {
			return;
		}
		final long id = businessObject.getId();
		values.put(id, businessObject);
		notifyBusinessObjectAdded(businessObject);
	}

	/**
	 * Remvoe a {@link BusinessObject}.
	 *
	 * @param businessObject
	 *            the value
	 */
	void remove(final T businessObject) {
		if (businessObject == null) {
			return;
		}
		final long id = businessObject.getId();
		if (!values.containsKey(id)) {
			return;
		}
		values.remove(id);
		notifyBusinessObjectRemoved(businessObject);
	}

	/**
	 * Notify that a {@link BusinessObject} was added.
	 *
	 * @param businessObject
	 *            the {@link BusinessObject}
	 */
	private void notifyBusinessObjectAdded(final T businessObject) {
		listeners.forEach(l -> l.entryAdded(this, businessObject));
	}

	/**
	 * Notify that a {@link BusinessObject} was added.
	 *
	 * @param businessObject
	 *            the {@link BusinessObject}
	 */
	private void notifyBusinessObjectRemoved(final T businessObject) {
		listeners.forEach(l -> l.entryRemoved(this, businessObject));
	}

	/** the {@link BusinessObjectModelListener}. */
	private final List<BusinessObjectModelListener<T>> listeners = new LinkedList<>();
	/** the {@link Map}. */
	private final Map<Long, T> values = new LinkedHashMap<>();

}
