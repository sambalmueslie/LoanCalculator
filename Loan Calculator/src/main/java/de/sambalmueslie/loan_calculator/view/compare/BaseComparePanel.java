/**
 *
 */
package de.sambalmueslie.loan_calculator.view.compare;

import java.util.Set;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import de.sambalmueslie.loan_calculator.model.Model;
import de.sambalmueslie.loan_calculator.model.compare.Comparison;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;

/**
 * A base {@link ComparePanel}.
 *
 * @author sambalmueslie 2015
 * @param <T>
 *            the compare value type
 */
public abstract class BaseComparePanel<T extends GenericModelEntry> extends BorderPane implements ComparePanel<T> {

	/**
	 * Constructor.
	 *
	 * @param comparison
	 *            {@link #comparison}
	 */
	protected BaseComparePanel(final Comparison<T> comparison, final ViewActionListener actionListener, final Model model) {
		this.comparison = comparison;
		this.actionListener = actionListener;
		this.model = model;

		comparison.register(c -> update((Comparison) c));
		setup(comparison.getElements());

		setOnDragOver(event -> {
			final Dragboard db = event.getDragboard();
			final String strId = db.hasString() ? db.getString() : null;
			if (strId != null) {
				final long entryId = Long.parseLong(strId);
				if (isDragValid(entryId)) {
					event.acceptTransferModes(TransferMode.MOVE);
				}
			}
			event.consume();
		});

		setOnDragDropped(event -> {
			final Dragboard db = event.getDragboard();
			final String strId = db.hasString() ? db.getString() : null;
			if (strId != null) {
				final long entryId = Long.parseLong(strId);
				requestComparisonAddEntry(entryId);
			}

			event.setDropCompleted(strId != null);
			event.consume();
		});

		setOnMouseClicked(e -> showContextMenu(e));

	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.compare.ComparePanel#getContent()
	 */
	@Override
	public final Node getContent() {
		return this;
	}

	/**
	 * Get a entry by id.
	 *
	 * @param entryId
	 *            the entry id
	 * @return the entry or <code>null</code>
	 */
	protected abstract T get(long entryId);

	/**
	 * @return the {@link #actionListener}
	 */
	protected ViewActionListener getActionListener() {
		return actionListener;
	}

	/**
	 * @return the {@link #comparison}
	 */
	protected Comparison<T> getComparison() {
		return comparison;
	}

	/**
	 * @return the {@link ContextMenu}.
	 */
	protected abstract ContextMenu getContextMenu();

	/**
	 * @return the {@link #model}
	 */
	protected Model getModel() {
		return model;
	}

	protected abstract void requestComparisonAddEntry(long entryId);

	/**
	 * Setup.
	 *
	 * @param elements
	 *            the elements
	 */
	protected abstract void setup(Set<T> elements);

	/**
	 * Check if the drag is valid.
	 *
	 * @param entryId
	 *            the entry id
	 * @return <code>true</code> if so, otherwise <code>false</code>.
	 */
	private boolean isDragValid(final long entryId) {
		final T entry = get(entryId);
		if (entry == null) return false;
		return getComparison().getElements().stream().noneMatch(f -> f.getId() == entryId);
	}

	/**
	 * Show the context menu.
	 *
	 * @param e
	 *            the {@link MouseEvent}
	 */
	private void showContextMenu(final MouseEvent e) {
		if (e.getButton() == MouseButton.SECONDARY) {
			getContextMenu().show(this, e.getScreenX(), e.getScreenY());
		}
	}

	/**
	 * Update.
	 */
	private void update(final Comparison<T> comparison) {
		setup(comparison.getElements());
	}

	/** the {@link ViewActionListener}. */
	private final ViewActionListener actionListener;

	/** the {@link Comparison}. */
	private final Comparison<T> comparison;
	/** the {@link Model}. */
	private final Model model;

}
