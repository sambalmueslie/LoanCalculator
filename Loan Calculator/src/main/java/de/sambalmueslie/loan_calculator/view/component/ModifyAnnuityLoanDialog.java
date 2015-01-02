/**
 *
 */
package de.sambalmueslie.loan_calculator.view.component;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import de.sambalmueslie.loan_calculator.model.AnnuityLoan;

/**
 * {@link Dialog} to add or update a {@link AnnuityLoan}.
 *
 * @author sambalmueslie 2015
 */
public class ModifyAnnuityLoanDialog extends Dialog<ButtonType> {

	/**
	 * Constructor.
	 */
	public ModifyAnnuityLoanDialog(final AnnuityLoan loan) {
		String dialogTitle = (loan == null) ? "Add new loan." : "Update loan";
		setTitle(dialogTitle);

		final DialogPane dialogPane = getDialogPane();

		final GridPane content = new GridPane();
		content.setHgap(10);
		content.setVgap(10);
		content.setMaxWidth(Double.MAX_VALUE);
		content.setAlignment(Pos.CENTER_LEFT);

		content.add(new Label("Name"), 0, 0);
		final TextField textFieldName = new TextField();
		textFieldName.textProperty().bindBidirectional(name);
		content.add(textFieldName, 1, 0);

		content.add(new Label("Amount"), 0, 1);
		final DoubleField amountTextField = new DoubleField(amount);
		content.add(amountTextField, 1, 1);

		content.add(new Label("Payment rate"), 0, 2);
		final DoubleField paymentRateTextField = new DoubleField(paymentRate);
		content.add(paymentRateTextField, 1, 2);

		content.add(new Label("Fixed debit interest"), 0, 3);
		final DoubleField fixedDebitInterestTextField = new DoubleField(fixedDebitInterest);
		content.add(fixedDebitInterestTextField, 1, 3);

		content.add(new Label("Fixed interest period"), 0, 4);
		final IntegerField fixedInterestPeriodTextField = new IntegerField(fixedInterestPeriod);
		content.add(fixedInterestPeriodTextField, 1, 4);

		content.add(new Label("Estimated debit interest"), 0, 5);
		final DoubleField estimatedDebitInterestTextField = new DoubleField(estimatedDebitInterest);
		content.add(estimatedDebitInterestTextField, 1, 5);

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
		return amount.get();
	}

	/**
	 * @return the estimated debit interest.
	 */
	public double getEstimatedDebitInterest() {
		return estimatedDebitInterest.get();
	}

	/**
	 * @return the fixed debit interest.
	 */
	public double getFixedDebitInterest() {
		return fixedDebitInterest.get();
	}

	/**
	 * @return the fixed interest period.
	 */
	public int getFixedInterestPeriod() {
		return fixedInterestPeriod.get();
	}

	/**
	 * @return the name.
	 */
	public String getName() {
		return name.get();
	}

	/**
	 * @return the payment rate.
	 */
	public double getPaymentRate() {
		return paymentRate.get();
	}

	/** the amount {@link SimpleDoubleProperty}. */
	private final SimpleDoubleProperty amount = new SimpleDoubleProperty(50000);
	/** the estimated debit interest {@link SimpleDoubleProperty}. */
	private final SimpleDoubleProperty estimatedDebitInterest = new SimpleDoubleProperty(5.00);
	/** the fixed debit interest {@link SimpleDoubleProperty}. */
	private final SimpleDoubleProperty fixedDebitInterest = new SimpleDoubleProperty(2.00);
	/** the fixed interest {@link SimpleIntegerProperty}. */
	private final SimpleIntegerProperty fixedInterestPeriod = new SimpleIntegerProperty(50000);
	/** the name {@link SimpleStringProperty}. */
	private final SimpleStringProperty name = new SimpleStringProperty();
	/** the payment rate {@link SimpleDoubleProperty}. */
	private final SimpleDoubleProperty paymentRate = new SimpleDoubleProperty(3.00);

}
