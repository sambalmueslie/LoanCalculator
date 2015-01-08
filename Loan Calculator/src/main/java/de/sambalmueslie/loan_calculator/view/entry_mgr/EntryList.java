/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr;

import static de.sambalmueslie.loan_calculator.view.Constants.DEFAULT_SPACING;
import static de.sambalmueslie.loan_calculator.view.Constants.TITLE_FONT;

import java.util.LinkedList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import de.sambalmueslie.loan_calculator.model.Model;
import de.sambalmueslie.loan_calculator.model.ModelChangeListener;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;

/**
 * The entry manager.
 *
 * @author sambalmueslie 2015
 */
public class EntryList extends GridPane {

	/**
	 * The handler for the {@link ModelChangeListener}.
	 *
	 * @author sambalmueslie 2015
	 */
	private class ModelChangeHandler implements ModelChangeListener {

		/**
		 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#foundingAdded(de.sambalmueslie.loan_calculator.model.founding.Founding)
		 */
		@Override
		public void foundingAdded(final Founding founding) {
			add(founding);
		}

		/**
		 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#foundingRemoved(de.sambalmueslie.loan_calculator.model.founding.Founding)
		 */
		@Override
		public void foundingRemoved(final Founding founding) {
			remove(founding);
		}

		/**
		 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#loanAdded(de.sambalmueslie.loan_calculator.model.loan.Loan)
		 */
		@Override
		public void loanAdded(final Loan loan) {
			add(loan);
		}

		/**
		 * @see de.sambalmueslie.loan_calculator.model.ModelChangeListener#loanRemoved(de.sambalmueslie.loan_calculator.model.loan.Loan)
		 */
		@Override
		public void loanRemoved(final Loan loan) {
			remove(loan);
		}

	}

	/**
	 * Constructor.
	 *
	 * @param model
	 *            the {@link Model}
	 * @param actionForwarder
	 *            the {@link ViewActionListener}
	 */
	public EntryList(final Model model, final ViewActionListener actionForwarder) {
		modelChangeHandler = new ModelChangeHandler();
		model.listenerRegister(modelChangeHandler);

		setHgap(DEFAULT_SPACING);
		setVgap(DEFAULT_SPACING);

		final HBox title = new HBox(DEFAULT_SPACING);
		title.setAlignment(Pos.CENTER);
		final Label overview = new Label("Overview");
		overview.setFont(TITLE_FONT);
		title.getChildren().add(overview);
		add(title, 0, 0);

		entries = FXCollections.observableArrayList();
		entryList = new ListView<>(entries);

		final EntryListCellContentFactory<?> foundingFactory = new EntryListCellContentFactory<Founding>(Founding.class, FoundingEntryListCell.class);
		foundingFactory.setListener(actionForwarder);
		final EntryListCellContentFactory<?> loanFactory = new EntryListCellContentFactory<Loan>(Loan.class, LoanEntryListCell.class);
		loanFactory.setListener(actionForwarder);

		entryList.setCellFactory(entry -> new EntryListCell(foundingFactory, loanFactory));
		add(entryList, 0, 1);

		entryList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		entryList.getSelectionModel().selectedItemProperty().addListener((ChangeListener<GenericModelEntry<?>>) (ov, old_val, new_val) -> {
			selectionListeners.forEach(l -> l.changed(ov, old_val, new_val));
		});
	}

	/**
	 * Register a selection {@link ChangeListener};
	 *
	 * @param listener
	 *            the listener
	 */
	public void registerSelectionChangeListener(final ChangeListener<GenericModelEntry<?>> listener) {
		if (listener == null || selectionListeners.contains(listener)) return;
		selectionListeners.add(listener);
	}

	/**
	 * Unregister a selection {@link ChangeListener};
	 *
	 * @param listener
	 *            the listener
	 */
	public void unregisterSelectionChangeListener(final ChangeListener<GenericModelEntry<?>> listener) {
		if (listener == null) return;
		selectionListeners.remove(listener);
	}

	/**
	 * Add a {@link GenericModelEntry}.
	 *
	 * @param entry
	 *            the entry
	 */
	private void add(final GenericModelEntry<?> entry) {
		entries.add(entry);
	}

	/**
	 * Remove a {@link GenericModelEntry}.
	 *
	 * @param entry
	 *            the entry
	 */
	private void remove(final GenericModelEntry<?> entry) {
		entries.remove(entry);
	}

	/** the current managed {@link GenericModelEntry}s. */
	private final ObservableList<GenericModelEntry<?>> entries;
	/** the {@link ListView} for the {@link GenericModelEntry}s. */
	private final ListView<GenericModelEntry<?>> entryList;
	/** the {@link ModelChangeHandler}. */
	private final ModelChangeHandler modelChangeHandler;
	/** the selection {@link ChangeListener}. */
	private final List<ChangeListener<GenericModelEntry<?>>> selectionListeners = new LinkedList<>();
}
