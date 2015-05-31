/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.TextField;
import de.sambalmueslie.loan_calculator.frontend.Constants;

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
	 * Constructor.
	 */
	public CurrencyTextField() {
		setValue(0.0);
	}

	/**
	 * Constructor.
	 *
	 * @param value
	 *            the value
	 */
	public CurrencyTextField(final Double value) {
		setValue(value);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.component.BaseTextField#getType()
	 */
	@Override
	public TextFieldType getType() {
		return TextFieldType.CURRENCY;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.component.BaseTextField#format(java.lang.Object)
	 */
	@Override
	protected String format(final Double value) {
		return String.format(FORMAT, value);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.component.BaseTextField#parse(java.lang.String)
	 */
	@Override
	protected Double parse(final String value) {
		final Matcher m = pattern.matcher(value);
		if (!m.find()) {
			return new Double(-1);
		}
		final String rawValue = m.group(1);
		final String text = rawValue.replace(".", "").replace(",", ".");
		return Double.parseDouble(text);
	}

}
