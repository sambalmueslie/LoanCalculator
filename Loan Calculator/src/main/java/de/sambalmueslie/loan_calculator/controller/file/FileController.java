/**
 *
 */
package de.sambalmueslie.loan_calculator.controller.file;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import de.sambalmueslie.loan_calculator.controller.file.wrapper.XMLAnnuityLoan;
import de.sambalmueslie.loan_calculator.controller.file.wrapper.XMLComparison;
import de.sambalmueslie.loan_calculator.controller.file.wrapper.XMLFounding;
import de.sambalmueslie.loan_calculator.controller.file.wrapper.XMLModel;
import de.sambalmueslie.loan_calculator.model.Model;
import de.sambalmueslie.loan_calculator.model.compare.Comparison;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;
import de.sambalmueslie.loan_calculator.model.loan.BaseAnnuityLoan;
import de.sambalmueslie.loan_calculator.model.loan.Loan;

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

	/**
	 * @return <code>true</code> if the current {@link LoanFile} is already saved, otherwise <code>false</code>.
	 */
	public boolean isCurrendFileAlreadySaved() {
		return currentFile != null && currentFile.getPath() != null;
	}

	/**
	 * Save the current file.
	 */
	public void saveCurrentFile() {
		if (isCurrendFileAlreadySaved()) return;
		// TODO Auto-generated method stub
	}

	/**
	 * Save the current file.
	 *
	 * @param path
	 *            the {@link Path} to save
	 */
	public void saveCurrentNewFile(final Path path) {
		if (isCurrendFileAlreadySaved()) return;

		currentFile.setPath(path);

		try {
			final OutputStream file = Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);

			final JAXBContext context = JAXBContext.newInstance(XMLModel.class);
			final Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			final Model model = currentFile.getModel();
			final XMLModel xmlModel = new XMLModel();

			// add all loans
			final List<XMLAnnuityLoan> annuityLoans = new LinkedList<>();
			for (final Loan loan : model.getAllLoans()) {
				if (loan instanceof BaseAnnuityLoan) {
					final BaseAnnuityLoan annuityLoan = (BaseAnnuityLoan) loan;
					final XMLAnnuityLoan xmlAnnuityLoan = new XMLAnnuityLoan();
					xmlAnnuityLoan.setId(annuityLoan.getId());
					xmlAnnuityLoan.setName(annuityLoan.getName());
					xmlAnnuityLoan.setAmount(annuityLoan.getAmount());
					xmlAnnuityLoan.setEstimatedDebitInterest(annuityLoan.getEstimatedDebitInterest());
					xmlAnnuityLoan.setFixedDebitInterest(annuityLoan.getFixedDebitInterest());
					xmlAnnuityLoan.setFixedInterestPeriod(annuityLoan.getFixedInterestPeriod());
					annuityLoans.add(xmlAnnuityLoan);
				}
			}
			xmlModel.setAnnuityLoans(annuityLoans);

			// add all foundings
			final List<XMLFounding> foundings = new LinkedList<>();
			for (final Founding founding : model.getAllFoundings()) {
				final XMLFounding xmlFounding = new XMLFounding();
				xmlFounding.setId(founding.getId());
				xmlFounding.setBankName(founding.getBankName());
				xmlFounding.setLoanIds(founding.getLoans().stream().map(Loan::getId).collect(Collectors.toSet()));
				foundings.add(xmlFounding);
			}

			xmlModel.setFoundings(foundings);
			// add all comparisons
			final List<XMLComparison> comparisons = new LinkedList<>();
			for (final Comparison<?> comparison : model.getAllComparisons()) {
				final XMLComparison xmlComparison = new XMLComparison();
				xmlComparison.setId(comparison.getId());
				xmlComparison.setName(comparison.getName());
				xmlComparison.setType(comparison.getType());
				xmlComparison.setElementIds(comparison.getElements().stream().map(GenericModelEntry::getId).collect(Collectors.toSet()));
				comparisons.add(xmlComparison);
			}
			xmlModel.setComparisons(comparisons);

			m.marshal(xmlModel, file);

		} catch (final IOException e) {
			e.printStackTrace();
			// TODO Auto-generated method stub
		} catch (final JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/** the current {@link LoanFile}. */
	private BaseLoanFile currentFile;
	/** the currently opened {@link LoanFile}s. */
	private final Map<String, LoanFile> files = new HashMap<>();

}
