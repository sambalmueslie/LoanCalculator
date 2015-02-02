/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.tabs;

import static de.sambalmueslie.loan_calculator.frontend.Constants.CLASS_PANEL;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import de.sambalmueslie.loan_calculator.backend.common.BusinessObject;
import de.sambalmueslie.loan_calculator.backend.compare_mgt.Comparison;
import de.sambalmueslie.loan_calculator.backend.founding_mgt.Founding;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreement;
import de.sambalmueslie.loan_calculator.backend.model.Model;
import de.sambalmueslie.loan_calculator.frontend.ViewActionListener;
import de.sambalmueslie.loan_calculator.frontend.compare.ComparePanel;
import de.sambalmueslie.loan_calculator.frontend.compare.ComparePanelFactory;
import de.sambalmueslie.loan_calculator.frontend.panel.AnnuityLoanPanel;
import de.sambalmueslie.loan_calculator.frontend.panel.BuildingLoanAgreementPanel;
import de.sambalmueslie.loan_calculator.frontend.panel.FoundingPanel;

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
	public EntryTab(final BusinessObject entry, final ViewActionListener actionListener, final Model model) {
		this.entry = entry;
		this.actionListener = actionListener;
		this.model = model;

		getStyleClass().add(CLASS_PANEL);
		setClosable(true);
		final Node content = getContent(entry);
		final ScrollPane scrollPane = new ScrollPane(content);
		setContent(scrollPane);
		final String name = entry.getName();
		setText(name);
	}

	/**
	 * @return the {@link #entry}
	 */
	BusinessObject getEntry() {
		return entry;
	}

	/**
	 * Get the content {@link Node} for a {@link BusinessObject}.
	 *
	 * @param entry
	 *            the entry
	 * @return the content
	 */
	private Node getContent(final BusinessObject entry) {
		if (entry instanceof AnnuityLoan) {
			final AnnuityLoan annuityLoan = (AnnuityLoan) entry;
			return new AnnuityLoanPanel(annuityLoan);
		}
		if (entry instanceof BuildingLoanAgreement) {
			final BuildingLoanAgreement buildingLoanAgreement = (BuildingLoanAgreement) entry;
			return new BuildingLoanAgreementPanel(buildingLoanAgreement);
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
	/** the {@link BusinessObject}. */
	private final BusinessObject entry;
	/** the {@link Model}. */
	private final Model model;

}
