/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.external;

/**
 * The common action listener.
 *
 * @author sambalmueslie 2015
 */
public interface CommonActionListener {
	/**
	 * Create a new file.
	 */
	void requestFileNew();

	/**
	 * Open a file.
	 */
	void requestFileOpen();

	/**
	 * Save a file.
	 */
	void requestFileSave();

	/**
	 * Request to exit the programm.
	 */
	void requestProgrammExit();
}
