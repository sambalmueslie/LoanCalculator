/**
 *
 */
package de.sambalmueslie.loan_calculator.view;

import static de.sambalmueslie.loan_calculator.view.Constants.CLASS_PANEL_EMPTY;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import de.sambalmueslie.loan_calculator.model.Model;
import de.sambalmueslie.loan_calculator.view.entry_mgr.tabs.EntryTabPane;
import de.sambalmueslie.loan_calculator.view.entry_mgr.tree.EntryTree;

/**
 * The view.
 *
 * @author sambalmueslie 2015
 */
public class View extends BorderPane {

	/**
	 * Constructor.
	 *
	 * @param model
	 */
	public View(final Model model) {
		entryTree = new EntryTree(model, actionListenerMgr);
		entryTabPane = new EntryTabPane(model, actionListenerMgr);
	}

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
	 * Setup.
	 *
	 * @param primaryStage
	 *            the primary {@link Stage}
	 */
	public void setup(final Stage primaryStage) {
		primaryStage.setTitle("Loan calculator by sambalmueslie!");
		final Label statusbar = new Label();

		final BorderPane content = new BorderPane();
		content.getStyleClass().add(CLASS_PANEL_EMPTY);
		content.setLeft(entryTree);
		content.setCenter(entryTabPane);

		getStyleClass().add(CLASS_PANEL_EMPTY);
		setCenter(content);
		setBottom(statusbar);

		final Scene scene = new Scene(this, 1480, 940);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
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
	 * Teardown.
	 */
	public void teardown() {
		// TODO not implemented yet
	}

	/** the {@link ViewActionListenerMgr}. */
	private final ViewActionListenerMgr actionListenerMgr = new ViewActionListenerMgr();
	/** the {@link EntryTabPane}. */
	private final EntryTabPane entryTabPane;
	/** the {@link EntryTree} */
	private final EntryTree entryTree;

}
