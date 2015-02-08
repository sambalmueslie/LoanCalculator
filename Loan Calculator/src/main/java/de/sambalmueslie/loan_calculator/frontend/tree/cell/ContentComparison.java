/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.tree.cell;

import static de.sambalmueslie.loan_calculator.frontend.Constants.CLASS_PANEL;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import de.sambalmueslie.loan_calculator.backend.compare_mgt.Comparison;
import de.sambalmueslie.loan_calculator.backend.founding_mgt.Founding;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;
import de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener;
import de.sambalmueslie.loan_calculator.frontend.icons.IconProvider;
import de.sambalmueslie.loan_calculator.frontend.tree.contextmenu.ComparisonContextMenu;

/**
 * The {@link EntryTreeCellContent} for a {@link Loan}.
 *
 * @author sambalmueslie 2015
 */
class ContentComparison extends GridPane implements EntryTreeCellContent<Comparison<?>> {

	/**
	 * Constructor.
	 *
	 * @param comparison
	 *            the {@link Comparison}
	 * @param listener
	 *            the {@link ViewActionListener}
	 */
	ContentComparison(final Comparison<?> comparison, final ViewActionListener listener) {
		getStyleClass().add(CLASS_PANEL);

		contextMenu = new ComparisonContextMenu(comparison, listener);

		if (comparison.getType().equals(Founding.class)) {
			add(IconProvider.createImageView(IconProvider.ICON_LIST_IMAGES), 0, 0);
		} else if (comparison.getType().equals(Loan.class)) {
			add(IconProvider.createImageView(IconProvider.ICON_PAGE_COMPONENT), 0, 0);
		}

		final Label name = new Label(comparison.getName());
		add(name, 1, 0);

		setOnDragOver(event -> {
			if (comparison != null) {
				event.acceptTransferModes(TransferMode.MOVE);
			}
			event.consume();
		});

		setOnDragDropped(event -> {

			final Dragboard db = event.getDragboard();

			final String strId = db.hasString() ? db.getString() : null;
			if (strId != null) {
				final long elementId = Long.parseLong(strId);
				final long comparisonId = comparison.getId();
				if (comparison.getType().equals(Founding.class)) {
					listener.requestComparisonAddFounding(comparisonId, elementId);
				} else if (comparison.getType().equals(Loan.class)) {
					listener.requestComparisonAddLoan(comparisonId, elementId);
				}
			}

			event.setDropCompleted(strId != null);
			event.consume();
		});
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.tree.cell.EntryTreeCellContent#getContextMenu()
	 */
	@Override
	public ContextMenu getContextMenu() {
		return contextMenu;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.tree.cell.EntryTreeCellContent#getGrapic()
	 */
	@Override
	public Node getGrapic() {
		return this;
	}

	/** the {@link ComparisonContextMenu}. */
	private final ComparisonContextMenu contextMenu;

}
