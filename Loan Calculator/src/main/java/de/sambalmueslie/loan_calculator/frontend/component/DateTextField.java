/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The text field for a timestamp.
 *
 * @author sambalmueslie 2015
 */
public class DateTextField extends BaseTextField<LocalDate> {

	/** time format. */
	private static final String TIME_FORMAT = "dd.MM.yyyy";

	/**
	 * Constructor.
	 */
	public DateTextField() {
		formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.component.BaseTextField#getType()
	 */
	@Override
	public TextFieldType getType() {
		return TextFieldType.DATE;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.component.BaseTextField#format(java.lang.Object)
	 */
	@Override
	protected String format(final LocalDate value) {
		return (value == null) ? "" : value.format(formatter);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.frontend.component.BaseTextField#parse(java.lang.String)
	 */
	@Override
	protected LocalDate parse(final String value) {
		try {
			return LocalDate.parse(value, formatter);
		} catch (final DateTimeParseException e) {
			e.printStackTrace();
		}
		return LocalDate.now();
	}

	/** the {@link DateTimeFormatter}. */
	private final DateTimeFormatter formatter;
}
