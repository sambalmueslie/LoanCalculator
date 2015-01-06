/**
 *
 */
package de.sambalmueslie.loan_calculator.view.component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.TextField;
import de.sambalmueslie.loan_calculator.view.Constants;

/**
 * A currency {@link TextField}.
 *
 * @author sambalmueslie 2015
 */
public class CurrencyTextField extends BaseTextField<Double> {

	/** the format string. */
	private static final String FORMAT = "%,.2f " + Constants.DEFAULT_CURRENCY;

	/** the {@link Pattern}. */
	private static Pattern pattern;

	/** the regex. */
	private static final String REGEX = "([\\d\\.,]*).*";

	static {
		pattern = Pattern.compile(REGEX);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.component.BaseTextField#getType()
	 */
	@Override
	public TextFieldType getType() {
		return TextFieldType.CURRENCY;
	}

	@Override
	public Double getValue() {
		final Matcher m = pattern.matcher(getText());
		if (!m.find()) return new Double(-1);
		final String rawValue = m.group(1);
		final String text = rawValue.replace(".", "").replace(",", ".");
		return Double.parseDouble(text);
	}

	@Override
	public void setValue(final Double value) {
		final String text = String.format(FORMAT, value);
		setText(text);
	}
}
