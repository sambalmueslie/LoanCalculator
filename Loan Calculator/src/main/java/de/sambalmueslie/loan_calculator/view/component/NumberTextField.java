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
	 * @see de.sambalmueslie.loan_calculator.view.component.BaseTextField#getValue()
	 */
	@Override
	public Integer getValue() {
		return Integer.parseInt(getText());
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.component.BaseTextField#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(final Integer value) {
		final String strVal = String.format(FORMAT, value);
		setText(strVal);
	}

}
