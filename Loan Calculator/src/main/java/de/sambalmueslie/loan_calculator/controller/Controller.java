/**
 *
 */
package de.sambalmueslie.loan_calculator.controller;

import javafx.application.Application;
import javafx.stage.Stage;
import de.sambalmueslie.loan_calculator.model.Model;
import de.sambalmueslie.loan_calculator.view.View;

/**
 * The controller.
 *
 * @author sambalmueslie 2015
 */
public class Controller extends Application {

	/**
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(final Stage primaryStage) throws Exception {
		model = new Model();
		view = new View(model);

		view.setup(primaryStage);
	}

	/** the {@link Model}. */
	private Model model;

	/** the {@link View}. */
	private View view;

}
