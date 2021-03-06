/**
 *
 */
package de.sambalmueslie.loan_calculator.controller.file;

import java.nio.file.Path;

import de.sambalmueslie.loan_calculator.model.Model;

/**
 * A base {@link LoanFile}.
 *
 * @author sambalmueslie 2015
 */
public class BaseLoanFile implements LoanFile {

	/**
	 * Constructor.
	 *
	 * @param path
	 *            {@link #path}
	 * @param model
	 *            {@link #model}
	 */
	public BaseLoanFile(final Path path, final Model model) {
		this.path = path;
		this.model = model;
		new ModelChangeHandler(model, this);
	}

	/**
	 * Constructor.
	 *
	 * @param model
	 *            {@link #model}
	 */
	BaseLoanFile(final Model model) {
		this(null, model);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.controller.file.LoanFile#getModel()
	 */
	@Override
	public Model getModel() {
		return model;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.controller.file.LoanFile#getName()
	 */
	@Override
	public String getName() {
		return (path == null) ? "New file" : path.toString();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.controller.file.LoanFile#getPath()
	 */
	@Override
	public Path getPath() {
		return path;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.controller.file.LoanFile#hasUnsavedChanges()
	 */
	@Override
	public boolean hasUnsavedChanges() {
		return unsavedChanges;
	}

	/**
	 * Clear the unsaved changes.
	 */
	void clearUnsavedChanges() {
		unsavedChanges = false;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	void setPath(final Path path) {
		this.path = path;
	}

	/**
	 * Set the unsaved changes.
	 */
	void setUnsavedChanges() {
		unsavedChanges = true;
	}

	/** the {@link Model}. */
	private final Model model;
	/** the {@link Path}. */
	private Path path;
	/** the unsaved changes flag. */
	private boolean unsavedChanges;

}
