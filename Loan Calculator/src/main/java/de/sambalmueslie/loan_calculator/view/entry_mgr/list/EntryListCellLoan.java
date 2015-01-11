/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr.list;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.Constants;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;
import de.sambalmueslie.loan_calculator.view.entry_mgr.LoanContextMenu;

/**
 * The {@link Loan} entry {@link ListCell}.
 *
 * @author sambalmueslie 2015
 */
public class EntryListCellLoan extends GridPane implements EntryListCellContent<Loan> {

	/**
	 * Constructor.
	 */
	public EntryListCellLoan() {
		setHgap(Constants.DEFAULT_SPACING);
		setVgap(Constants.DEFAULT_SPACING);

		add(name, 0, 0);
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
	/** the name {@link Label}. */
	private final Label name = new Label();

}
