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
	 * @see de.sambalmueslie.loan_calculator.view.component.BaseTextField#getValue()
	 */
	@Override
	public String getValue() {
		return getText();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.component.BaseTextField#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(final String value) {
		setText(value);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.component.BaseTextField#validate(java.lang.String)
	 */
	@Override
	protected void validate(final String input) {
		// intentionally left empty
	}

}
