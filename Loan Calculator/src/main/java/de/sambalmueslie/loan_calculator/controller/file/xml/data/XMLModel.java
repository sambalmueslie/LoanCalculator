/**
 *
 */
package de.sambalmueslie.loan_calculator.controller.file.xml.data;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author sambalmueslie 2015
 */

@XmlRootElement(name = "model")
public class XMLModel {

	/**
	 * @return the {@link #annuityLoans}
	 */
	@XmlElementWrapper(name = "annuityloans")
	@XmlElement(name = "annuityloan")
	public List<XMLAnnuityLoan> getAnnuityLoans() {
		return annuityLoans;
	}

	/**
	 * @return the {@link #buildingLoanAgreements}
	 */
	@XmlElementWrapper(name = "buildingLoanAgreements")
	@XmlElement(name = "buildingLoanAgreement")
	public List<XMLBuildingLoanAgreement> getBuildingLoanAgreements() {
		return buildingLoanAgreements;
	}

	/**
	 * @return the {@link #comparisons}
	 */
	@XmlElementWrapper(name = "comparisons")
	@XmlElement(name = "comparison")
	public List<XMLComparison> getComparisons() {
		return comparisons;
	}

	/**
	 * @return the {@link #foundings}
	 */
	@XmlElementWrapper(name = "foundings")
	@XmlElement(name = "founding")
	public List<XMLFounding> getFoundings() {
		return foundings;
	}

	/**
	 * @param annuityLoans
	 *            the annuityLoans to set
	 */
	public void setAnnuityLoans(final List<XMLAnnuityLoan> annuityLoans) {
		this.annuityLoans = annuityLoans;
	}

	/**
	 * @param buildingLoanAgreements
	 *            the buildingLoanAgreements to set
	 */
	public void setBuildingLoanAgreements(final List<XMLBuildingLoanAgreement> buildingLoanAgreements) {
		this.buildingLoanAgreements = buildingLoanAgreements;
	}

	/**
	 * @param comparisons
	 *            the comparisons to set
	 */
	public void setComparisons(final List<XMLComparison> comparisons) {
		this.comparisons = comparisons;
	}

	/**
	 * @param foundings
	 *            the foundings to set
	 */
	public void setFoundings(final List<XMLFounding> foundings) {
		this.foundings = foundings;
	}

	/** the {@link XMLAnnuityLoan}. */
	private List<XMLAnnuityLoan> annuityLoans;

	/** the {@link XMLBuildingLoanAgreement}. */
	private List<XMLBuildingLoanAgreement> buildingLoanAgreements;

	/** the {@link XMLComparison}. */
	private List<XMLComparison> comparisons;

	/** the {@link XMLFounding}. */
	private List<XMLFounding> foundings;
}
