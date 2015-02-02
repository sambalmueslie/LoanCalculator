/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.file_mgt;

import java.nio.file.Path;

import de.sambalmueslie.loan_calculator.backend.model.Model;

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
	 * @return the {@link Path}.
	 */
	Path getPath();

	/**
	 * @return <code>true</code> if the file has unsaved changes, otherwise <code>false</code>.
	 */
	boolean hasUnsavedChanges();
}
