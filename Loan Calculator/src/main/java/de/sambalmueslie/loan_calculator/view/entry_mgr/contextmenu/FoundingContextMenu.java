/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr.contextmenu;

import java.util.Optional;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

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
public class FoundingContextMenu extends EntryTreeContextMenu {

	/** the logger. */
	private static final Logger logger = LogManager.getLogger(FoundingContextMenu.class);

	/**
	 * Constructor.
	 */
	public FoundingContextMenu() {
		super();
		updateMenuItem = new MenuItem("Update");
		removeMenuItem = new MenuItem("Remove");
		compareMenuItem = new MenuItem("Compare", IconProvider.createImageView(IconProvider.ICON_LIST_IMAGES));
		getItems().addAll(new SeparatorMenuItem(), updateMenuItem, removeMenuItem, compareMenuItem);
	}

	/**
	 * Set the {@link Founding}.
	 *
	 * @param loan
	 *            the loan
	 */
	public void set(final Founding founding) {
		if (founding == null) {
			updateMenuItem.setOnAction(null);
			updateMenuItem.setText("Update");
			removeMenuItem.setOnAction(null);
			updateMenuItem.setText("Remove");
			compareMenuItem.setOnAction(null);
			updateMenuItem.setText("Compare");
		} else {
			updateMenuItem.setOnAction(e -> update(founding));
			updateMenuItem.setText("Update " + founding.getName());
			removeMenuItem.setOnAction(e -> remove(founding));
			removeMenuItem.setText("Remove " + founding.getName());
			compareMenuItem.setOnAction(e -> compare(founding));
			compareMenuItem.setText("Compare " + founding.getName());
		}
	}

	/**
	 * @param listener
	 *            the listener to set
	 */
	@Override
	public void setListener(final ViewActionListener listener) {
		super.setListener(listener);
		this.listener = listener;
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
	private ViewActionListener listener;
	/** the remove {@link MenuItem}. */
	private final MenuItem removeMenuItem;
	/** the update {@link MenuItem}. */
	private final MenuItem updateMenuItem;
}
