/**
 *
 */
package de.sambalmueslie.loan_calculator.view.dialog;

import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import de.sambalmueslie.loan_calculator.model.loan.BuildingLoanAgreement;
import de.sambalmueslie.loan_calculator.view.component.CurrencyTextField;
import de.sambalmueslie.loan_calculator.view.component.NumberTextField;
import de.sambalmueslie.loan_calculator.view.component.PercentageTextField;
import de.sambalmueslie.loan_calculator.view.component.SimpleTextField;

/**
 * {@link Dialog} to add or update a {@link BuildingLoanAgreement}.
 *
 * @author sambalmueslie 2015
 */
public class ModifyBuildingLoanAgreementDialog extends Dialog<ButtonType> {

	/**
	 * Constructor.
	 */
	public ModifyBuildingLoanAgreementDialog(final BuildingLoanAgreement loan) {
		final String dialogTitle = (loan == null) ? "Add new building loan agreement." : "Update building loan agreement";
		setTitle(dialogTitle);

		if (loan != null) {
			name.setValue(loan.getName());
			amount.setValue(loan.getAmount());
			aquisitonFee.setValue(loan.getAquisitonFee());
			contribution.setValue(loan.getContribution());
			creditInterest.setValue(loan.getCreditInterest());
			debitInterest.setValue(loan.getDebitInterest());
			minimumSavings.setValue(loan.getMinimumSavings());
			regularSavingAmount.setValue(loan.getRegularSavingAmount());
			savingDuration.setValue(loan.getSavingDuration());
			savingPhaseInterest.setValue(loan.getSavingPhaseInterest());
		}

		final DialogPane dialogPane = getDialogPane();

		final GridPane content = new GridPane();
		content.setHgap(10);
		content.setVgap(10);
		content.setMaxWidth(Double.MAX_VALUE);
		content.setAlignment(Pos.CENTER_LEFT);

		content.add(new Label("Name"), 0, 0);
		content.add(name, 1, 0);

		content.add(new Label("Amount"), 0, 1);
		content.add(amount, 1, 1);

		content.add(new Label("Credit interest"), 0, 2);
		content.add(creditInterest, 1, 2);

		content.add(new Label("Regular saving amount"), 0, 3);
		content.add(regularSavingAmount, 1, 3);

		content.add(new Label("Minimum savings"), 0, 4);
		content.add(minimumSavings, 1, 4);

		content.add(new Label("Saving duration"), 0, 5);
		content.add(savingDuration, 1, 5);

		content.add(new Label("Saving phase interest"), 0, 6);
		content.add(savingPhaseInterest, 1, 6);

		content.add(new Label("Debit interest"), 0, 7);
		content.add(debitInterest, 1, 7);

		content.add(new Label("Contribution"), 0, 8);
		content.add(contribution, 1, 8);

		content.add(new Label("Aquisition fee"), 0, 9);
		content.add(aquisitonFee, 1, 9);

		getDialogPane().setContent(content);

		dialogPane.setHeaderText("HEADER TEXT");
		dialogPane.getStyleClass().add("choice-dialog");
		dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		setResultConverter(dialogButton -> dialogButton);
	}

	/**
	 * @return the amount.
	 */
	public double getAmount() {
		return amount.getValue();
	}

	/**
	 * @return the aquisition feed.
	 */
	public double getAquisitionFee() {
		return aquisitonFee.getValue();
	}

	/**
	 * @return the contribution.
	 */
	public double getContribution() {
		return contribution.getValue();
	}

	/**
	 * @return the credit interest.
	 */
	public double getCreditInterest() {
		return creditInterest.getValue();
	}

	/**
	 * @return the debit interest.
	 */
	public double getDebitInterest() {
		return debitInterest.getValue();
	}

	/**
	 * @return the minimum savings.
	 */
	public double getMinimumSavings() {
		return minimumSavings.getValue();
	}

	/**
	 * @return the name.
	 */
	public String getName() {
		return name.getValue();
	}

	/**
	 * @return the regular saving amount.
	 */
	public double getRegularSavingAmount() {
		return regularSavingAmount.getValue();
	}

	/**
	 * @return the saving duration.
	 */
	public int getSavingDuration() {
		return savingDuration.getValue();
	}

	/**
	 * @return the saving phase interest.
	 */
	public double getSavingPhaseInterest() {
		return savingPhaseInterest.getValue();
	}

	/** the amount {@link CurrencyTextField}. */
	private final CurrencyTextField amount = new CurrencyTextField(100000.0);
	/** the acquisition fee {@link PercentageTextField}. */
	private final PercentageTextField aquisitonFee = new PercentageTextField(1.0);
	/** the contribution {@link PercentageTextField}. */
	private final PercentageTextField contribution = new PercentageTextField(7.0);
	/** the credit interest {@link PercentageTextField}. */
	private final PercentageTextField creditInterest = new PercentageTextField(0.25);
	/** the debit interest {@link PercentageTextField}. */
	private final PercentageTextField debitInterest = new PercentageTextField(1.5);
	/** the minimum savings {@link PercentageTextField}. */
	private final PercentageTextField minimumSavings = new PercentageTextField(25.0);
	/** the name {@link SimpleTextField}. */
	private final SimpleTextField name = new SimpleTextField();
	/** the regular saving amount {@link PercentageTextField}. */
	private final PercentageTextField regularSavingAmount = new PercentageTextField(5.0);
	/** the saving duration {@link PercentageTextField}. */
	private final NumberTextField savingDuration = new NumberTextField(10);
	/** the saving phase interest {@link PercentageTextField}. */
	private final PercentageTextField savingPhaseInterest = new PercentageTextField(1.5);

}
