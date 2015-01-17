/**
 *
 */
package de.sambalmueslie.loan_calculator.view.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;

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
		file = new Menu("File");

		final MenuItem fileNew = new MenuItem("New");
		fileNew.setOnAction(f -> actionListener.requestFileNew());
		final MenuItem fileOpen = new MenuItem("Open");
		fileOpen.setOnAction(f -> actionListener.requestFileOpen());
		final MenuItem fileSave = new MenuItem("Save");
		fileSave.setOnAction(f -> actionListener.requestFileSave());
		file.getItems().addAll(fileNew, fileOpen, fileSave);

		file.getItems().add(new SeparatorMenuItem());

		final MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(f -> actionListener.requestProgrammExit());
		file.getItems().add(exit);

		return file;
	}

	/** the {@link ViewActionListener}. */
	private final ViewActionListener actionListener;

	private Menu file;
}
