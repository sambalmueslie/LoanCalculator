/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.tree.cell;

import static de.sambalmueslie.loan_calculator.frontend.Constants.CLASS_PANEL;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;
import de.sambalmueslie.loan_calculator.frontend.ViewActionListener;
import de.sambalmueslie.loan_calculator.frontend.icons.IconProvider;
import de.sambalmueslie.loan_calculator.frontend.tree.contextmenu.LoanContextMenu;

/**
 * The {@link EntryTreeCellContent} for a {@link Loan}.
 *
 * @author sambalmueslie 2015
 */
class ContentLoan extends GridPane implements EntryTreeCellContent<Loan> {

	/**
	 * Constructor.
	 *
	 * @param loan
	 *            the {@link Loan}
	 * @param listener
	 *            the {@link ViewActionListener}
	 */
	ContentLoan(final Loan loan, final ViewActionListener listener) {
		getStyleClass().add(CLASS_PANEL);

		contextMenu = new LoanContextMenu(loan, listener);

		final ImageView icon = IconProvider.createImageView(IconProvider.ICON_NOTE);
		add(icon, 0, 0);

		final Label name = new Label(loan.getName());
		add(name, 1, 0);

		setOnDragDetected(event -> {
			final Dragboard dragBoard = startDragAndDrop(TransferMode.MOVE);
			final ClipboardContent content = new ClipboardContent();
			content.put(DataFormat.PLAIN_TEXT, Long.toString(loan.getId()));
			dragBoard.setContent(content);
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

	/** the {@link LoanContextMenu}. */
	private final LoanContextMenu contextMenu;

}
