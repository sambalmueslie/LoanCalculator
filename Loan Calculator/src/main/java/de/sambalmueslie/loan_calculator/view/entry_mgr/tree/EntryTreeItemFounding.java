/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr.tree;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;
import de.sambalmueslie.loan_calculator.view.entry_mgr.FoundingContextMenu;

/**
 * @author sambalmueslie 2015
 */
public class EntryTreeItemFounding extends GridPane implements EntryTreeItemContent<Founding> {

	/**
	 * Constructor.
	 */
	public EntryTreeItemFounding() {
		getStyleClass().add("entry-tree-item");

		final ImageView icon = IconProvider.createImageView(IconProvider.ICON_FOLDER_PAGE);
		add(icon, 0, 0);

		add(name, 1, 0);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.entry_mgr.list.EntryListCellContent#getContextMenu(de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry)
	 */
	@Override
	public ContextMenu getContextMenu(final Founding entry) {
		contextMenu.set(entry);
		return contextMenu;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.entry_mgr.list.EntryListCellContent#getGrapic(de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry)
	 */
	@Override
	public Node getGrapic(final Founding entry) {
		name.setText(entry.getName());
		return this;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.entry_mgr.list.EntryListCellContent#set(de.sambalmueslie.loan_calculator.view.ViewActionListener)
	 */
	@Override
	public void set(final ViewActionListener listener) {
		contextMenu.setListener(listener);
	}

	/** the {@link FoundingContextMenu}. */
	private final FoundingContextMenu contextMenu = new FoundingContextMenu();
	/** the name {@link Label}. */
	private final Label name = new Label();

}
