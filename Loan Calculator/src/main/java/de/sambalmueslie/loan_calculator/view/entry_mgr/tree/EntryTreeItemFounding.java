/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr.tree;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;
import de.sambalmueslie.loan_calculator.view.entry_mgr.FoundingContextMenu;

/**
 * The {@link Founding} {@link EntryTreeItemContent}.
 *
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

		setOnDragOver(event -> {
			if (founding != null) {
				event.acceptTransferModes(TransferMode.MOVE);
			}
			event.consume();
		});

		setOnDragDropped(event -> {
			if (founding == null) return;

			final Dragboard db = event.getDragboard();

			final String strId = db.hasString() ? db.getString() : null;
			if (strId != null) {
				final long loanId = Long.parseLong(strId);
				final long foundingId = founding.getId();
				listener.requestFoundingAddLoan(foundingId, loanId);
			}

			event.setDropCompleted(strId != null);
			event.consume();
		});
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.entry_mgr.list.EntryListCellContent#getContextMenu(de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry)
	 */
	@Override
	public ContextMenu getContextMenu(final Founding founding) {
		this.founding = founding;
		contextMenu.set(founding);
		return contextMenu;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.entry_mgr.list.EntryListCellContent#getGrapic(de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry)
	 */
	@Override
	public Node getGrapic(final Founding founding) {
		this.founding = founding;
		name.setText(founding.getName());
		return this;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.entry_mgr.list.EntryListCellContent#set(de.sambalmueslie.loan_calculator.view.ViewActionListener)
	 */
	@Override
	public void set(final ViewActionListener listener) {
		this.listener = listener;
		contextMenu.setListener(listener);
	}

	/** the {@link FoundingContextMenu}. */
	private final FoundingContextMenu contextMenu = new FoundingContextMenu();
	/** the {@link Founding}. */
	private Founding founding;
	/** the {@link ViewActionListener}. */
	private ViewActionListener listener;
	/** the name {@link Label}. */
	private final Label name = new Label();

}
