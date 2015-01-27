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
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.view.component.SimpleTextField;
import de.sambalmueslie.loan_calculator.view.i18n.I18n;

/**
 * {@link Dialog} to add or modify a {@link Founding}.
 *
 * @author sambalmueslie 2015
 */
public class ModifyFoundingDialog extends Dialog<ButtonType> {

	/**
	 * Constructor.
	 */
	public ModifyFoundingDialog(final Founding founding) {
		final String dialogTitle = (founding == null) ? I18n.get(I18n.FOUNDING_DIALOG_TITLE_NEW) : I18n.get(I18n.FOUNDING_DIALOG_TITLE_UPDATE);
		setTitle(dialogTitle);

		if (founding != null) {
			name.setText(founding.getName());
			bankName.setText(founding.getBankName());
		}

		final DialogPane dialogPane = getDialogPane();

		final GridPane content = new GridPane();
		content.setHgap(10);
		content.setVgap(10);
		content.setMaxWidth(Double.MAX_VALUE);
		content.setAlignment(Pos.CENTER_LEFT);

		content.add(new Label(I18n.get(I18n.TEXT_NAME)), 0, 0);
		content.add(name, 1, 0);

		content.add(new Label(I18n.get(I18n.TEXT_BANK_NAME)), 0, 1);
		content.add(bankName, 1, 1);

		getDialogPane().setContent(content);

		dialogPane.setHeaderText("HEADER TEXT");
		dialogPane.getStyleClass().add("choice-dialog");
		dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		setResultConverter(dialogButton -> dialogButton);
	}

	/**
	 * @return the {@link #bankName}
	 */
	public String getBankName() {
		return bankName.getText();
	}

	/**
	 * @return the {@link #name}
	 */
	public String getName() {
		return name.getText();
	}

	/** the bank name. */
	private final SimpleTextField bankName = new SimpleTextField();
	/** the name. */
	private final SimpleTextField name = new SimpleTextField();
}
