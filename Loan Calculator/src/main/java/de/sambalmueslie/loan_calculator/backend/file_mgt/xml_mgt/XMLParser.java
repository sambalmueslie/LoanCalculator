/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.file_mgt.xml_mgt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.loan_calculator.backend.common.BusinessObject;
import de.sambalmueslie.loan_calculator.backend.compare_mgt.BaseComparison;
import de.sambalmueslie.loan_calculator.backend.compare_mgt.Comparison;
import de.sambalmueslie.loan_calculator.backend.file_mgt.BaseLoanFile;
import de.sambalmueslie.loan_calculator.backend.file_mgt.LoanFile;
import de.sambalmueslie.loan_calculator.backend.file_mgt.xml_mgt.data.*;
import de.sambalmueslie.loan_calculator.backend.founding_mgt.BaseFounding;
import de.sambalmueslie.loan_calculator.backend.founding_mgt.Founding;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoanSettings;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.BaseAnnuityLoan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BaseBuildingLoanAgreement;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreement;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreementSettings;
import de.sambalmueslie.loan_calculator.backend.model.BaseModel;
import de.sambalmueslie.loan_calculator.backend.model.Model;

/**
 * The xml {@link LoanFile} parser.
 *
 * @author sambalmueslie 2015
 */
public class XMLParser {

	/** the {@link Logger}. */
	private static Logger logger = LogManager.getLogger(XMLParser.class);

	/**
	 * Read a {@link LoanFile} from a {@link Path}.
	 *
	 * @param path
	 *            the path
	 * @param model
	 *            the {@link BaseModel}
	 * @return the {@link LoanFile}
	 * @throws IOException
	 *             on file exception
	 * @throws JAXBException
	 *             on xml format exception
	 */
	public LoanFile read(final Path path, final BaseModel model) throws IOException, JAXBException {
		if (logger.isDebugEnabled()) {
			logger.debug("Read loan file from " + path);
		}

		final JAXBContext context = JAXBContext.newInstance(XMLModel.class);
		final Unmarshaller um = context.createUnmarshaller();
		final InputStream fileStream = Files.newInputStream(path, StandardOpenOption.READ);
		final XMLModel xmlModel = (XMLModel) um.unmarshal(fileStream);
		parse(xmlModel, model);
		final BaseLoanFile file = new BaseLoanFile(path, model);
		return file;
	}

	/**
	 * Save a {@link LoanFile}.
	 *
	 * @param file
	 *            the {@link LoanFile} to save
	 * @throws IOException
	 *             on file exception
	 * @throws JAXBException
	 *             on xml format exception
	 */
	public void save(final LoanFile file) throws IOException, JAXBException {
		if (logger.isDebugEnabled()) {
			logger.debug("Save loan file " + file.getPath());
		}
		final Path path = file.getPath();

		final OutputStream fileStream = Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);

		final JAXBContext context = JAXBContext.newInstance(XMLModel.class);

