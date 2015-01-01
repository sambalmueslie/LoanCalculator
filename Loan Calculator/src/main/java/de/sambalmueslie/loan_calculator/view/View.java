/**
 *
 */
package de.sambalmueslie.loan_calculator.view;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import de.sambalmueslie.loan_calculator.model.Model;

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
		this.model = model;
	}

	/**
	 * Register a {@link ViewActionListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	public void listenerRegister(final ViewActionListener listener) {
		if (listener != null && !listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	/**
	 * Unregister a {@link ViewActionListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	public void listenerUnregister(final ViewActionListener listener) {
		if (listener != null) {
			listeners.remove(listener);
		}
	}

	/**
	 * Setup.
	 *
	 * @param primaryStage
	 *            the primary {@link Stage}
	 */
	public void setup(final Stage primaryStage) {
		primaryStage.setTitle("Loan calculator by sambalmueslie!");

		primaryStage.setScene(new Scene(this, 1024, 768));
		primaryStage.show();

	}

	/** the {@link ViewActionListener}. */
	private final List<ViewActionListener> listeners = new LinkedList<>();
	/** the {@link Model}. */
	private final Model model;

}
