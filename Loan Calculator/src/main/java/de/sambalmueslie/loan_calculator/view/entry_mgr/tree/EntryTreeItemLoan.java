/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr.tree;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;
import de.sambalmueslie.loan_calculator.view.entry_mgr.LoanContextMenu;

/**
 * @author sambalmueslie 2015
 */
public class EntryTreeItemLoan extends GridPane implements EntryTreeItemContent<Loan> {

	/** the {@link Logger}. */
	private static Logger logger = LogManager.getLogger(EntryTreeItemLoan.class);

	/**
	 * Constructor.
	 */
	public EntryTreeItemLoan() {
		getStyleClass().add("entry-tree-item");

		final ImageView icon = IconProvider.createImageView(IconProvider.ICON_NOTE);
		add(icon, 0, 0);

		add(name, 1, 0);

		setOnDragDetected(event -> {
			if (loan == null) return;
			logger.info("Start drag of " + Long.toHexString(loan.getId()) + " " + loan.getName());
			final Dragboard dragBoard = startDragAndDrop(TransferMode.MOVE);
			final ClipboardContent content = new ClipboardContent();
			content.put(DataFormat.PLAIN_TEXT, Long.toString(loan.getId()));
			dragBoard.setContent(content);
			event.consume();
		});
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.entry_mgr.list.EntryListCellContent#getContextMenu(de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry)
	 */
	@Override
	public ContextMenu getContextMenu(final Loan loan) {
		this.loan = loan;
		contextMenu.set(loan);
		return contextMenu;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.entry_mgr.list.EntryListCellContent#getGrapic(de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry)
	 */
	@Override
	public Node getGrapic(final Loan loan) {
		this.loan = loan;
		name.setText(loan.getName());
		return this;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.entry_mgr.list.EntryListCellContent#set(de.sambalmueslie.loan_calculator.view.ViewActionListener)
	 */
	@Override
	public void set(final ViewActionListener listener) {
		contextMenu.setListener(listener);
	}

	/** the {@link LoanContextMenu}. */
	private final LoanContextMenu contextMenu = new LoanContextMenu();
	/** the {@link Loan}. */
	private Loan loan;
	/** the name {@link Label}. */
	private final Label name = new Label();
}
