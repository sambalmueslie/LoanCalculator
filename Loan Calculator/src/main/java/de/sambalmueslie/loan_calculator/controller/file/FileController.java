/**
 *
 */
package de.sambalmueslie.loan_calculator.controller.file;

import java.io.IOException;
import java.nio.file.Path;

import javax.xml.bind.JAXBException;

import de.sambalmueslie.loan_calculator.controller.file.xml.XMLParser;
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
		final BaseLoanFile file = new BaseLoanFile(model);
		if (!model.isEmpty()) {
			file.setUnsavedChanges();
		}
		currentFile = file;
		return file;
	}

	/**
	 * @return the {@link #currentFile}
	 */
	public LoanFile getCurrentFile() {
		return currentFile;
	}

	/**
	 * @return <code>true</code> if the current {@link LoanFile} has unsaved changes, otherwise <code>false</code>.
	 */
	public boolean hasUnsavedChanges() {
		return currentFile != null && currentFile.hasUnsavedChanges();
	}

	/**
	 * Open a file.
	 *
	 * @param path
	 *            the Path
	 */
	public LoanFile open(final Path path) throws IOException, JAXBException {
		currentFile = (BaseLoanFile) xmlParser.read(path);
		return currentFile;
	}

	/**
	 * Save the file.
	 *
	 * @param loanFile
	 *            the {@link LoanFile} to save
	 * @throws IOException
	 *             on io error
	 * @throws JAXBException
	 *             on jaxb error
	 */
	public void save(final LoanFile loanFile) throws IOException, JAXBException {
		final BaseLoanFile baseLoanFile = (BaseLoanFile) loanFile;
		xmlParser.save(loanFile);
		baseLoanFile.clearUnsavedChanges();
	}

	/**
	 * Save the file.
	 *
	 * @param loanFile
	 *            the {@link LoanFile} to save
	 * @param path
	 *            the path to save
	 * @throws IOException
	 *             on io error
	 * @throws JAXBException
	 *             on jaxb error
	 */
	public void saveAs(final LoanFile loanFile, final Path path) throws IOException, JAXBException {
		((BaseLoanFile) loanFile).setPath(path);
		save(loanFile);
	}

	/** the current {@link LoanFile}. */
	private BaseLoanFile currentFile;
	/** the {@link XMLOperator}. */
	private final XMLParser xmlParser = new XMLParser();

}
