/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.dialog;

import java.time.LocalDate;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreement;
import de.sambalmueslie.loan_calculator.frontend.component.CurrencyTextField;
import de.sambalmueslie.loan_calculator.frontend.component.NumberTextField;
import de.sambalmueslie.loan_calculator.frontend.component.PercentageTextField;
import de.sambalmueslie.loan_calculator.frontend.component.SimpleTextField;
import de.sambalmueslie.loan_calculator.frontend.i18n.I18n;

/**
 * {@link Dialog} to add or update a {@link BuildingLoanAgreement}.
 *
 * @author sambalmueslie 2015
 */
public class ModifyBuildingLoanAgreementDialog extends Dialog<ButtonType> {

	/** default value. */
	private static final double DEFAULT_AMOUNT = 100000.0;
	/** default value. */
	private static final double DEFAULT_AQUISITION_FEE = 1.0;
	/** default value. */
	private static final double DEFAULT_CONTRIBUTION = 7.0;
	/** default value. */
	private static final double DEFAULT_CREDIT_INTEREST = 0.25;
	/** default value. */
	private static final double DEFAULT_DEBIT_INTEREST = 1.5;
	/** default value. */
	private static final double DEFAULT_MINIMUM_SAVINGS = 25.0;
	/** default value. */
	private static final double DEFAULT_REGULAR_SAVING_AMOUNT = 5.0;
	/** default value. */
	private static final int DEFAULT_SAVING_DURATION = 10;
	/** default value. */
	private static final double DEFAULT_SAVING_PHASE_INTEREST = 1.5;

	/**
	 * Constructor.
	 */
	public ModifyBuildingLoanAgreementDialog(final BuildingLoanAgreement loan) {
		final String dialogTitle = (loan == null) ? I18n.get(I18n.BUILDING_LOAN_AGREEMENT_DIALOG_TITLE_NEW) : I18n
				.get(I18n.BUILDING_LOAN_AGREEMENT_DIALOG_TITLE_UPDATE);
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
			startDatePicker.setValue(loan.getStartDate());
		}

		final DialogPane dialogPane = getDialogPane();

		final GridPane content = new GridPane();
		content.setHgap(10);
		content.setVgap(10);
		content.setMaxWidth(Double.MAX_VALUE);
		content.setAlignment(Pos.CENTER_LEFT);

		content.add(new Label(I18n.get(I18n.TEXT_NAME)), 0, 0);
		content.add(name, 1, 0);

		content.add(new Label(I18n.get(I18n.TEXT_AMOUNT)), 0, 1);
		content.add(amount, 1, 1);

		content.add(new Label(I18n.get(I18n.TEXT_CREDIT_INTERST)), 0, 2);
		content.add(creditInterest, 1, 2);

		content.add(new Label(I18n.get(I18n.TEXT_REGULAR_SAVING_AMOUNT)), 0, 3);
		content.add(regularSavingAmount, 1, 3);

		content.add(new Label(I18n.get(I18n.TEXT_MINIMUM_SAVINGS)), 0, 4);
		content.add(minimumSavings, 1, 4);

		content.add(new Label(I18n.get(I18n.TEXT_SAVING_DURATION)), 0, 5);
		content.add(savingDuration, 1, 5);

		content.add(new Label(I18n.get(I18n.TEXT_SAVING_PHASE_INTEREST)), 0, 6);
		content.add(savingPhaseInterest, 1, 6);

		content.add(new Label(I18n.get(I18n.TEXT_DEBIT_INTEREST)), 0, 7);
		content.add(debitInterest, 1, 7);

		content.add(new Label(I18n.get(I18n.TEXT_CONTRIBUTION)), 0, 8);
		content.add(contribution, 1, 8);

		content.add(new Label(I18n.get(I18n.TEXT_AQUISITION_FEE)), 0, 9);
		content.add(aquisitonFee, 1, 9);

		content.add(new Label(I18n.get(I18n.TEXT_START_DATE)), 0, 10);
		content.add(startDatePicker, 1, 10);

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

	/**
	 * @return the start date.
	 */
	public LocalDate getStartDate() {
		final LocalDate startDate = startDatePicker.getValue();
		return (startDate == null) ? LocalDate.now() : startDate;
	}

	/** the amount {@link CurrencyTextField}. */
	private final CurrencyTextField amount = new CurrencyTextField(DEFAULT_AMOUNT);
	/** the acquisition fee {@link PercentageTextField}. */
	private final PercentageTextField aquisitonFee = new PercentageTextField(DEFAULT_AQUISITION_FEE);
	/** the contribution {@link PercentageTextField}. */
	private final PercentageTextField contribution = new PercentageTextField(DEFAULT_CONTRIBUTION);
	/** the credit interest {@link PercentageTextField}. */
	private final PercentageTextField creditInterest = new PercentageTextField(DEFAULT_CREDIT_INTEREST);
	/** the debit interest {@link PercentageTextField}. */
	private final PercentageTextField debitInterest = new PercentageTextField(DEFAULT_DEBIT_INTEREST);
	/** the minimum savings {@link PercentageTextField}. */
	private final PercentageTextField minimumSavings = new PercentageTextField(DEFAULT_MINIMUM_SAVINGS);
	/** the name {@link SimpleTextField}. */
	private final SimpleTextField name = new SimpleTextField();
	/** the regular saving amount {@link PercentageTextField}. */
	private final PercentageTextField regularSavingAmount = new PercentageTextField(DEFAULT_REGULAR_SAVING_AMOUNT);
	/** the saving duration {@link PercentageTextField}. */
	private final NumberTextField savingDuration = new NumberTextField(DEFAULT_SAVING_DURATION);
	/** the saving phase interest {@link PercentageTextField}. */
	private final PercentageTextField savingPhaseInterest = new PercentageTextField(DEFAULT_SAVING_PHASE_INTEREST);
	/** the start {@link DatePicker}. */
	private final DatePicker startDatePicker = new DatePicker();

}
