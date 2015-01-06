/**
 *
 */
package de.sambalmueslie.loan_calculator.view.component;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

/**
 * A base {@link TextField}.
 *
 * @author sambalmueslie 2015
 */
public abstract class BaseTextField<T> extends TextField {

	/**
	 * Constructor.
	 */
	public BaseTextField() {
		setAlignment(Pos.CENTER);
		focusedProperty().addListener((ChangeListener<Boolean>) (value, oldVal, newVal) -> {
			if (newVal == false) {
				validate(getText());
			}
		});
	}

	/**
	 * @return the {@link TextFieldType}.
	 */
	public abstract TextFieldType getType();

	/**
	 * @return the value.
	 */
	public abstract T getValue();

	/**
	 * Set the value.
	 *
	 * @param value
	 *            the value to set
	 */
	public abstract void setValue(final T value);

	/**
	 * @see de.sambalmueslie.loan_calculator.view.component.BaseTextField#validate(java.lang.String)
	 */
	protected void validate(final String input) {
		try {
			final T value = getValue();
			setValue(value);
		} catch (NullPointerException | NumberFormatException e) {
			// intentionally left empty
		}
	}

}
