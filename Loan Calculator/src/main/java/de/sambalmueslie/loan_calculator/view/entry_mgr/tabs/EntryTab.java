/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr.tabs;

import static de.sambalmueslie.loan_calculator.view.Constants.CLASS_PANEL;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import de.sambalmueslie.loan_calculator.model.Model;
import de.sambalmueslie.loan_calculator.model.compare.Comparison;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;
import de.sambalmueslie.loan_calculator.model.loan.AnnuityLoan;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;
import de.sambalmueslie.loan_calculator.view.compare.ComparePanel;
import de.sambalmueslie.loan_calculator.view.compare.ComparePanelFactory;
import de.sambalmueslie.loan_calculator.view.panel.AnnuityLoanPanel;
import de.sambalmueslie.loan_calculator.view.panel.FoundingPanel;

/**
 * A entry {@link Tab}.
 *
 * @author sambalmueslie 2015
 */
public class EntryTab extends Tab {

	/**
	 * Constructor.
	 *
	 * @param entry
	 *            {@link #entry}
	 * @param actionListener
	 *            {@link #actionListener}
	 * @param model
	 *            {@link #model}
	 */
	public EntryTab(final GenericModelEntry entry, final ViewActionListener actionListener, final Model model) {
		this.entry = entry;
		this.actionListener = actionListener;
		this.model = model;

		getStyleClass().add(CLASS_PANEL);
		setClosable(false);
		final Node content = getContent(entry);
		final ScrollPane scrollPane = new ScrollPane(content);
		setContent(scrollPane);
		final String name = entry.getName();
		setText(name);
	}

	/**
	 * @return the {@link #entry}
	 */
	GenericModelEntry getEntry() {
		return entry;
	}

	/**
	 * Get the content {@link Node} for a {@link GenericModelEntry}.
	 *
	 * @param entry
	 *            the entry
	 * @return the content
	 */
	private Node getContent(final GenericModelEntry entry) {
		if (entry instanceof AnnuityLoan) {
			final AnnuityLoan annuityLoan = (AnnuityLoan) entry;
			return new AnnuityLoanPanel(annuityLoan);
		}
		if (entry instanceof Founding) {
			final Founding founding = (Founding) entry;
			return new FoundingPanel(founding);
		}
		if (entry instanceof Comparison) {
			final Comparison<?> comparison = (Comparison<?>) entry;
			final ComparePanel<?> panel = ComparePanelFactory.createComparePanel(comparison, actionListener, model);
			return panel.getContent();
		}
		return null;
	}

	/** the {@link ViewActionListener}. */
	private final ViewActionListener actionListener;
	/** the {@link GenericModelEntry}. */
	private final GenericModelEntry entry;
	/** the {@link Model}. */
	private final Model model;

}
