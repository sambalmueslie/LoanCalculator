/**
 *
 */
package de.sambalmueslie.loan_calculator.view.component;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A integer {@link TextField}.
 *
 * @author sambalmueslie 2015
 */
public class IntegerField extends TextField {
	/** the logger. */
	private static final Logger logger = LogManager.getLogger(IntegerField.class);

	/**
	 * Constructor.
	 */
	public IntegerField(final SimpleIntegerProperty property) {
		this.property = property;
		focusedProperty().addListener((ChangeListener<Boolean>) (value, oldVal, newVal) -> {
			if (newVal == false) {
				validateInput();
			}
		});
		showText();
	}

	/**
	 * Show the text.
	 */
	private void showText() {
		String text = String.format(format, property.get());
		logger.info("Show text '" + text + "'");
		if (text.equals(getText())) {
			return;
		}
		setText(text);
	}

	/**
	 * Validate the input.
	 */
	private void validateInput() {
		try {
			String text = getText().replace(".", "").replace(",", ".");
			logger.info("Validate input '" + text + "'");
			property.set(Integer.parseInt(text));
		} catch (NullPointerException | NumberFormatException e) {
			// intentionally left empty
		}
		showText();
	}

	/** the format. */
	private final String format = "%,.2f ";
	/** the {@link SimpleIntegerProperty}. */
	private final SimpleIntegerProperty property;

}
