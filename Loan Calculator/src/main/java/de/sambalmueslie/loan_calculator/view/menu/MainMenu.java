/**
 *
 */
package de.sambalmueslie.loan_calculator.view.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;
import de.sambalmueslie.loan_calculator.view.i18n.I18nPropertiesHandler;

/**
 * The main {@link MenuBar}.
 *
 * @author sambalmueslie 2015
 */
public class MainMenu extends MenuBar {

	/**
	 * Constructor.
	 */
	public MainMenu(final ViewActionListener actionListener) {
		this.actionListener = actionListener;

		getMenus().add(createFileMenu());
	}

	/**
	 * @return {@link #file}.
	 */
	private Menu createFileMenu() {
		file = new Menu(I18nPropertiesHandler.getString(I18nPropertiesHandler.MENU_FILE));

		final MenuItem fileNew = new MenuItem(I18nPropertiesHandler.getString(I18nPropertiesHandler.MENU_FILE_NEW));
		fileNew.setOnAction(f -> actionListener.requestFileNew());
		final MenuItem fileOpen = new MenuItem(I18nPropertiesHandler.getString(I18nPropertiesHandler.MENU_FILE_OPEN));
		fileOpen.setOnAction(f -> actionListener.requestFileOpen());
		final MenuItem fileSave = new MenuItem(I18nPropertiesHandler.getString(I18nPropertiesHandler.MENU_FILE_SAVE));
		fileSave.setOnAction(f -> actionListener.requestFileSave());
		file.getItems().addAll(fileNew, fileOpen, fileSave);

		file.getItems().add(new SeparatorMenuItem());

		final MenuItem exit = new MenuItem(I18nPropertiesHandler.getString(I18nPropertiesHandler.MENU_FILE_EXIT));
		exit.setOnAction(f -> actionListener.requestProgrammExit());
		file.getItems().add(exit);

		return file;
	}

	/** the {@link ViewActionListener}. */
	private final ViewActionListener actionListener;

	private Menu file;
}
