/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.common;

import java.util.Collection;
import java.util.UUID;

/**
 * The base business object mangaer.
 *
 * @author sambalmueslie 2015
 */
public abstract class BaseBusinessObjectMgr<T extends BusinessObject> implements BusinessObjectMgr<T> {

	/**
	 * Add a new {@link BusinessObject}.
	 *
	 * @param businessObject
	 *            the value
	 */
	public void add(final T businessObject) {
		model.add(businessObject);
	}

	/**
	 * Get element by id.
	 *
	 * @param id
	 *            the id
	 * @return the element or <code>null</code> if not found
	 */
	public T get(final long id) {
		return model.get(id);
	}

	/**
	 * @return a {@link Collection} of all elements.
	 */
	public Collection<T> getAll() {
		return model.getAll();
	}

	/**
	 * @return <code>true</code> if empty, otherwise <code>false</code>.
	 */
	public boolean isEmpty() {
		return model.isEmpty();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.common.BusinessObjectMgr#register(de.sambalmueslie.loan_calculator.backend.common.BusinessObjectModelListener)
	 */
	@Override
	public final void register(final BusinessObjectModelListener<T> listener) {
		model.register(listener);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.common.BusinessObjectMgr#unregister(de.sambalmueslie.loan_calculator.backend.common.BusinessObjectModelListener)
	 */
	@Override
	public final void unregister(final BusinessObjectModelListener<T> listener) {
		model.unregister(listener);
	}

	/**
	 * @return a new id.
	 */
	protected long createNewId() {
		return UUID.randomUUID().getLeastSignificantBits();
	}

	/**
	 * Remvoe a {@link BusinessObject}.
	 *
	 * @param businessObject
	 *            the value
	 */
	protected void remove(final T businessObject) {
		model.remove(businessObject);
	}

	/** the {@link BaseBusinessObjectModel}. */
	private final BaseBusinessObjectModel<T> model = new BaseBusinessObjectModel<>();
}
