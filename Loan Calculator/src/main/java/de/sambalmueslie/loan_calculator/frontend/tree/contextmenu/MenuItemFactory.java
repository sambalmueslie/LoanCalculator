/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.tree.contextmenu;

import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import de.sambalmueslie.loan_calculator.backend.compare_mgt.Comparison;
import de.sambalmueslie.loan_calculator.backend.founding_mgt.Founding;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;
import de.sambalmueslie.loan_calculator.frontend.i18n.I18n;
import de.sambalmueslie.loan_calculator.frontend.icons.IconProvider;

/**
 * The {@link MenuItem} factory.
 *
 * @author sambalmueslie 2015
 */
public class MenuItemFactory {

	/**
	 * Create a compare {@link Founding} {@link MenuItem}.
	 *
	 * @param founding
	 *            the loan
	 * @return the {@link MenuItem}
	 */
	public static MenuItem createCompareFoundingItem(final Founding founding) {
		final String text = I18n.get(I18n.CONTEXT_MENU_COMPARE) + " " + founding.getName();
		final ImageView icon = IconProvider.createImageView(IconProvider.ICON_LIST_IMAGES);
		return new MenuItem(text, icon);
	}

	/**
	 * Create a compare {@link Loan} {@link MenuItem}.
	 *
	 * @param loan
	 *            the loan
	 * @return the {@link MenuItem}
	 */
	public static MenuItem createCompareLoanItem(final Loan loan) {
		final String text = I18n.get(I18n.CONTEXT_MENU_COMPARE) + " " + loan.getName();
		final ImageView icon = IconProvider.createImageView(IconProvider.ICON_PAGE_COMPONENT);
		return new MenuItem(text, icon);
	}

	/**
	 * @return a new annuity loan {@link MenuItem}.
	 */
	public static MenuItem createNewAnnuityLoanItem() {
		final ImageView icon = IconProvider.createImageView(IconProvider.ICON_PAGE_NEW);
		final String text = I18n.get(I18n.CONTEXT_MENU_ADD_NEW_ANNUITY_LOAN);
		return new MenuItem(text, icon);
	}

	/**
	 * @return a new building loan agreement {@link MenuItem}.
	 */
	public static MenuItem createNewBuildingLoanAgreementItem() {
		final String text = I18n.get(I18n.CONTEXT_MENU_ADD_NEW_BUILDING_LOAN_AGREEMENT);
		final ImageView icon = IconProvider.createImageView(IconProvider.ICON_PAGE_NEW);
		return new MenuItem(text, icon);
	}

	/**
	 * @return create a new founding {@link MenuItem}.
	 */
	public static MenuItem createNewFoundingItem() {
		final String text = I18n.get(I18n.CONTEXT_MENU_ADD_NEW_FOUNDING);
		final ImageView icon = IconProvider.createImageView(IconProvider.ICON_FOLDER_NEW);
		return new MenuItem(text, icon);
	}

	/**
	 * Create a remove {@link Comparison} {@link MenuItem}.
	 *
	 * @param comparison
	 *            the comparison
	 * @return the {@link MenuItem}
	 */
	public static MenuItem createRemoveComparisonItem(final Comparison<?> comparison) {
		final String text = I18n.get(I18n.CONTEXT_MENU_REMOVE_COMPARISON);
		return new MenuItem(text);
	}

	/**
	 * Create a remove {@link Founding} {@link MenuItem}.
	 *
	 * @param founding
	 *            the founding
	 * @return the {@link MenuItem}
	 */
	public static MenuItem createRemoveFoundingItem(final Founding founding) {
		final String text = I18n.get(I18n.CONTEXT_MENU_REMOVE) + " " + founding.getName();
		return new MenuItem(text);
	}

	/**
	 * Create a remove {@link Loan} {@link MenuItem}.
	 *
	 * @param loan
	 *            the loan
	 * @return the {@link MenuItem}
	 */
	public static MenuItem createRemoveLoanItem(final Loan loan) {
		final String text = I18n.get(I18n.CONTEXT_MENU_REMOVE) + " " + loan.getName();
		final ImageView icon = IconProvider.createImageView(IconProvider.ICON_PAGE_DELETE);
		return new MenuItem(text, icon);
	}

	/**
	 * Create a update {@link Founding} {@link MenuItem}.
	 *
	 * @param founding
	 *            the founding
	 * @return the {@link MenuItem}
	 */
	public static MenuItem createUpdateFoundingItem(final Founding founding) {
		final String text = I18n.get(I18n.CONTEXT_MENU_UPDATE) + " " + founding.getName();
		return new MenuItem(text);
	}

	/**
	 * Create a update {@link Loan} {@link MenuItem}.
	 *
	 * @param loan
	 *            the loan
	 * @return the {@link MenuItem}
	 */
	public static MenuItem createUpdateLoanItem(final Loan loan) {
		final String text = I18n.get(I18n.CONTEXT_MENU_UPDATE) + " " + loan.getName();
		final ImageView icon = IconProvider.createImageView(IconProvider.ICON_PAGE_EDIT);
		return new MenuItem(text, icon);
	}

}
