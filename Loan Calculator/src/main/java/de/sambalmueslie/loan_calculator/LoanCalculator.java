/**
 *
 */
package de.sambalmueslie.loan_calculator;

import javafx.application.Application;
import javafx.stage.Stage;
import de.sambalmueslie.loan_calculator.backend.Controller;

/**
 * The loan calculator.
 *
 * @author sambalmueslie 2015
 */
public class LoanCalculator extends Application {
	/**
	 * Main.
	 *
	 * @param args
	 *            the args
	 */
	public static void main(final String[] args) {
		Application.launch(args);
	}

	/**
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(final Stage primaryStage) throws Exception {
		final Controller controller = new Controller();
		controller.start(primaryStage);
	}
}
