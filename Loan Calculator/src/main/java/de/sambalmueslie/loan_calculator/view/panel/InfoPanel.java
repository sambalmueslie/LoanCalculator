/**
 *
 */
package de.sambalmueslie.loan_calculator.view.panel;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import de.sambalmueslie.loan_calculator.view.Constants;
import de.sambalmueslie.loan_calculator.view.component.*;

/**
 * The info panel.
 *
 * @author sambalmueslie 2015
 */
class InfoPanel extends TilePane {

	/**
	 * Constructor.
	 */
	InfoPanel() {
		getStyleClass().add(Constants.CLASS_PANEL_BORDER);
		setPrefColumns(1);
	}

	/**
	 * Add a new info field.
	 *
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @param type
	 *            the {@link TextFieldType}
	 */
	@SuppressWarnings("unchecked")
	<T> void add(final String name, final T value, final TextFieldType type) {
		final Label label = new Label(name);
		final BaseTextField<T> textField = (BaseTextField<T>) createTextField(type);
		textField.setValue(value);
		textField.setEditable(false);
		textFields.put(name, textField);
		getChildren().addAll(label, textField);
	}

	/**
	 * Update the value.
	 *
	 * @param name
	 *            the name
	 * @param value
	 *            the values
	 */
	@SuppressWarnings("unchecked")
	<T> void update(final String name, final T value) {
		final BaseTextField<T> textField = (BaseTextField<T>) textFields.get(name);
		if (textField == null) return;
		textField.setValue(value);
	}

	/**
	 * Create a {@link TextField} for {@link TextFieldType}.
	 *
	 * @param type
	 *            the type
	 * @return the {@link TextField}
	 */
	private BaseTextField<?> createTextField(final TextFieldType type) {
		switch (type) {
		default:
		case TEXT:
			return new SimpleTextField();
		case CURRENCY:
			return new CurrencyTextField();
		case PERCENTAGE:
			return new PercentageTextField();
		case NUMBER:
			return new NumberTextField();
		}
	}

	/** the current {@link BaseTextField}s. */
	private final Map<String, BaseTextField<?>> textFields = new HashMap<>();

}
