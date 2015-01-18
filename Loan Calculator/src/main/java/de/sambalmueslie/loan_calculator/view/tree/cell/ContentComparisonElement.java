/**
 *
 */
package de.sambalmueslie.loan_calculator.view.tree.cell;

import static de.sambalmueslie.loan_calculator.view.Constants.CLASS_PANEL;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import de.sambalmueslie.loan_calculator.model.compare.Comparison;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;
import de.sambalmueslie.loan_calculator.view.icons.IconProvider;

/**
 * The {@link EntryTreeCellContent} for a comparison content.
 *
 * @author sambalmueslie 2015
 */
class ContentComparisonElement extends GridPane implements EntryTreeCellContent<GenericModelEntry> {

	/**
	 * Constructor.
	 *
	 * @param comparison
	 *            the {@link Comparison}
	 * @param listener
	 *            the {@link ViewActionListener}
	 */
	ContentComparisonElement(final GenericModelEntry entry, final ViewActionListener listener) {
		getStyleClass().add(CLASS_PANEL);

		if (entry instanceof Founding) {
			add(IconProvider.createImageView(IconProvider.ICON_FOLDER_PAGE), 0, 0);
		} else if (entry instanceof Loan) {
			add(IconProvider.createImageView(IconProvider.ICON_NOTE), 0, 0);
		}

		final Label name = new Label(entry.getName());
		add(name, 1, 0);

	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.tree.cell.EntryTreeCellContent#getContextMenu()
	 */
	@Override
	public ContextMenu getContextMenu() {
		return null;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.tree.cell.EntryTreeCellContent#getGrapic()
	 */
	@Override
	public Node getGrapic() {
		return this;
	}

}
