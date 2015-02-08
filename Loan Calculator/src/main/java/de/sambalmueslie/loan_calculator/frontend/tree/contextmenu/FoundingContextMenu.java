/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.tree.contextmenu;

import java.util.Optional;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.loan_calculator.backend.founding_mgt.Founding;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;
import de.sambalmueslie.loan_calculator.frontend.dialog.ModifyFoundingDialog;
import de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener;
import de.sambalmueslie.loan_calculator.frontend.i18n.I18n;
import de.sambalmueslie.loan_calculator.frontend.icons.IconProvider;

/**
 * The context menu for a loan list cell loan.
 *
 * @author sambalmueslie 2015
 */
public class FoundingContextMenu extends BaseContextMenu {
	/** the logger. */
	private static final Logger logger = LogManager.getLogger(FoundingContextMenu.class);

	/**
	 * Constructor.
	 *
	 * @param founding
	 *            the {@link Founding}
	 * @param listener
	 *            {@link #listener}
	 */
	public FoundingContextMenu(final Founding founding, final ViewActionListener listener) {
		super(listener);
		this.listener = listener;
		updateMenuItem = new MenuItem(I18n.get(I18n.CONTEXT_MENU_UPDATE) + " " + founding.getName());
		updateMenuItem.setOnAction(e -> update(founding));
		removeMenuItem = new MenuItem(I18n.get(I18n.CONTEXT_MENU_REMOVE) + " " + founding.getName());
		removeMenuItem.setOnAction(e -> remove(founding));
		compareMenuItem = new MenuItem(I18n.get(I18n.CONTEXT_MENU_COMPARE) + " " + founding.getName(),
				IconProvider.createImageView(IconProvider.ICON_LIST_IMAGES));
		compareMenuItem.setOnAction(e -> compare(founding));
		getItems().addAll(new SeparatorMenuItem(), updateMenuItem, removeMenuItem, compareMenuItem, new SeparatorMenuItem());

		final long foundingId = founding.getId();
		final Menu remove = new Menu(I18n.get(I18n.CONTEXT_MENU_REMOVE_ELEMENT));
		for (final Loan loan : founding.getLoans()) {
			final MenuItem menuItem = new MenuItem(loan.getName());
			final long loanId = loan.getId();
			menuItem.setOnAction(event -> listener.requestFoundingRemoveLoan(foundingId, loanId));
			remove.getItems().add(menuItem);
		}
		getItems().add(remove);
	}

	/**
	 * Request to compare the {@link Founding}.
	 *
	 * @param founding
	 *            the founding
	 */
	private void compare(final Founding founding) {
		if (logger.isDebugEnabled()) {
			logger.debug("Request compare " + founding);
		}
		final long foundingId = founding.getId();
		listener.requestAddComparisonFounding(foundingId);
	}

	/**
	 * Remove a existing {@link Founding}.
	 *
	 * @param founding
	 *            the {@link Founding}
	 */
	private void remove(final Founding founding) {
		if (logger.isDebugEnabled()) {
			logger.debug("Request remove " + founding);
		}
		final long foundingId = founding.getId();
		listener.requestRemoveFounding(foundingId);
	}

	/**
	 * Update a existing {@link Founding}.
	 *
	 * @param founding
	 *            the {@link Founding}
	 */
	private void update(final Founding founding) {
		if (logger.isDebugEnabled()) {
			logger.debug("Request update founding " + founding);
		}

		final ModifyFoundingDialog dialog = new ModifyFoundingDialog(founding);
		final Optional<ButtonType> type = dialog.showAndWait();
		if (type.isPresent() && type.get() == ButtonType.OK) {
			final long foundingId = founding.getId();
			final String name = dialog.getName();
			final String bankName = dialog.getBankName();

			listener.requestUpdateFounding(foundingId, name, bankName);
		}
	}

	/** the compare {@link MenuItem}. */
	private final MenuItem compareMenuItem;
	/** the {@link ViewActionListener}. */
	private final ViewActionListener listener;
	/** the remove {@link MenuItem}. */
	private final MenuItem removeMenuItem;
	/** the update {@link MenuItem}. */
	private final MenuItem updateMenuItem;
}
