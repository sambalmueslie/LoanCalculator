/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr.contextmenu;

import java.util.Optional;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;
import de.sambalmueslie.loan_calculator.view.dialog.ModifyFoundingDialog;
import de.sambalmueslie.loan_calculator.view.entry_mgr.tree.IconProvider;

/**
 * The {@link ContextMenu} for a {@link Founding}.
 *
 * @author sambalmueslie 2015
 */
public class FoundingContextMenu extends ContextMenu {

	/** the logger. */
	private static final Logger logger = LogManager.getLogger(FoundingContextMenu.class);

	/**
	 * Constructor.
	 */
	public FoundingContextMenu() {
		addMenuItem = new MenuItem("Add");
		updateMenuItem = new MenuItem("Update");
		removeMenuItem = new MenuItem("Remove");
		compareMenuItem = new MenuItem("Compare", IconProvider.createImageView(IconProvider.ICON_LIST_IMAGES));
		getItems().addAll(addMenuItem, updateMenuItem, removeMenuItem, compareMenuItem);
	}

	/**
	 * Set the {@link Founding}.
	 *
	 * @param loan
	 *            the loan
	 */
	public void set(final Founding founding) {
		addMenuItem.setOnAction(e -> add());
		if (founding == null) {
			updateMenuItem.setOnAction(null);
			removeMenuItem.setOnAction(null);
			compareMenuItem.setOnAction(null);
		} else {
			updateMenuItem.setOnAction(e -> update(founding));
			removeMenuItem.setOnAction(e -> remove(founding));
			compareMenuItem.setOnAction(e -> compare(founding));
		}
	}

	/**
	 * @param listener
	 *            the listener to set
	 */
	public void setListener(final ViewActionListener listener) {
		this.listener = listener;
	}

	/**
	 * Add a new {@link Founding}.
	 */
	private void add() {
		if (logger.isDebugEnabled()) {
			logger.debug("Request add new founding");
		}

		final ModifyFoundingDialog dialog = new ModifyFoundingDialog(null);
		final Optional<ButtonType> type = dialog.showAndWait();
		if (type.isPresent() && type.get() == ButtonType.OK) {
			final String name = dialog.getName();
			final String bankName = dialog.getBankName();

			listener.requestAddFounding(name, bankName);
		}
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

	/** the add {@link MenuItem}. */
	private final MenuItem addMenuItem;
	/** the compare {@link MenuItem}. */
	private final MenuItem compareMenuItem;
	/** the {@link ViewActionListener}. */
	private ViewActionListener listener;
	/** the remove {@link MenuItem}. */
	private final MenuItem removeMenuItem;
	/** the update {@link MenuItem}. */
	private final MenuItem updateMenuItem;
}
