/**
 *
 */
package de.sambalmueslie.loan_calculator.view.component;

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
}
