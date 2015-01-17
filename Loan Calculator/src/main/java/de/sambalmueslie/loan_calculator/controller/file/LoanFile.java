/**
 *
 */
package de.sambalmueslie.loan_calculator.controller.file;

import de.sambalmueslie.loan_calculator.model.Model;

/**
 * A file.
 *
 * @author sambalmueslie 2015
 */
public interface LoanFile {
	/**
	 * @return the {@link Model}.
	 */
	Model getModel();

	/**
	 * @return the name.
	 */
	String getName();

	/**
	 * @return <code>true</code> if the file has unsaved changes, otherwise <code>false</code>.
	 */
	boolean hasUnsavedChanges();
}
