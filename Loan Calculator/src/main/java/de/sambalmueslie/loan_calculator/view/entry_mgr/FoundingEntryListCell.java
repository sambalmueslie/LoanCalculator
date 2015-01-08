/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.view.Constants;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;

/**
 * The {@link Founding} entry {@link ListCell}.
 *
 * @author sambalmueslie 2015
 */
public class FoundingEntryListCell extends GridPane implements EntryListCellContent<Founding> {

	/**
	 * Constructor.
	 */
	public FoundingEntryListCell() {
		setHgap(Constants.DEFAULT_SPACING);
		setVgap(Constants.DEFAULT_SPACING);

		add(name, 0, 0);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.entry_mgr.EntryListCellContent#getContextMenu(de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry)
	 */
	@Override
	public ContextMenu getContextMenu(final Founding entry) {
		// TODO Auto-generated method stub
		return contextMenu;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.entry_mgr.EntryListCellContent#getGrapic(de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry)
	 */
	@Override
	public Node getGrapic(final Founding entry) {
		name.setText(entry.getName());
		return this;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.entry_mgr.EntryListCellContent#set(de.sambalmueslie.loan_calculator.view.ViewActionListener)
	 */
	@Override
	public void set(final ViewActionListener listener) {
		// TODO Auto-generated method stub

	}

	/** the {@link ContextMenu}. */
	private final ContextMenu contextMenu = new ContextMenu();
	/** the name {@link Label}. */
	private final Label name = new Label();

}
