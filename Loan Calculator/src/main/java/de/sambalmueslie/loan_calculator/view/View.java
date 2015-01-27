/**
 *
 */
package de.sambalmueslie.loan_calculator.view;

import static de.sambalmueslie.loan_calculator.view.Constants.CLASS_PANEL_EMPTY;

import java.io.File;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import de.sambalmueslie.loan_calculator.controller.file.LoanFile;
import de.sambalmueslie.loan_calculator.view.i18n.I18n;
import de.sambalmueslie.loan_calculator.view.menu.MainMenu;
import de.sambalmueslie.loan_calculator.view.tabs.EntryTabPane;
import de.sambalmueslie.loan_calculator.view.tree.EntryTreePane;

/**
 * The view.
 *
 * @author sambalmueslie 2015
 */
public class View extends BorderPane {

	/** the file extension. */
	private static final String FILE_EXTENSION = "*.ldf";
	/** the stylesheet file name. */
	private static final String STYLESHEET_FILE = "style.css";
	/** the user home property. */
	private static final String USER_HOME = "user.home";

	/**
	 * Register a {@link ViewActionListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	public void listenerRegister(final ViewActionListener listener) {
		actionListenerMgr.register(listener);
	}

	/**
	 * Unregister a {@link ViewActionListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	public void listenerUnregister(final ViewActionListener listener) {
		actionListenerMgr.unregister(listener);
	}

	/**
	 * Refresh the ui.
	 */
	public void refresh() {
		primaryStage.setTitle(I18n.get(I18n.VIEW_TITLE) + file.getName());
		entryTree.show(file);
		entryTabPane.update(file);
	}

	/**
	 * Setup.
	 *
	 * @param primaryStage
	 *            the primary {@link Stage}
	 */
	public void setup(final Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle(I18n.get(I18n.VIEW_TITLE));
		final Label statusbar = new Label();

		content = new BorderPane();
		content.getStyleClass().add(CLASS_PANEL_EMPTY);
		entryTabPane = new EntryTabPane(actionListenerMgr);
		entryTree = new EntryTreePane(entryTabPane, actionListenerMgr);

		content.setLeft(entryTree);
		content.setCenter(entryTabPane);

		final MainMenu mainMenu = new MainMenu(this, actionListenerMgr);

		getStyleClass().add(CLASS_PANEL_EMPTY);
		setTop(mainMenu);
		setCenter(content);
		setBottom(statusbar);

		final Scene scene = new Scene(this, 1480, 960);
		scene.getStylesheets().add(getClass().getResource(STYLESHEET_FILE).toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

		statusbar.setText(primaryStage.getScene().getWidth() + " " + primaryStage.getScene().getHeight());

		primaryStage.widthProperty().addListener((ChangeListener<Number>) (observableValue, oldSceneWidth, newSceneWidth) -> {
			statusbar.setText(primaryStage.getScene().getWidth() + " " + primaryStage.getScene().getHeight());
		});
		primaryStage.heightProperty().addListener((ChangeListener<Number>) (observableValue, oldSceneWidth, newSceneWidth) -> {
			statusbar.setText(primaryStage.getScene().getWidth() + " " + primaryStage.getScene().getHeight());
		});

	}

	/**
	 * Show.
	 *
	 * @param file
	 *            the {@link LoanFile}
	 */
	public void show(final LoanFile file) {
		this.file = file;
		refresh();
	}

	/**
	 * @return
	 */
	public Path ShowDialogOpenFile() {
		final FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(I18n.get(I18n.OPEN_DIALOG_TITLE));
		fileChooser.setInitialDirectory(new File(System.getProperty(USER_HOME)));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(I18n.get(I18n.FILE_DESCRIPTION), FILE_EXTENSION));

		final File file = fileChooser.showOpenDialog(primaryStage);
		return (file != null) ? file.toPath() : null;
	}

	/**
	 * Show the open file failed diaog.
	 *
	 * @param path
	 *            the {@link Path} to open
	 * @param message
	 *            the message
	 */
	public void showDialogOpenFileFailed(final Path path, final String message) {
		final Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(I18n.get(I18n.OPEN_FAILED_DIALOG_TITLE));
		alert.setHeaderText(I18n.get(I18n.OPEN_FAILED_DIALOG_MESSAGE));
		alert.setContentText(message);
		alert.showAndWait();
	}

	/**
	 * Show the dialog to ask the user if it is his will to refuse the unsaved changes.
	 *
	 * @return <code>true</code> if he like to refuse, othewise <code>false</code>
	 */
	public boolean showDialogRefuseUnsavedChanges() {
		final Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(I18n.get(I18n.REFUSE_UNSAVED_CHANGES_DIALOG_TITLE));
		alert.setHeaderText(I18n.get(I18n.REFUSE_UNSAVED_CHANGES_DIALOG_HEADER_TEXT));
		alert.setContentText(I18n.get(I18n.REFUSE_UNSAVED_CHANGES_DIALOG_CONTENT_TEXT));
		alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

		final Optional<ButtonType> result = alert.showAndWait();
		return result.get() == ButtonType.YES;
	}

	/**
	 * Show the save file failed dialog.
	 *
	 * @param file
	 *            the affected {@link LoanFile}
	 * @param message
	 *            the message
	 */
	public void showDialogSaveFileFailed(final LoanFile file, final String message) {
		final Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(I18n.get(I18n.SAVE_FILE_FAILED_DIALOG_TITLE));
		alert.setHeaderText(I18n.get(I18n.SAVE_FILE_FAILED_DIALOG_HEADER_TEXT));
		alert.setContentText(message);
		alert.showAndWait();
	}

	/**
	 * Show the save file succeed dialog.
	 *
	 * @param file
	 *            the {@link LoanFile}
	 */
	public void showDialogSaveFileSucceed(final LoanFile file) {
		final Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(I18n.get(I18n.SAVE_FILE_SUCCEED_DIALOG_TITLE));
		alert.setHeaderText(null);
		alert.setContentText(I18n.get(I18n.SAVE_FILE_SUCCEED_DIALOG_CONTENT_TEXT));

		alert.showAndWait();

		primaryStage.setTitle(I18n.get(I18n.VIEW_TITLE) + file.getName());
	}

	/**
	 * @return the save {@link Path}.
	 */
	public Path showDialogSaveNewFile() {
		final FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(I18n.get(I18n.SAVE_NEW_FILE_DIALOG_TITLE));
		fileChooser.setInitialDirectory(new File(System.getProperty(USER_HOME)));
		fileChooser.setInitialFileName(new SimpleDateFormat("YYYY-MM-dd").format(new Date()) + "_LoanCalcutlation.ldf");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(I18n.get(I18n.FILE_DESCRIPTION), FILE_EXTENSION));

		File file = fileChooser.showSaveDialog(primaryStage);
		if (!file.getName().endsWith(".ldf")) {
			file = new File(file.getPath() + ".ldf");
		}
		return (file != null) ? file.toPath() : null;
	}

	/**
	 * Teardown.
	 */
	public void teardown() {
		// TODO not implemented yet
	}

	/** the {@link ViewActionListenerMgr}. */
	private final ViewActionListenerMgr actionListenerMgr = new ViewActionListenerMgr();

	/** the content pane. */
	private BorderPane content;
	private EntryTabPane entryTabPane;
	private EntryTreePane entryTree;
	/** the {@link LoanFile}. */
	private LoanFile file;

	/** the primary {@link Stage}. */
	private Stage primaryStage;

}
