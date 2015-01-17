/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr.tree;

import static de.sambalmueslie.loan_calculator.view.Constants.CLASS_PANEL;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import de.sambalmueslie.loan_calculator.model.compare.Comparison;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;
import de.sambalmueslie.loan_calculator.view.entry_mgr.contextmenu.ComparisonContextMenu;

/**
 * The {@link EntryTreeCellContent} for the {@link Comparison}.
 *
 * @author sambalmueslie 2015
 */
public class EntryTreeCellComparison<T extends GenericModelEntry> extends GridPane implements EntryTreeCellContent<Comparison<T>> {

	/**
	 * Constructor.
	 */
	public EntryTreeCellComparison() {
		getStyleClass().add(CLASS_PANEL);

		final ImageView icon = IconProvider.createImageView(IconProvider.ICON_NOTE);
		iconPane.setCenter(icon);
		add(iconPane, 0, 0);

		add(name, 1, 0);

		setOnDragOver(event -> {
			if (comparison != null) {
				event.acceptTransferModes(TransferMode.MOVE);
			}
			event.consume();
		});

		setOnDragDropped(event -> {
			if (comparison == null) return;

			final Dragboard db = event.getDragboard();

			final String strId = db.hasString() ? db.getString() : null;
			if (strId != null) {
				final long elementId = Long.parseLong(strId);
				final long comparisonId = comparison.getId();
				// FIXME this is some kind of ugly :-)
				listener.requestComparisonAddFounding(comparisonId, elementId);
				listener.requestComparisonAddLoan(comparisonId, elementId);
			}

			event.setDropCompleted(strId != null);
			event.consume();
		});
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.entry_mgr.list.EntryListCellContent#getContextMenu(de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry)
	 */
	@Override
	public ContextMenu getContextMenu(final Comparison<T> comparison) {
		this.comparison = comparison;
		contextMenu.set(comparison);
		return contextMenu;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.entry_mgr.list.EntryListCellContent#getGrapic(de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry)
	 */
	@Override
	public Node getGrapic(final Comparison<T> comparison) {
		this.comparison = comparison;
		name.setText(comparison.getName());
		if (comparison.getType().equals(Founding.class)) {
			iconPane.setCenter(IconProvider.createImageView(IconProvider.ICON_LIST_IMAGES));
		} else if (comparison.getType().equals(Loan.class)) {
			iconPane.setCenter(IconProvider.createImageView(IconProvider.ICON_PAGE_COMPONENT));
		}

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

	/** the {@link Comparison<?>}. */
	private Comparison<?> comparison;
	/** the {@link ComparisonContextMenu}. */
	private final ComparisonContextMenu contextMenu = new ComparisonContextMenu();
	/** the icon pane. */
	private final BorderPane iconPane = new BorderPane();
	/** the {@link ViewActionListener}. */
	private ViewActionListener listener;
	/** the name {@link Label}. */
	private final Label name = new Label();
}
