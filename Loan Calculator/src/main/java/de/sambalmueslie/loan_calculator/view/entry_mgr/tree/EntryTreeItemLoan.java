/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr.tree;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.Constants;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;
import de.sambalmueslie.loan_calculator.view.entry_mgr.LoanContextMenu;

/**
 * @author sambalmueslie 2015
 */
public class EntryTreeItemLoan extends GridPane implements EntryTreeItemContent<Loan> {
	/**
	 * Constructor.
	 */
	public EntryTreeItemLoan() {
		setHgap(Constants.DEFAULT_SPACING);
		setVgap(Constants.DEFAULT_SPACING);

		add(icon, 0, 0);
		add(name, 1, 0);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.entry_mgr.list.EntryListCellContent#getContextMenu(de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry)
	 */
	@Override
	public ContextMenu getContextMenu(final Loan entry) {
		contextMenu.set(entry);
		return contextMenu;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.entry_mgr.list.EntryListCellContent#getGrapic(de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry)
	 */
	@Override
	public Node getGrapic(final Loan entry) {
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

	/** the {@link LoanContextMenu}. */
	private final LoanContextMenu contextMenu = new LoanContextMenu();
	/** the icon {@link ImageView}. */
	private final ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("page.gif")));
	/** the name {@link Label}. */
	private final Label name = new Label();
}
