/**
 *
 */
package de.sambalmueslie.loan_calculator.view.component;

/**
 * A simple text field.
 *
 * @author sambalmueslie 2015
 */
public class SimpleTextField extends BaseTextField<String> {

	/**
	 * @see de.sambalmueslie.loan_calculator.view.component.BaseTextField#getType()
	 */
	@Override
	public TextFieldType getType() {
		return TextFieldType.TEXT;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.component.BaseTextField#format(java.lang.Object)
	 */
	@Override
	protected String format(final String value) {
		return value;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.component.BaseTextField#parse(java.lang.String)
	 */
	@Override
	protected String parse(final String value) {
		return value;
	}

}
