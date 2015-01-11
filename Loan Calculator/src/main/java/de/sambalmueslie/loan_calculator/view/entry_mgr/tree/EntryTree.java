/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr.tree;

import static de.sambalmueslie.loan_calculator.view.Constants.DEFAULT_SPACING;

import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import de.sambalmueslie.loan_calculator.model.Model;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;

/**
 * @author sambalmueslie 2015
 */
public class EntryTree extends GridPane {

	/**
	 * Constructor.
	 *
	 * @param model
	 *            the {@link Model}
	 * @param actionForwarder
	 *            the {@link ViewActionListener}
	 */
	public EntryTree(final Model model, final ViewActionListener actionForwarder) {
		modelChangeHandler = new ModelChangeHandler(this);
		model.listenerRegister(modelChangeHandler);

		setHgap(DEFAULT_SPACING);
		setVgap(DEFAULT_SPACING);

		final HBox title = new HBox(DEFAULT_SPACING);
		title.setAlignment(Pos.CENTER);
		final Label overview = new Label("Overview");
		overview.getStyleClass().add("headline-label");
		title.getChildren().add(overview);
		add(title, 0, 0);

		treeView = new TreeView<>(new TreeItem<>());
		treeView.setShowRoot(false);
		treeView.setEditable(false);

		final EntryTreeItemContentFactory<?> foundingFactory = new EntryTreeItemContentFactory<Founding>(Founding.class, EntryTreeItemFounding.class);
		foundingFactory.setListener(actionForwarder);
		final EntryTreeItemContentFactory<?> loanFactory = new EntryTreeItemContentFactory<Loan>(Loan.class, EntryTreeItemLoan.class);
		loanFactory.setListener(actionForwarder);

		treeView.setCellFactory(entry -> new EntryTreeItem(foundingFactory, loanFactory));

		add(treeView, 0, 1);

		treeView.setContextMenu(new EntryTreeContextMenu(actionForwarder));
	}

	/**
	 * Add a {@link Founding}.
	 *
	 * @param founding
	 *            the founding
	 */
	void add(final Founding founding) {
		final TreeItem<GenericModelEntry<?>> foundingItem = new TreeItem<>(founding);
		treeView.getRoot().getChildren().add(foundingItem);
		assignFoundingChildren(founding, foundingItem);
		founding.register(this::foundingChanged);
		foundingItem.setExpanded(true);
	}

	/**
	 * Add a new {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	void add(final Loan loan) {
		final TreeItem<GenericModelEntry<?>> loanItem = new TreeItem<>(loan);
		final TreeItem<GenericModelEntry<?>> foundingParent = getLoanFoundingParent(loan);
		if (foundingParent != null) {
			foundingParent.getChildren().add(loanItem);
		} else {
			treeView.getRoot().getChildren().add(loanItem);
		}
	}

	/**
	 * Remove a {@link Founding}.
	 *
	 * @param founding
	 *            the founding
	 */
	void remove(final Founding founding) {
		final ObservableList<TreeItem<GenericModelEntry<?>>> rootChildren = treeView.getRoot().getChildren();
		rootChildren.removeIf(n -> n.getValue().equals(founding));
	}

	/**
	 * Remove a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	void remove(final Loan loan) {
		final ObservableList<TreeItem<GenericModelEntry<?>>> rootChildren = treeView.getRoot().getChildren();
		rootChildren.removeIf(n -> n.getValue().equals(loan));
	}

	/**
	 * Assign the children of a founding to the {@link TreeItem}.
	 *
	 * @param founding
	 *            the {@link Founding}
	 * @param foundingItem
	 *            the founding {@link TreeItem}.
	 */
	private void assignFoundingChildren(final Founding founding, final TreeItem<GenericModelEntry<?>> foundingItem) {
		final ObservableList<TreeItem<GenericModelEntry<?>>> rootChildren = treeView.getRoot().getChildren();
		for (final Loan loan : founding.getLoans()) {
			final TreeItem<GenericModelEntry<?>> loanItem = getTreeItemByValue(loan, rootChildren);
			if (loanItem != null) {
				rootChildren.remove(loanItem);
				foundingItem.getChildren().add(loanItem);
			}
		}
	}

	/**
	 * The {@link Founding} has changed.
	 *
	 * @param founding
	 */
	private void foundingChanged(final Founding founding) {
		final ObservableList<TreeItem<GenericModelEntry<?>>> rootChildren = treeView.getRoot().getChildren();
		final TreeItem<GenericModelEntry<?>> foundingItem = getTreeItemByValue(founding, rootChildren);
		if (foundingItem != null) {
			assignFoundingChildren(founding, foundingItem);
		}
	}

	/**
	 * Get the {@link Founding} parent {@link TreeItem} for a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 * @return the {@link TreeItem} or <code>null</code> if not exists.
	 */
	private TreeItem<GenericModelEntry<?>> getLoanFoundingParent(final Loan loan) {
		final ObservableList<TreeItem<GenericModelEntry<?>>> rootChildren = treeView.getRoot().getChildren();
		final Optional<TreeItem<GenericModelEntry<?>>> result = rootChildren.stream()
				.filter(item -> item.getValue() instanceof Founding && ((Founding) item.getValue()).getLoans().contains(loan)).findFirst();
		return result.isPresent() ? result.get() : null;
	}

	/**
	 * Get a {@link TreeItem} from a list by value.
	 *
	 * @param value
	 *            the value to search for
	 * @param treeItems
	 *            the list to search
	 * @return the {@link TreeItem} if found, otherwise <code>null</code>
	 */
	private TreeItem<GenericModelEntry<?>> getTreeItemByValue(final GenericModelEntry<?> value, final ObservableList<TreeItem<GenericModelEntry<?>>> treeItems) {
		final Optional<TreeItem<GenericModelEntry<?>>> result = treeItems.stream().filter(item -> item.getValue().equals(value)).findFirst();
		return (result.isPresent()) ? result.get() : null;
	}

	/** the {@link ModelChangeHandler}. */
	private final ModelChangeHandler modelChangeHandler;
	/** the {@link TreeView}. */
	private final TreeView<GenericModelEntry<?>> treeView;

}
