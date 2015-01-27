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
import de.sambalmueslie.loan_calculator.model.loan.AnnuityLoan;
import de.sambalmueslie.loan_calculator.view.component.CurrencyTextField;
import de.sambalmueslie.loan_calculator.view.component.NumberTextField;
import de.sambalmueslie.loan_calculator.view.component.PercentageTextField;
import de.sambalmueslie.loan_calculator.view.component.SimpleTextField;
import de.sambalmueslie.loan_calculator.view.i18n.I18n;

/**
 * {@link Dialog} to add or update a {@link AnnuityLoan}.
 *
 * @author sambalmueslie 2015
 */
public class ModifyAnnuityLoanDialog extends Dialog<ButtonType> {

	/** default value. */
	private static final double DEFAULT_AMOUNT = 50000.0;
	/** default value. */
	private static final double DEFAULT_ESTIMATED_DEBIT_INTEREST = 5.00;
	/** default value. */
	private static final double DEFAULT_FIXED_DEBIT_INTEREST = 2.00;
	/** default value. */
	private static final int DEFAULT_FIXED_INTEREST_PERIOD = 100;
	/** default value. */
	private static final double DEFAULT_PAYMENT_RATE = 3.00;

	/**
	 * Constructor.
	 */
	public ModifyAnnuityLoanDialog(final AnnuityLoan loan) {
		final String dialogTitle = (loan == null) ? I18n.get(I18n.ANNUITY_DIALOG_TITLE_NEW) : I18n.get(I18n.ANNUITY_DIALOG_TITLE_UPDATE);
		setTitle(dialogTitle);

		if (loan != null) {
			name.setValue(loan.getName());
			amount.setValue(loan.getAmount());
			paymentRate.setValue(loan.getPaymentRate());
			fixedDebitInterest.setValue(loan.getFixedDebitInterest());
			fixedInterestPeriod.setValue(loan.getFixedInterestPeriod());
			estimatedDebitInterest.setValue(loan.getEstimatedDebitInterest());
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

		content.add(new Label(I18n.get(I18n.TEXT_PAYMENT_RATE)), 0, 2);
		content.add(paymentRate, 1, 2);

		content.add(new Label(I18n.get(I18n.TEXT_FIXED_DEBIT_INTEREST)), 0, 3);
		content.add(fixedDebitInterest, 1, 3);

		content.add(new Label(I18n.get(I18n.TEXT_FIXED_INTEREST_PERIOD)), 0, 4);
		content.add(fixedInterestPeriod, 1, 4);

		content.add(new Label(I18n.get(I18n.TEXT_ESTIMATED_DEBIT_INTEREST)), 0, 5);
		content.add(estimatedDebitInterest, 1, 5);

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
	 * @return the estimated debit interest.
	 */
	public double getEstimatedDebitInterest() {
		return estimatedDebitInterest.getValue();
	}

	/**
	 * @return the fixed debit interest.
	 */
	public double getFixedDebitInterest() {
		return fixedDebitInterest.getValue();
	}

	/**
	 * @return the fixed interest period.
	 */
	public int getFixedInterestPeriod() {
		return fixedInterestPeriod.getValue();
	}

	/**
	 * @return the name.
	 */
	public String getName() {
		return name.getValue();
	}

	/**
	 * @return the payment rate.
	 */
	public double getPaymentRate() {
		return paymentRate.getValue();
	}

	/** the amount {@link CurrencyTextField}. */
	private final CurrencyTextField amount = new CurrencyTextField(DEFAULT_AMOUNT);
	/** the estimated debit interest {@link PercentageTextField}. */
	private final PercentageTextField estimatedDebitInterest = new PercentageTextField(DEFAULT_ESTIMATED_DEBIT_INTEREST);
	/** the fixed debit interest {@link PercentageTextField}. */
	private final PercentageTextField fixedDebitInterest = new PercentageTextField(DEFAULT_FIXED_DEBIT_INTEREST);
	/** the fixed interest {@link NumberTextField}. */
	private final NumberTextField fixedInterestPeriod = new NumberTextField(DEFAULT_FIXED_INTEREST_PERIOD);
	/** the name {@link SimpleTextField}. */
	private final SimpleTextField name = new SimpleTextField();
	/** the payment rate {@link PercentageTextField}. */
	private final PercentageTextField paymentRate = new PercentageTextField(DEFAULT_PAYMENT_RATE);

}
