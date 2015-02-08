/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.menu;

import javafx.scene.control.*;
import de.sambalmueslie.loan_calculator.frontend.View;
import de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener;
import de.sambalmueslie.loan_calculator.frontend.i18n.I18n;

/**
 * The main {@link MenuBar}.
 *
 * @author sambalmueslie 2015
 */
public class MainMenu extends MenuBar {

	/**
	 * Constructor.
	 *
	 * @param view
	 *            {@link #view}
	 * @param actionListener
	 *            {@link #actionListener}
	 */
	public MainMenu(final View view, final ViewActionListener actionListener) {
		this.view = view;
		this.actionListener = actionListener;

		getMenus().add(createFileMenu());
		getMenus().add(createSettingsMenu());
	}

	/**
	 * @return {@link #file}.
	 */
	private Menu createFileMenu() {
		file = new Menu(I18n.get(I18n.MENU_FILE));

		final MenuItem fileNew = new MenuItem(I18n.get(I18n.MENU_FILE_NEW));
		fileNew.setOnAction(f -> actionListener.requestFileNew());
		final MenuItem fileOpen = new MenuItem(I18n.get(I18n.MENU_FILE_OPEN));
		fileOpen.setOnAction(f -> actionListener.requestFileOpen());
		final MenuItem fileSave = new MenuItem(I18n.get(I18n.MENU_FILE_SAVE));
		fileSave.setOnAction(f -> actionListener.requestFileSave());
		file.getItems().addAll(fileNew, fileOpen, fileSave);

		file.getItems().add(new SeparatorMenuItem());

		final MenuItem exit = new MenuItem(I18n.get(I18n.MENU_FILE_EXIT));
		exit.setOnAction(f -> actionListener.requestProgrammExit());
		file.getItems().add(exit);

		return file;
	}

	/**
	 * @return {@link #settings}.
	 */
	private Menu createSettingsMenu() {
		settings = new Menu(I18n.get(I18n.MENU_SETTINGS));

		final CheckMenuItem langEnglish = new CheckMenuItem("English");
		final CheckMenuItem langGerman = new CheckMenuItem("Deutsch");
		langEnglish.setSelected(true);
		langGerman.setSelected(false);

		langEnglish.setOnAction(e -> {
			langGerman.setSelected(false);
			I18n.setLocale("en", "US");
			view.refresh();
		});
		langGerman.setOnAction(e -> {
			langEnglish.setSelected(false);
			I18n.setLocale("de", "DE");
			view.refresh();
		});

		settings.getItems().addAll(langEnglish, langGerman);
		return settings;
	}

	/** the {@link ViewActionListener}. */
	private final ViewActionListener actionListener;
	/** the file {@link Menu}. */
	private Menu file;
	/** the settings {@link Menu}. */
	private Menu settings;
	/** the {@link View}. */
	private final View view;
}
