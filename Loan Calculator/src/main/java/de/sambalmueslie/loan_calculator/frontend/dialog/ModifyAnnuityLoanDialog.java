/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.dialog;

import java.time.LocalDate;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoanSettings;
import de.sambalmueslie.loan_calculator.frontend.component.CurrencyTextField;
import de.sambalmueslie.loan_calculator.frontend.component.NumberTextField;
import de.sambalmueslie.loan_calculator.frontend.component.PercentageTextField;
import de.sambalmueslie.loan_calculator.frontend.component.SimpleTextField;
import de.sambalmueslie.loan_calculator.frontend.i18n.I18n;

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
	/** default value. */
	private static final double DEFAULT_UNSCHEDULED_REPAYMENT = 0.00;

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
			startDatePicker.setValue(loan.getStartDate());
			unscheduledRepayment.setValue(loan.getUnscheduledRepayment());
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

		content.add(new Label(I18n.get(I18n.TEXT_UNSCHEDULED_REPAYMENT)), 0, 6);
		content.add(unscheduledRepayment, 1, 6);

		content.add(new Label(I18n.get(I18n.TEXT_START_DATE)), 0, 7);
		content.add(startDatePicker, 1, 7);

		getDialogPane().setContent(content);

		dialogPane.setHeaderText("HEADER TEXT");
		dialogPane.getStyleClass().add("choice-dialog");
		dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		setResultConverter(dialogButton -> dialogButton);
	}

	/**
	 * @return the {@link AnnuityLoanSettings}.
	 */
	public AnnuityLoanSettings getSettings() {
		return new AnnuityLoanSettings(getName(), getAmount(), getStartDate(), getPaymentRate(), getFixedDebitInterest(), getFixedInterestPeriod(),
				getEstimatedDebitInterest(), getUnscheduledRepayment());
	}

	/**
	 * @return the amount.
	 */
	private double getAmount() {
		return amount.getValue();
	}

	/**
	 * @return the estimated debit interest.
	 */
	private double getEstimatedDebitInterest() {
		return estimatedDebitInterest.getValue();
	}

	/**
	 * @return the fixed debit interest.
	 */
	private double getFixedDebitInterest() {
		return fixedDebitInterest.getValue();
	}

	/**
	 * @return the fixed interest period.
	 */
	private int getFixedInterestPeriod() {
		return fixedInterestPeriod.getValue();
	}

	/**
	 * @return the name.
	 */
	private String getName() {
		return name.getValue();
	}

	/**
	 * @return the payment rate.
	 */
	private double getPaymentRate() {
		return paymentRate.getValue();
	}

	/**
	 * @return the start date.
	 */
	private LocalDate getStartDate() {
		final LocalDate startDate = startDatePicker.getValue();
		return (startDate == null) ? LocalDate.now() : startDate;
	}

	/**
	 * @return the unscheduled repayment.
	 */
	private double getUnscheduledRepayment() {
		return unscheduledRepayment.getValue();
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
	/** the start {@link DatePicker}. */
	private final DatePicker startDatePicker = new DatePicker();
	/** the unscheduled repayment {@link PercentageTextField}. */
	private final PercentageTextField unscheduledRepayment = new PercentageTextField(DEFAULT_UNSCHEDULED_REPAYMENT);

}
