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
	 * @param name
	 *            {@link #name}
	 * @param model
	 *            {@link #model}
	 */
	BaseLoanFile(final String name, final Model model) {
		this.name = name;
		this.model = model;
		new ModelChangeHandler(model, this);
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
		return name;
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
		name = path.toString();
	}

	/**
	 * Set the unsaved changes.
	 */
	void setUnsavedChanges() {
		unsavedChanges = true;
	}

	/** the {@link Model}. */
	private final Model model;
	/** the name. */
	private String name;

	/** the {@link Path}. */
	private Path path;

	/** the unsaved changes flag. */
	private boolean unsavedChanges;

}
