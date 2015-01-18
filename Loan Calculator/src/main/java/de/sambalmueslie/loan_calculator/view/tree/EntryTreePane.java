/**
 *
 */
package de.sambalmueslie.loan_calculator.view.tree;

import static de.sambalmueslie.loan_calculator.view.Constants.CLASS_HEADLINE_LABEL;
import static de.sambalmueslie.loan_calculator.view.Constants.CLASS_PANEL;

import java.util.Optional;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import de.sambalmueslie.loan_calculator.controller.file.LoanFile;
import de.sambalmueslie.loan_calculator.model.Model;
import de.sambalmueslie.loan_calculator.model.compare.Comparison;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntryChangeListener;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;
import de.sambalmueslie.loan_calculator.view.tabs.EntryTabPane;
import de.sambalmueslie.loan_calculator.view.tree.cell.EntryTreeCell;
import de.sambalmueslie.loan_calculator.view.tree.contextmenu.BaseContextMenu;

/**
 * The entry tree pane.
 *
 * @author sambalmueslie 2015
 */
public class EntryTreePane extends BorderPane {

	/**
	 * The handler for the {@link Comparison} {@link GenericModelEntryChangeListener}.
	 *
	 * @author sambalmueslie 2015
	 */
	private class ComparisonChangeHandler implements GenericModelEntryChangeListener {
		/**
		 * @see de.sambalmueslie.loan_calculator.model.generic.GenericModelEntryChangeListener#entryChanged(de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry)
		 */
		@Override
		public void entryChanged(final GenericModelEntry entry) {
			handleChanged((Comparison<?>) entry);
		}
	}

	/**
	 * The handler for the {@link Founding} {@link GenericModelEntryChangeListener}.
	 *
	 * @author sambalmueslie 2015
	 */
	private class FoundingChangeHandler implements GenericModelEntryChangeListener {
		/**
		 * @see de.sambalmueslie.loan_calculator.model.generic.GenericModelEntryChangeListener#entryChanged(de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry)
		 */
		@Override
		public void entryChanged(final GenericModelEntry entry) {
			handleChanged((Founding) entry);
		}
	}

	/**
	 * Constructor.
	 *
	 * @param entryTabPane
	 *            {@link #entryTabPane}
	 * @param listener
	 *            {@link #listener}
	 */
	public EntryTreePane(final EntryTabPane entryTabPane, final ViewActionListener listener) {
		this.entryTabPane = entryTabPane;
		this.listener = listener;
		getStyleClass().add(CLASS_PANEL);

		setupTitle();
		setupTreeView();
	}

	/**
	 * Show.
	 *
	 * @param loanFile
	 *            the {@link LoanFile}.
	 */
	public void show(final LoanFile loanFile) {
		treeView = new EntryTreeView();
		treeView.setCellFactory(entry -> new EntryTreeCell(entry, entryTabPane, listener));
		model = loanFile.getModel();
		comparisonChangeHandler = new ComparisonChangeHandler();
		foundingChangeHandler = new FoundingChangeHandler();

		setupTreeView();

		final ModelChangeHandler modelChangeHandler = new ModelChangeHandler(this);
		model.listenerRegister(modelChangeHandler);

		model.getAllLoans().forEach(this::add);
		model.getAllFoundings().forEach(this::add);
	}

	/**
	 * Add a {@link Comparison}.
	 *
	 * @param comparison
	 *            the comparison
	 */
	void add(final Comparison<?> comparison) {
		treeView.addRootWithChildren(comparison, EntryTreeContentType.COMPARISON, comparison.getElements(), EntryTreeContentType.COMPARISON_ELEMENT);
		comparison.register(comparisonChangeHandler);
	}

	/**
	 * Add a {@link Founding}.
	 *
	 * @param founding
	 *            the founding
	 */
	void add(final Founding founding) {
		treeView.removeAll(founding.getLoans());
		treeView.addRootWithChildren(founding, EntryTreeContentType.FOUNDING, founding.getLoans(), EntryTreeContentType.LOAN);
		founding.register(foundingChangeHandler);
	}

	/**
	 * Add a new {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	void add(final Loan loan) {
		final Founding founding = getParentFounding(loan);
		if (founding != null) {
			treeView.remove(loan);
			treeView.addChild(founding, loan, EntryTreeContentType.LOAN);
		} else {
			treeView.addRoot(loan, EntryTreeContentType.LOAN);
		}
	}

	/**
	 * Remove a {@link Comparison}.
	 *
	 * @param comparison
	 *            the comparison
	 */
	void remove(final Comparison<?> comparison) {
		treeView.remove(comparison);
		comparison.unregister(comparisonChangeHandler);
	}

	/**
	 * Remove a {@link Founding}.
	 *
	 * @param founding
	 *            the founding
	 */
	void remove(final Founding founding) {
		treeView.remove(founding);
		founding.unregister(foundingChangeHandler);
	}

	/**
	 * Remove a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	void remove(final Loan loan) {
		treeView.remove(loan);
	}

	/**
	 * Get the parent {@link Founding} for a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 * @return the {@link Founding} or <code>null</code> if not found
	 */
	private Founding getParentFounding(final Loan loan) {
		final Optional<Founding> optional = model.getAllFoundings().stream().filter(f -> f.getLoans().contains(loan)).findAny();
		return (optional.isPresent()) ? optional.get() : null;
	}

	/**
	 * Handle the change of a {@link Comparison}.
	 *
	 * @param comparison
	 *            the comparison
	 */
	private void handleChanged(final Comparison<?> comparison) {
		treeView.updateWithChildren(comparison, EntryTreeContentType.COMPARISON, comparison.getElements(), EntryTreeContentType.COMPARISON_ELEMENT);
	}

	/**
	 * Handle the change of a {@link Founding}.
	 *
	 * @param founding
	 *            the founding
	 */
	private void handleChanged(final Founding founding) {
		treeView.removeAll(founding.getLoans());
		treeView.updateWithChildren(founding, EntryTreeContentType.FOUNDING, founding.getLoans(), EntryTreeContentType.LOAN);
	}

	/**
	 * Setup the title.
	 */
	private void setupTitle() {
		final HBox title = new HBox();
		title.getStyleClass().add(CLASS_PANEL);
		title.setAlignment(Pos.CENTER);
		final Label overview = new Label("Overview");
		overview.getStyleClass().add(CLASS_HEADLINE_LABEL);
		title.getChildren().add(overview);
		setTop(title);
	}

	/**
	 * Setup the tree view.
	 */
	private void setupTreeView() {
		treeView.setContextMenu(new BaseContextMenu(listener));
		setCenter(treeView);
	}

	/** the {@link ComparisonChangeHandler}. */
	private ComparisonChangeHandler comparisonChangeHandler = new ComparisonChangeHandler();

	/** the {@link EntryTabPane}. */
	private final EntryTabPane entryTabPane;
	/** the {@link FoundingChangeHandler}. */
	private FoundingChangeHandler foundingChangeHandler = new FoundingChangeHandler();
	/** the {@link ViewActionListener}. */
	private final ViewActionListener listener;
	/** the {@link Model}. */
	private Model model;
	/** the {@link EntryTreeView} */
	private EntryTreeView treeView = new EntryTreeView();

}
