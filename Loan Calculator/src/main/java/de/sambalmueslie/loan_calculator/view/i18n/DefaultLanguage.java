/**
 *
 */
package de.sambalmueslie.loan_calculator.view.i18n;

import static de.sambalmueslie.loan_calculator.view.i18n.I18n.*;

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
		values.put(FILE_DESCRIPTION, "Loan Data files");
		values.put(OPEN_DIALOG_TITLE, "Open file");
		values.put(OPEN_FAILED_DIALOG_MESSAGE, "Open file failed");
		values.put(OPEN_FAILED_DIALOG_TITLE, "Open file failed");
		values.put(REFUSE_UNSAVED_CHANGES_DIALOG_CONTENT_TEXT, "Do you want to continue?");
		values.put(REFUSE_UNSAVED_CHANGES_DIALOG_HEADER_TEXT, "There are unsaved changes.");
		values.put(REFUSE_UNSAVED_CHANGES_DIALOG_TITLE, "Confirmation Dialog");
		values.put(SAVE_FILE_FAILED_DIALOG_HEADER_TEXT, "Save file failed");
		values.put(SAVE_FILE_FAILED_DIALOG_TITLE, "Save file failed");
		values.put(SAVE_FILE_SUCCEED_DIALOG_CONTENT_TEXT, "File successfully saved!");
		values.put(SAVE_FILE_SUCCEED_DIALOG_TITLE, "Information Dialog");
		values.put(SAVE_NEW_FILE_DIALOG_TITLE, "Save file as");
		values.put(VIEW_TITLE, "Loan calculator by sambalmueslie!");
		values.put(MENU_FILE, "File");
		values.put(MENU_FILE_NEW, "New");
		values.put(MENU_FILE_OPEN, "Open");
		values.put(MENU_FILE_SAVE, "Save");
		values.put(MENU_FILE_EXIT, "Exit");
		values.put(REDEMPTION_PLAN_CHART_TITLE, "Redemption Plan");
		values.put(ANNUITY_PLAN_CHART_TITLE, "Annuity Plan");
		values.put(TEXT_INTEREST, "interest");
		values.put(TEXT_REDEMPTION, "redemption");
		values.put(TEXT_RISK_CAPITAL, "Risk capital");
		values.put(TEXT_TOTAL_AMOUNT, "Total amount");
		values.put(TEXT_TOTAL_INTEREST, "Total interest");
		values.put(TEXT_TOTAL_PAYMENT, "Total payment");
		values.put(TEXT_TERM, "Term");
		values.put(TEXT_RESIDUAL_DEBT, "Residual debt");
		values.put(TEXT_NAME, "Name");
		values.put(TEXT_AMOUNT, "Amount");
		values.put(TEXT_PAYMENT_RATE, "Payment rate");
		values.put(TEXT_FIXED_DEBIT_INTEREST, "Fixed debit interest");
		values.put(TEXT_FIXED_INTEREST_PERIOD, "Fixed interest period");
		values.put(TEXT_ESTIMATED_DEBIT_INTEREST, "Estimated debit interest");
		values.put(CONTEXT_MENU_REMOVE_COMPARISON, "Remove comparison");
		values.put(CONTEXT_MENU_REMOVE_ELEMENT, "Remove element");
		values.put(ANNUITY_DIALOG_TITLE_NEW, "Add new loan.");
		values.put(ANNUITY_DIALOG_TITLE_UPDATE, "Update loan.");
		values.put(TEXT_AQUISITION_FEE, "Aquisition fee");
		values.put(TEXT_REGULAR_SAVING_AMOUNT, "Regular saving amount");
		values.put(TEXT_MINIMUM_SAVINGS, "Minimum savings");
		values.put(TEXT_SAVING_DURATION, "Saving duration");
		values.put(TEXT_DEBIT_INTEREST, "Debit interest");
		values.put(TEXT_SAVING_PHASE_INTEREST, "Saving phase interest");
		values.put(TEXT_CONTRIBUTION, "Contribution");
		values.put(TEXT_BANK_NAME, "Bank name");
		values.put(TEXT_ANNUITY_INTEREST, "Annuity interest");
		values.put(TEXT_ANNUITY_REDEMPTION, "Annuity redemption");
		values.put(TEXT_ANNUITY_TOTAL, "Annuity total");
		values.put(TEXT_SAVINGS, "Savings");
		values.put(BUILDING_LOAN_AGREEMENT_DIALOG_TITLE_NEW, "Add new building loan agreement.");
		values.put(BUILDING_LOAN_AGREEMENT_DIALOG_TITLE_UPDATE, "Update building loan agreement.");
		values.put(FOUNDING_DIALOG_TITLE_NEW, "Add new founding.");
		values.put(FOUNDING_DIALOG_TITLE_UPDATE, "Update founding.");
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
