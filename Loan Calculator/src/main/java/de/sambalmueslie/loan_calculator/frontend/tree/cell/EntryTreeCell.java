/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.tree.cell;

import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import de.sambalmueslie.loan_calculator.backend.compare_mgt.Comparison;
import de.sambalmueslie.loan_calculator.backend.founding_mgt.Founding;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;
import de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener;
import de.sambalmueslie.loan_calculator.frontend.tabs.EntryTabPane;
import de.sambalmueslie.loan_calculator.frontend.tree.EntryTreeContent;

/**
 * The entry {@link TreeCell}.
 *
 * @author sambalmueslie 2015
 */
public class EntryTreeCell extends TreeCell<EntryTreeContent> {

	/**
	 * Constructor.
	 *
	 * @param entry
	 *            {@link #entry}
	 * @param entryTabPane
	 *            {@link #entryTabPane}
	 * @param listener
	 *            {@link #listener}
	 */
	public EntryTreeCell(final TreeView<EntryTreeContent> entry, final EntryTabPane entryTabPane, final ViewActionListener listener) {
		this.entry = entry;
		this.entryTabPane = entryTabPane;
		this.listener = listener;
	}

	/**
	 * @return the {@link #entry}
	 */
	protected TreeView<EntryTreeContent> getEntry() {
		return entry;
	}

	/**
	 * @return the {@link #listener}
	 */
	protected ViewActionListener getListener() {
		return listener;
	}

	/**
	 * @see javafx.scene.control.Cell#updateItem(java.lang.Object, boolean)
	 */
	@Override
	protected void updateItem(final EntryTreeContent item, final boolean empty) {
		super.updateItem(item, empty);
		if (empty) {
			clearContent();
		} else {
			addContent(item);
		}
	}

	/**
	 * Add some content.
	 *
	 * @param item
	 *            the {@link EntryTreeContent}
	 */
	private void addContent(final EntryTreeContent item) {
		setText(null);
		final EntryTreeCellContent<?> content = getContent(item);
		if (content == null) {
			setGraphic(null);
			setContextMenu(null);
		} else {
			setOnMouseClicked(event -> {
				if (event.getClickCount() == 2) {
					entryTabPane.show(item.getContent());
				}
			});
			setGraphic(content.getGrapic());
			setContextMenu(content.getContextMenu());
		}
	}

	/**
	 * Clear content.
	 */
	private void clearContent() {
		setText(null);
		setGraphic(null);
	}

	/**
	 * Get the content for a {@link EntryTreeContent}.
	 *
	 * @param item
	 *            the item
	 * @return the {@link EntryTreeCellContent}
	 */
	private EntryTreeCellContent<?> getContent(final EntryTreeContent item) {
		switch (item.getType()) {
		case LOAN:
			return new ContentLoan((Loan) item.getContent(), listener);
		case FOUNDING:
			return new ContentFounding((Founding) item.getContent(), listener);
		case COMPARISON:
			return new ContentComparison((Comparison<?>) item.getContent(), listener);
		default:
		case COMPARISON_ELEMENT:
			return new ContentComparisonElement(item.getContent(), listener);
		}
	}

	/** the {@link TreeView} */
	private final TreeView<EntryTreeContent> entry;

	/** the {@link EntryTabPane}. */
	private final EntryTabPane entryTabPane;
	/** the {@link ViewActionListener}. */
	private final ViewActionListener listener;

}
