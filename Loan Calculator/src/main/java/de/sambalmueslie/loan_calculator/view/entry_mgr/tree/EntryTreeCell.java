/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr.tree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javafx.scene.Node;
import javafx.scene.control.TreeCell;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;
import de.sambalmueslie.loan_calculator.view.entry_mgr.tabs.EntryTabPane;

/**
 * The entry tree {@link TreeCell}.
 *
 * @author sambalmueslie 2015
 */
public class EntryTreeCell extends TreeCell<GenericModelEntry> {
	/**
	 * Constructor.
	 *
	 * @param entryTabPane
	 *            {@link #entryTabPane}
	 * @param factories
	 *            the {@link EntryTreeCellContentFactory}s.
	 */
	public EntryTreeCell(final EntryTabPane entryTabPane, final EntryTreeCellContentFactory<?>... factories) {
		this.entryTabPane = entryTabPane;
		this.factories = new HashSet<>(Arrays.asList(factories));
	}

	/**
	 * @see javafx.scene.control.Cell#updateItem(java.lang.Object, boolean)
	 */
	@Override
	public final void updateItem(final GenericModelEntry entry, final boolean empty) {
		super.updateItem(entry, empty);
		if (empty) {
			clearContent();
		} else {
			addContent(entry);
		}
	}

	/**
	 * @param entry
	 */
	@SuppressWarnings("unchecked")
	private <T extends GenericModelEntry> void addContent(final GenericModelEntry entry) {
		setText(null);
		final T e = (T) entry;
		final EntryTreeCellContent<T> content = getContent(e);
		if (content == null) {
			setGraphic(null);
			setContextMenu(null);
		} else {
			setOnMouseClicked(event -> {
				if (event.getClickCount() == 2) {
					entryTabPane.show(entry);
				}
			});
			setGraphic(content.getGrapic(e));
			setContextMenu(content.getContextMenu(e));
		}
	}

	/**
	 * Clear content.
	 */
	private void clearContent() {
		setText(null);
		setGraphic(null);
	}

	/**
	 * Is {@link EntryTreeCellContentFactory} type and {@link GenericModelEntry} type equals.
	 *
	 * @param factory
	 *            the factory
	 * @param entry
	 *            the entry
	 * @return <code>true</code> if so, otherwise <code>false</code>
	 */
	private boolean equals(final EntryTreeCellContentFactory<?> factory, final GenericModelEntry entry) {
		final Class<?> factoryType = factory.getType();
		final Class<?> entryType = entry.getClass();
		return factoryType.isAssignableFrom(entryType);
	}

	/**
	 * Get the content for a {@link GenericModelEntry}.
	 *
	 * @param entry
	 *            the entry
	 * @return the content {@link Node}
	 */
	@SuppressWarnings("unchecked")
	private <T extends GenericModelEntry> EntryTreeCellContent<T> getContent(final T entry) {
		final Optional<EntryTreeCellContentFactory<?>> optional = factories.stream().filter(f -> equals(f, entry)).findFirst();
		if (!optional.isPresent()) return null;
		final EntryTreeCellContentFactory<T> factory = (EntryTreeCellContentFactory<T>) optional.get();
		final EntryTreeCellContent<T> content = factory.create();
		return content;
	}

	private final EntryTabPane entryTabPane;

	/** the {@link EntryTreeCellContentFactory}s. */
	private final Set<EntryTreeCellContentFactory<?>> factories;
}
