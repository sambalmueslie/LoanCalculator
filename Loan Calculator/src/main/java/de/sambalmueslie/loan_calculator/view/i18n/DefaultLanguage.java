/**
 *
 */
package de.sambalmueslie.loan_calculator.view.i18n;

import java.util.HashMap;
import java.util.Map;

/**
 * The default language.
 *
 * @author sambalmueslie 2015
 */
public class DefaultLanguage {

	/**
	 * Constructor.
	 */
	public DefaultLanguage() {
		values.put(I18nPropertiesHandler.FILE_DESCRIPTION, "Loan Data files");
		values.put(I18nPropertiesHandler.OPEN_DIALOG_TITLE, "Open file");
		values.put(I18nPropertiesHandler.OPEN_FAILED_DIALOG_MESSAGE, "Open file failed");
		values.put(I18nPropertiesHandler.OPEN_FAILED_DIALOG_TITLE, "Open file failed");
		values.put(I18nPropertiesHandler.REFUSE_UNSAVED_CHANGES_DIALOG_CONTENT_TEXT, "Do you want to continue?");
		values.put(I18nPropertiesHandler.REFUSE_UNSAVED_CHANGES_DIALOG_HEADER_TEXT, "There are unsaved changes.");
		values.put(I18nPropertiesHandler.REFUSE_UNSAVED_CHANGES_DIALOG_TITLE, "Confirmation Dialog");
		values.put(I18nPropertiesHandler.SAVE_FILE_FAILED_DIALOG_HEADER_TEXT, "Save file failed");
		values.put(I18nPropertiesHandler.SAVE_FILE_FAILED_DIALOG_TITLE, "Save file failed");
		values.put(I18nPropertiesHandler.SAVE_FILE_SUCCEED_DIALOG_CONTENT_TEXT, "File successfully saved!");
		values.put(I18nPropertiesHandler.SAVE_FILE_SUCCEED_DIALOG_TITLE, "Information Dialog");
		values.put(I18nPropertiesHandler.SAVE_NEW_FILE_DIALOG_TITLE, "Save file as");
		values.put(I18nPropertiesHandler.VIEW_TITLE, "Loan calculator by sambalmueslie!");
	}

	/**
	 * Get a {@link String} by key.
	 * 
	 * @param key
	 *            the key
	 * @return the string
	 */
	public String getString(final String key) {
		if (values.containsKey(key)) return values.get(key);
		return "";
	}

	/** the values. */
	private final Map<String, String> values = new HashMap<>();
}
