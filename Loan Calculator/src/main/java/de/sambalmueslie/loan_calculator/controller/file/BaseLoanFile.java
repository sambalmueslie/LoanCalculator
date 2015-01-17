/**
 *
 */
package de.sambalmueslie.loan_calculator.controller.file;

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
	 * @see de.sambalmueslie.loan_calculator.controller.file.LoanFile#hasUnsavedChanges()
	 */
	@Override
	public boolean hasUnsavedChanges() {
		return unsavedChanges;
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
	private final String name;
	/** the unsaved changes flag. */
	private boolean unsavedChanges;

}
