/**
 *
 */
package de.sambalmueslie.loan_calculator.model.generic;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.loan_calculator.model.ModelChangeListener;

/**
 * A generic model.
 *
 * @author sambalmueslie 2015
 */
public class GenericModel<T extends GenericModelEntry> {
	/** the logger. */
	private static final Logger logger = LogManager.getLogger(GenericModel.class);

	/**
	 * Add a {@link GenericModelEntry}.
	 *
	 * @param entry
	 *            the entry.
	 */
	public void add(final T entry) {
		if (entry == null) {
			logger.error("Cannot add entry null value.");
			return;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Add new entry " + entry);
		}
		final long id = entry.getId();
		entries.put(id, entry);
		listeners.forEach(l -> l.entryAdded(this, entry));
	}

	/**
	 * Get entry by id.
	 *
	 * @param id
	 *            the id
	 * @return the entry or <code>null</code> if not found.
	 */
	public T get(final long id) {
		return entries.get(id);
	}

	/**
	 * @return a {@link Collection} of all {@link GenericModelEntry}s.
	 */
	public Collection<T> getAll() {
		return Collections.unmodifiableCollection(entries.values());
	}

	/**
	 * @see Collection#isEmpty()
	 */
	public boolean isEmpty() {
		return entries.isEmpty();
	}

	/**
	 * Register a {@link GenericModelListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	public void register(final GenericModelListener<T> listener) {
		if (listener == null || listeners.contains(listener)) return;
		listeners.add(listener);
	}

	/**
	 * Remove a {@link GenericModelEntry}.
	 *
	 * @param entry
	 *            the entry
	 */
	public void remove(final T entry) {
		if (entry == null) {
			logger.error("Cannot remove founding null value.");
			return;
		}
		final long id = entry.getId();
		if (!entries.containsKey(id)) {
			logger.warn("Try to remove not added founding " + entry);
			return;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Remove founding " + entry);
		}
		entries.remove(id);
		listeners.forEach(l -> l.entryRemoved(this, entry));
	}

	/**
	 * Unregister a {@link GenericModelListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	public void unregister(final GenericModelListener<T> listener) {
		if (listener == null) return;
		listeners.remove(listener);
	}

	/** the {@link GenericModelEntry} by id. */
	private final Map<Long, T> entries = new HashMap<>();
	/** the {@link ModelChangeListener}. */
	private final List<GenericModelListener<T>> listeners = new LinkedList<>();

}
