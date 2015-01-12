/**
 *
 */
package de.sambalmueslie.loan_calculator.model.compare;

import java.util.*;

import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntryChangeListener;

/**
 * A base {@link Comparison}.
 *
 * @author sambalmueslie 2015
 * @param <T>
 *            the {@link GenericModelEntry} type
 */
public class BaseComparison<T extends GenericModelEntry<T>> implements Comparison<T> {

	/**
	 * Constructor.
	 *
	 * @param name
	 *            {@link #name}
	 * @param type
	 *            the compared type
	 * @throws on
	 *             illegal argument
	 */
	public BaseComparison(final String name, final Class<T> type) throws IllegalArgumentException {
		this.type = type;
		id = UUID.randomUUID().getLeastSignificantBits();
		update(name);
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
	 * @see de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry#getId()
	 */
	@Override
	public final long getId() {
		return id;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry#getName()
	 */
	@Override
	public final String getName() {
		return name;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.compare.Comparison#getType()
	 */
	@Override
	public Class<T> getType() {
		return type;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry#register(de.sambalmueslie.loan_calculator.model.generic.GenericModelEntryChangeListener)
	 */
	@Override
	public final void register(final GenericModelEntryChangeListener<Comparison<T>> listener) {
		if (listener == null || listeners.contains(listener)) return;
		listeners.add(listener);
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

	/**
	 * @see de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry#unregister(de.sambalmueslie.loan_calculator.model.generic.GenericModelEntryChangeListener)
	 */
	@Override
	public final void unregister(final GenericModelEntryChangeListener<Comparison<T>> listener) {
		if (listener == null) return;
		listeners.remove(listener);
	}

	/**
	 * Update.
	 *
	 * @param name
	 *            {@link #name}
	 * @throws IllegalArgumentException
	 *             on invalid argument
	 */
	public void update(final String name) throws IllegalArgumentException {
		if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name '" + name + "' for comparison cannot be null or empty.");
		this.name = name;
		notifyChanged();
	}

	/**
	 * Notify that the founding has changed.
	 */
	private void notifyChanged() {
		listeners.forEach(l -> l.entryChanged(this));
	}

	/** the elements. */
	private final Set<T> elements = new LinkedHashSet<>();
	/** the id. */
	private final long id;
	/** the {@link GenericModelEntryChangeListener}s. */
	private final List<GenericModelEntryChangeListener<Comparison<T>>> listeners = new LinkedList<>();
	/** the name. */
	private String name;
	/** the type. */
	private final Class<T> type;
}
