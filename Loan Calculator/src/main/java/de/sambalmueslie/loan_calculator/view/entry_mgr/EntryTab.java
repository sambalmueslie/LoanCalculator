/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;
import de.sambalmueslie.loan_calculator.model.loan.AnnuityLoan;
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
	 */
	public EntryTab(final GenericModelEntry<?> entry) {
		this.entry = entry;
		setClosable(false);
		if (entry instanceof AnnuityLoan) {
			final AnnuityLoan annuityLoan = (AnnuityLoan) entry;
			final Node content = new AnnuityLoanPanel(annuityLoan);
			final String name = annuityLoan.getName();
			setContent(new ScrollPane(content));
			setText(name);
		} else if (entry instanceof Founding) {
			final Founding founding = (Founding) entry;
			final Node content = new FoundingPanel(founding);
			final String name = founding.getName();
			setContent(new ScrollPane(content));
			setText(name);
		}
	}

	/**
	 * @return the {@link #entry}
	 */
	GenericModelEntry<?> getEntry() {
		return entry;
	}

	/** the {@link GenericModelEntry}. */
	private final GenericModelEntry<?> entry;

}
