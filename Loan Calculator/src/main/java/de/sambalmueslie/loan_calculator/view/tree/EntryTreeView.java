/**
 *
 */
package de.sambalmueslie.loan_calculator.view.tree;

import java.util.Collection;
import java.util.Optional;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;

/**
 * The {@link TreeView} for entries.
 *
 * @author sambalmueslie 2015
 */
class EntryTreeView extends TreeView<EntryTreeContent> {

	/**
	 * Constructor.
	 */
	EntryTreeView() {
		super(new TreeItem<>());
		setShowRoot(false);
		setEditable(false);
	}

	/**
	 * Add a child {@link EntryTreeContent} for a parent {@link GenericModelEntry}.
	 *
	 * @param parent
	 *            the parent entry
	 * @param entry
	 *            the entry
	 * @param type
	 *            the {@link EntryTreeContentType}
	 */
	void addChild(final GenericModelEntry parent, final GenericModelEntry entry, final EntryTreeContentType type) {
		final TreeItem<EntryTreeContent> parentItem = find(parent, getRoot());
		if (parentItem == null) return;
		parentItem.getChildren().add(new TreeItem<EntryTreeContent>(new EntryTreeContent(entry, type)));
	}

	/**
	 * Add a root {@link EntryTreeContent}.
	 *
	 * @param entry
	 *            the entry
	 * @param type
	 *            the {@link EntryTreeContentType}
	 */
	void addRoot(final GenericModelEntry entry, final EntryTreeContentType type) {
		final EntryTreeContent content = new EntryTreeContent(entry, type);
		getRoot().getChildren().add(new TreeItem<EntryTreeContent>(content));
	}

	/**
	 * Add a root {@link GenericModelEntry} with children.
	 *
	 * @param entry
	 *            the root entry
	 * @param type
	 *            the root type
	 * @param children
	 *            the children
	 * @param childType
	 *            the children type
	 */
	void addRootWithChildren(final GenericModelEntry entry, final EntryTreeContentType type, final Collection<? extends GenericModelEntry> children,
			final EntryTreeContentType childType) {
		final EntryTreeContent content = new EntryTreeContent(entry, type);
		final TreeItem<EntryTreeContent> rootItem = new TreeItem<EntryTreeContent>(content);
		children.stream().map(c -> new TreeItem<>(new EntryTreeContent(c, childType))).forEach(rootItem.getChildren()::add);
		getRoot().getChildren().add(rootItem);
		rootItem.setExpanded(true);
	}

	/**
	 * Remove a {@link GenericModelEntry}.
	 *
	 * @param entry
	 *            the entry
	 */
	void remove(final GenericModelEntry entry) {
		remove(entry, getRoot());
	}

	/**
	 * Remove all {@link GenericModelEntry}s.
	 *
	 * @param entries
	 *            the entries
	 * @param <T>
	 *            the {@link GenericModelEntry} type
	 */
	<T extends GenericModelEntry> void removeAll(final Collection<T> entries) {
		entries.forEach(this::remove);
	}

	/**
	 * Update the tree view item.
	 *
	 * @param entry
	 *            the changed entry
	 * @param type
	 *            the {@link EntryTreeContentType}
	 */
	void update(final GenericModelEntry entry, final EntryTreeContentType type) {
		final TreeItem<EntryTreeContent> item = find(entry, getRoot());
		if (item == null) return;
		item.setValue(new EntryTreeContent(entry, type));
	}

	/**
	 * Update a tree view item with children.
	 *
	 * @param entry
	 *            the changed entry
	 * @param type
	 *            the {@link EntryTreeContentType}
	 * @param children
	 *            the children
	 * @param childType
	 *            the children {@link EntryTreeContentType}
	 */
	void updateWithChildren(final GenericModelEntry entry, final EntryTreeContentType type, final Collection<? extends GenericModelEntry> children,
			final EntryTreeContentType childType) {
		final TreeItem<EntryTreeContent> rootItem = find(entry, getRoot());
		if (rootItem == null) return;
		rootItem.setValue(new EntryTreeContent(entry, type));
		rootItem.getChildren().clear();
		children.stream().map(c -> new TreeItem<>(new EntryTreeContent(c, childType))).forEach(rootItem.getChildren()::add);
		rootItem.setExpanded(true);

	}

	/**
	 * Check if a {@link GenericModelEntry} is equals to a {@link TreeItem}.
	 *
	 * @param entry
	 *            the entry
	 * @param item
	 *            the tree item
	 * @return <code>true</code> if so, otherwise <code>false</code>
	 */
	private boolean equals(final GenericModelEntry entry, final TreeItem<EntryTreeContent> item) {
		return item.getValue().getContent().equals(entry);
	}

	/**
	 * Find a {@link TreeItem} for a {@link GenericModelEntry}.
	 *
	 * @param entry
	 *            the entry
	 * @param parent
	 *            the parent
	 * @return the {@link TreeItem} or <code>null</code> if not found
	 */
	private TreeItem<EntryTreeContent> find(final GenericModelEntry entry, final TreeItem<EntryTreeContent> parent) {
		final Optional<TreeItem<EntryTreeContent>> optional = parent.getChildren().stream().filter(children -> equals(entry, children)).findAny();
		if (optional.isPresent()) return optional.get();
		for (final TreeItem<EntryTreeContent> children : parent.getChildren()) {
			final TreeItem<EntryTreeContent> result = find(entry, children);
			if (result != null) return result;
		}
		return null;
	}

	/**
	 * Remove a {@link GenericModelEntry} from the parent {@link TreeItem} and all its children.
	 *
	 * @param entry
	 *            the entry
	 * @param parent
	 *            the parent
	 */
	private void remove(final GenericModelEntry entry, final TreeItem<EntryTreeContent> parent) {
		parent.getChildren().removeIf(children -> equals(entry, children));
		parent.getChildren().forEach(children -> remove(entry, children));
	}
}
