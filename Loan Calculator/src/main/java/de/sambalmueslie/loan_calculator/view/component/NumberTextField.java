/**
 *
 */
package de.sambalmueslie.loan_calculator.view.component;

/**
 * A number text field.
 *
 * @author sambalmueslie 2015
 */
public class NumberTextField extends BaseTextField<Integer> {

	/** the format string. */
	private static final String FORMAT = "%d";

	/**
	 * Constructor.
	 */
	public NumberTextField() {
		setValue(0);
	}

	/**
	 * Constructor.
	 *
	 * @param value
	 *            the value
	 */
	public NumberTextField(final Integer value) {
		setValue(value);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.component.BaseTextField#getType()
	 */
	@Override
	public TextFieldType getType() {
		return TextFieldType.NUMBER;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.component.BaseTextField#format(java.lang.Object)
	 */
	@Override
	protected String format(final Integer value) {
		return String.format(FORMAT, value);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.component.BaseTextField#parse(java.lang.String)
	 */
	@Override
	protected Integer parse(final String value) {
		return Integer.parseInt(value);
	}

}
