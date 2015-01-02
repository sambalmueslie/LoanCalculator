/**
 *
 */
package de.sambalmueslie.loan_calculator;

import javafx.application.Application;
import de.sambalmueslie.loan_calculator.controller.Controller;

/**
 * @author sambalmueslie 2015
 */
public class LoanCalculator {
	/**
	 * Main.
	 *
	 * @param args
	 *            the args
	 */
	public static void main(final String[] args) {
		Application.launch(Controller.class, args);
	}
}
