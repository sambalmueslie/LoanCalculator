/**
 *
 */
package de.sambalmueslie.loan_calculator.controller.file;

import java.util.HashMap;
import java.util.Map;

import de.sambalmueslie.loan_calculator.model.Model;

/**
 * The controller for the file operations.
 *
 * @author sambalmueslie 2015
 */
public class FileController {

	/**
	 * Create a new file.
	 *
	 * @param model
	 *            the {@link Model} for the file
	 * @return the created file
	 */
	public LoanFile createNewFile(final Model model) {
		final String name = "Unknown";
		final BaseLoanFile file = new BaseLoanFile(name, model);
		if (!model.isEmpty()) {
			file.setUnsavedChanges();
		}
		files.put(name, file);
		currentFile = file;
		return file;
	}

	/**
	 * @return <code>true</code> if the current {@link LoanFile} has unsaved changes, otherwise <code>false</code>.
	 */
	public boolean hasUnsavedChanges() {
		return currentFile != null && currentFile.hasUnsavedChanges();
	}

	public void saveCurrentFile() {

	}

	/** the current {@link LoanFile}. */
	private LoanFile currentFile;
	/** the currently opened {@link LoanFile}s. */
	private final Map<String, LoanFile> files = new HashMap<>();

}
