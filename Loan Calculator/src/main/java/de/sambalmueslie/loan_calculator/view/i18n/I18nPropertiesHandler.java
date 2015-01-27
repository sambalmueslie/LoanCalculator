/**
 *
 */
package de.sambalmueslie.loan_calculator.view.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The properties handler for the i18n.
 *
 * @author sambalmueslie 2015
 */
public class I18nPropertiesHandler {

	/** property key. */
	public static final String FILE_DESCRIPTION = "file_description";
	/** property key. */
	public static final String OPEN_DIALOG_TITLE = "open_dialog_title";
	/** property key. */
	public static final String OPEN_FAILED_DIALOG_MESSAGE = "open_failed_dialog_message";
	/** property key. */
	public static final String OPEN_FAILED_DIALOG_TITLE = "open_failed_dialog_title";
	/** property key. */
	public static final String REFUSE_UNSAVED_CHANGES_DIALOG_CONTENT_TEXT = "refuse_unsaved_changes_dialog_content_text";
	/** property key. */
	public static final String REFUSE_UNSAVED_CHANGES_DIALOG_HEADER_TEXT = "refuse_unsaved_changes_dialog_header_text";
	/** property key. */
	public static final String REFUSE_UNSAVED_CHANGES_DIALOG_TITLE = "refuse_unsaved_changes_dialog_title";
	/** property key. */
	public static final String SAVE_FILE_FAILED_DIALOG_HEADER_TEXT = "save_file_failed_dialog_header_text";
	/** property key. */
	public static final String SAVE_FILE_FAILED_DIALOG_TITLE = "save_file_failed_dialog_title";
	/** property key. */
	public static final String SAVE_FILE_SUCCEED_DIALOG_CONTENT_TEXT = "save_file_succeed_dialog_header_text";
	/** property key. */
	public static final String SAVE_FILE_SUCCEED_DIALOG_TITLE = "save_file_succeed_dialog_title";
	/** property key. */
	public static final String SAVE_NEW_FILE_DIALOG_TITLE = "save_new_file_dialog_title";
	/** property key. */
	public static final String VIEW_TITLE = "view_title";

	/** singleton. */
	private static I18nPropertiesHandler instance;

	/**
	 * @return the {@link I18nPropertiesHandler}.
	 */
	public static I18nPropertiesHandler getInstance() {
		if (instance == null) {
			instance = new I18nPropertiesHandler();
		}
		return instance;
	}

	/**
	 * the {@link String} by key.
	 *
	 * @param key
	 *            the key
	 * @return the string
	 */
	public static String getString(final String key) {
		return getInstance().get(key);
	}

	/**
	 * Constructor.
	 */
	private I18nPropertiesHandler() {
		try {
			currentLocale = new Locale("en", "US");
			messages = ResourceBundle.getBundle("i18n", currentLocale);
		} catch (final RuntimeException e) {
			e.printStackTrace();
		}
		defaultLanguage = new DefaultLanguage();
	}

	/**
	 * the {@link String} by key.
	 *
	 * @param key
	 *            the key
	 * @return the string
	 */
	private String get(final String key) {
		if (messages == null) return defaultLanguage.getString(key);
		final String value = messages.getString(key);
		if (value == null) return "";
		return value;
	}

	/** the current {@link Locale}. */
	private Locale currentLocale;
	/** the {@link DefaultLanguage}. */
	private final DefaultLanguage defaultLanguage;
	/** the {@link ResourceBundle}. */
	private ResourceBundle messages;
}