		final Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		final XMLModel xmlModel = parse(file.getModel());
		m.marshal(xmlModel, fileStream);
	}

	/**
	 * Parse a {@link XMLAnnuityLoan} from a {@link AnnuityLoan}.
	 *
	 * @param annuityLoan
	 *            the annuityLoan
	 * @return the {@link XMLAnnuityLoan}
	 */
	private XMLAnnuityLoan parse(final AnnuityLoan annuityLoan) {
		if (logger.isDebugEnabled()) {
			logger.debug("Parse AnnuityLoan [" + annuityLoan.getId() + "] to xml.");
		}
		final XMLAnnuityLoan xmlAnnuityLoan = new XMLAnnuityLoan();
		xmlAnnuityLoan.setId(annuityLoan.getId());
		xmlAnnuityLoan.setName(annuityLoan.getName());
		xmlAnnuityLoan.setAmount(annuityLoan.getAmount());
		xmlAnnuityLoan.setPaymentRate(annuityLoan.getPaymentRate());
		xmlAnnuityLoan.setEstimatedDebitInterest(annuityLoan.getEstimatedDebitInterest());
		xmlAnnuityLoan.setFixedDebitInterest(annuityLoan.getFixedDebitInterest());
		xmlAnnuityLoan.setFixedInterestPeriod(annuityLoan.getFixedInterestPeriod());
		xmlAnnuityLoan.setStartDate(parse(annuityLoan.getStartDate()));
		xmlAnnuityLoan.setUnscheduledRepayment(annuityLoan.getUnscheduledRepayment());
		return xmlAnnuityLoan;
	}

	/**
	 * Parse a {@link XMLBuildingLoanAgreement} from a {@link BuildingLoanAgreement}.
	 *
	 * @param buildingLoanAgreement
	 *            the buildingLoanAgreement
	 * @return the {@link XMLBuildingLoanAgreement}
	 */
	private XMLBuildingLoanAgreement parse(final BuildingLoanAgreement buildingLoanAgreement) {
		if (logger.isDebugEnabled()) {
			logger.debug("Parse BuildingLoanAgreement [" + buildingLoanAgreement.getId() + "] to xml.");
		}
		final XMLBuildingLoanAgreement xmlBuildingLoanAgreement = new XMLBuildingLoanAgreement();
		xmlBuildingLoanAgreement.setId(buildingLoanAgreement.getId());
		xmlBuildingLoanAgreement.setName(buildingLoanAgreement.getName());
		xmlBuildingLoanAgreement.setAmount(buildingLoanAgreement.getAmount());
		xmlBuildingLoanAgreement.setAquisitonFee(buildingLoanAgreement.getAquisitonFee());
		xmlBuildingLoanAgreement.setContribution(buildingLoanAgreement.getContribution());
		xmlBuildingLoanAgreement.setCreditInterest(buildingLoanAgreement.getCreditInterest());
		xmlBuildingLoanAgreement.setDebitInterest(buildingLoanAgreement.getDebitInterest());
		xmlBuildingLoanAgreement.setMinimumSavings(buildingLoanAgreement.getMinimumSavings());
		xmlBuildingLoanAgreement.setRegularSavingAmount(buildingLoanAgreement.getRegularSavingAmount());
		xmlBuildingLoanAgreement.setSavingDuration(buildingLoanAgreement.getSavingDuration());
		xmlBuildingLoanAgreement.setSavingPhaseInterest(buildingLoanAgreement.getSavingPhaseInterest());
		xmlBuildingLoanAgreement.setStartDate(parse(buildingLoanAgreement.getStartDate()));
		return xmlBuildingLoanAgreement;
	}

	/**
	 * Parse a {@link XMLComparison} from a {@link Comparison}.
	 *
	 * @param comparison
	 *            the comparison
	 * @return the {@link XMLComparison}
	 */
	private XMLComparison parse(final Comparison<?> comparison) {
		if (logger.isDebugEnabled()) {
			logger.debug("Parse Comparison [" + comparison.getId() + "] to xml.");
		}
		final XMLComparison xmlComparison = new XMLComparison();
		xmlComparison.setId(comparison.getId());
		xmlComparison.setName(comparison.getName());
		xmlComparison.setType(comparison.getType());
		xmlComparison.setElementIds(comparison.getElements().stream().map(BusinessObject::getId).collect(Collectors.toSet()));
		return xmlComparison;
	}

	/**
	 * Parse a {@link XMLFounding} from a {@link Founding}.
	 *
	 * @param founding
	 *            the founding
	 * @return the {@link XMLFounding}
	 */
	private XMLFounding parse(final Founding founding) {
		if (logger.isDebugEnabled()) {
			logger.debug("Parse Founding [" + founding.getId() + "] to xml.");
		}
		final XMLFounding xmlFounding = new XMLFounding();
		xmlFounding.setId(founding.getId());
		xmlFounding.setName(founding.getName());
		xmlFounding.setBankName(founding.getBankName());
		xmlFounding.setLoanIds(founding.getLoans().stream().map(Loan::getId).collect(Collectors.toSet()));
		return xmlFounding;
	}

	/**
	 * Parse the {@link LocalDate} into a {@link String}.
	 *
	 * @param startDate
	 *            the value
	 * @return the result
	 */
	private String parse(final LocalDate startDate) {
		return startDate.toString();
	}

	/**
	 * Parse a {@link XMLModel} from a {@link Model}.
	 *
	 * @param model
	 *            the model
	 * @return the {@link XMLModel}
	 */
	private XMLModel parse(final Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("Parse model to xml.");
		}
		final XMLModel xmlModel = new XMLModel();

		final List<XMLAnnuityLoan> annuityLoans = model.getAnnuityLoans().stream().map(this::parse).collect(Collectors.toList());
		xmlModel.setAnnuityLoans(annuityLoans);

		final List<XMLBuildingLoanAgreement> buildingLoanAgreements = model.getBuildingLoanAgreements().stream().map(this::parse).collect(Collectors.toList());
		xmlModel.setBuildingLoanAgreements(buildingLoanAgreements);

		final List<XMLFounding> foundings = model.getFoundings().stream().map(this::parse).collect(Collectors.toList());
		xmlModel.setFoundings(foundings);

		final List<XMLComparison> comparisons = model.getComparisons().stream().map(this::parse).collect(Collectors.toList());
		xmlModel.setComparisons(comparisons);
		return xmlModel;
	}

	/**
	 * Parse a {@link LocalDate} from a {@link String}.
	 *
	 * @param value
	 *            the value
	 * @return the {@link LocalDate}
	 */
	private LocalDate parse(final String value) {
		if (value == null) {
			return null;
		}
		return LocalDate.parse(value);
	}

	/**
	 * Parse a {@link AnnuityLoan} from a {@link XMLAnnuityLoan}.
	 *
	 * @param xmlAnnuityLoan
	 *            the xml data
	 * @return the {@link AnnuityLoan}
	 */
	private AnnuityLoan parse(final XMLAnnuityLoan xmlAnnuityLoan) {
		if (logger.isDebugEnabled()) {
			logger.debug("Parse AnnuityLoan [" + xmlAnnuityLoan.getId() + "] from xml.");
		}
		final long id = xmlAnnuityLoan.getId();
		final String name = xmlAnnuityLoan.getName();
		final double amount = xmlAnnuityLoan.getAmount();
		final double paymentRate = xmlAnnuityLoan.getPaymentRate();
		final double fixedDebitInterest = xmlAnnuityLoan.getFixedDebitInterest();
		final int fixedInterestPeriod = xmlAnnuityLoan.getFixedInterestPeriod();
		final double estimatedDebitInterest = xmlAnnuityLoan.getEstimatedDebitInterest();
		final LocalDate startDate = parse(xmlAnnuityLoan.getStartDate());
		final double unscheduledRepayment = xmlAnnuityLoan.getUnscheduledRepayment();
		final AnnuityLoanSettings settings =
				new AnnuityLoanSettings(name, amount, startDate, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest,
						unscheduledRepayment);
		return new BaseAnnuityLoan(id, settings);
	}

	/**
	 * Parse a {@link BuildingLoanAgreement} from a {@link XMLBuildingLoanAgreement}.
	 *
	 * @param xmlBuildingLoanAgreement
	 *            the xml data
	 * @return the {@link BuildingLoanAgreement}
	 */
	private BuildingLoanAgreement parse(final XMLBuildingLoanAgreement xmlBuildingLoanAgreement) {
		if (logger.isDebugEnabled()) {
			logger.debug("Parse BuildingLoanAgreement [" + xmlBuildingLoanAgreement.getId() + "] from xml.");
		}
		final long id = xmlBuildingLoanAgreement.getId();
		final String name = xmlBuildingLoanAgreement.getName();
		final double amount = xmlBuildingLoanAgreement.getAmount();
		final double creditInterest = xmlBuildingLoanAgreement.getCreditInterest();
		final double regularSavingAmount = xmlBuildingLoanAgreement.getRegularSavingAmount();
		final double minimumSavings = xmlBuildingLoanAgreement.getMinimumSavings();
		final int savingDuration = xmlBuildingLoanAgreement.getSavingDuration();
		final double savingPhaseInterest = xmlBuildingLoanAgreement.getSavingPhaseInterest();
		final double debitInterest = xmlBuildingLoanAgreement.getDebitInterest();
		final double contribution = xmlBuildingLoanAgreement.getContribution();
		final double aquisitonFee = xmlBuildingLoanAgreement.getAquisitonFee();
		final LocalDate startDate = parse(xmlBuildingLoanAgreement.getStartDate());
		final BuildingLoanAgreementSettings settings =
				new BuildingLoanAgreementSettings(name, amount, startDate, creditInterest, regularSavingAmount, minimumSavings, savingDuration,
						savingPhaseInterest, debitInterest, contribution, aquisitonFee);
		return new BaseBuildingLoanAgreement(id, settings);
	}

	/**
	 * Parse a {@link Comparison} from a {@link XMLComparison}.
	 *
	 * @param xmlComparison
	 *            the xml data
	 * @param loans
	 *            the already parsed {@link Loan}s
	 * @param foundings
	 *            the already parsed {@link Founding}s
	 * @return the {@link Comparison}
	 */
	@SuppressWarnings("unchecked")
	private Comparison<?> parse(final XMLComparison xmlComparison, final Collection<Loan> loans, final Collection<Founding> foundings) {
		if (logger.isDebugEnabled()) {
			logger.debug("Parse Comparison [" + xmlComparison.getId() + "] from xml.");
		}
		final long id = xmlComparison.getId();
		final String name = xmlComparison.getName();
		final Class<?> type = xmlComparison.getType();
		if (type.equals(Founding.class)) {
			final BaseComparison<Founding> comparison = new BaseComparison<>(id, name, (Class<Founding>) type);
			foundings.stream().filter(f -> xmlComparison.getElementIds().contains(f.getId())).forEach(comparison::add);
			if (xmlComparison.getElementIds().size() != comparison.getElements().size()) {
				logger.warn("Parser ignored some founding ids, cause loans wasn't found.");
			}
			return comparison;
		} else if (type.equals(Loan.class)) {
			final BaseComparison<Loan> comparison = new BaseComparison<>(id, name, (Class<Loan>) type);
			loans.stream().filter(l -> xmlComparison.getElementIds().contains(l.getId())).forEach(comparison::add);
			if (xmlComparison.getElementIds().size() != comparison.getElements().size()) {
				logger.warn("Parser ignored some loan ids, cause loans wasn't found.");
			}
			return comparison;
		}
		return null;
	}

	/**
	 * Parse a {@link Founding} from a {@link XMLFounding}.
	 *
	 * @param xmlFounding
	 *            the xml data
	 * @param loans
	 *            the already parsed {@link Loan}s
	 * @return the {@link Founding}
	 */
	private Founding parse(final XMLFounding xmlFounding, final Collection<Loan> loans) {
		if (logger.isDebugEnabled()) {
			logger.debug("Parse Founding [" + xmlFounding.getId() + "] from xml.");
		}
		final long id = xmlFounding.getId();
		final String name = xmlFounding.getName();
		final String bankName = xmlFounding.getBankName();
		final BaseFounding founding = new BaseFounding(id, name, bankName);
		loans.stream().filter(l -> xmlFounding.getLoanIds().contains(l.getId())).forEach(founding::add);
		if (xmlFounding.getLoanIds().size() != founding.getLoans().size()) {
			logger.warn("Parser ignored some loan ids, cause loans wasn't found.");
		}
		return founding;
	}

	/**
	 * Parse a {@link Model} from a {@link XMLModel}.
	 *
	 * @param xmlModel
	 *            the xml data
	 * @return the {@link Model}
	 */
	private Model parse(final XMLModel xmlModel, final BaseModel model) {
		if (logger.isDebugEnabled()) {
			logger.debug("Parse Model from xml.");
		}
		xmlModel.getAnnuityLoans().stream().map(this::parse).forEach(model::add);
		xmlModel.getBuildingLoanAgreements().stream().map(this::parse).forEach(model::add);
		xmlModel.getFoundings().stream().map(f -> parse(f, model.getLoans())).forEach(model::add);
		xmlModel.getComparisons().stream().map(c -> parse(c, model.getLoans(), model.getFoundings())).forEach(model::add);
		return model;
	}

}
