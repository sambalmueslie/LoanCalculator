/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.component;

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
				if (listener == null) {
					return;
				}
				listener.valueChanged();
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
	public final T getValue() {
		final T result = parse(getText());
		return result;
	}

	public void setChangeListener(final TextFieldChangeListener listener) {
		this.listener = listener;
	}

	/**
	 * Set the value.
	 *
	 * @param value
	 *            the value to set
	 */
	public final void setValue(final T value) {
		this.value = value;
		final String text = format(value);
		if (text.equals(getText())) {
			return;
		}
		setText(text);
	}

	/**
	 * Format the value.
	 *
	 * @param value
	 *            the value
	 * @return the formated value.
	 */
	protected abstract String format(T value);

	/**
	 * Parse the value.
	 *
	 * @param value
	 *            the value
	 * @return the parsed result
	 */
	protected abstract T parse(String value);

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.component.BaseTextField#validate(java.lang.String)
	 */
	protected final void validate(final String input) {
		try {
			final T tmp = getValue();
			setValue(tmp);
		} catch (NullPointerException | NumberFormatException e) {
			setValue(value);
		}
	}

	/** the {@link TextFieldChangeListener}. */
	private TextFieldChangeListener listener;

	/** the value. */
	private T value;
}
