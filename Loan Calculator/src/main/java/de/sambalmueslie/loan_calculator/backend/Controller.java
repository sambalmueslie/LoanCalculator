/**
 *
 */
package de.sambalmueslie.loan_calculator.backend;

import java.io.IOException;
import java.nio.file.Path;

import javafx.stage.Stage;

import javax.xml.bind.JAXBException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.loan_calculator.backend.compare_mgt.CompareMgr;
import de.sambalmueslie.loan_calculator.backend.compare_mgt.Comparison;
import de.sambalmueslie.loan_calculator.backend.file_mgt.FileController;
import de.sambalmueslie.loan_calculator.backend.file_mgt.LoanFile;
import de.sambalmueslie.loan_calculator.backend.founding_mgt.Founding;
import de.sambalmueslie.loan_calculator.backend.founding_mgt.FoundingMgr;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt.AnnuityLoanMgr;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.building_loan_agreement_mgt.BuildingLoanAgreementMgr;
import de.sambalmueslie.loan_calculator.backend.model.BaseModel;
import de.sambalmueslie.loan_calculator.frontend.View;
import de.sambalmueslie.loan_calculator.frontend.external.ViewActionListener;

/**
 * The controller.
 *
 * @author sambalmueslie 2015
 */
public class Controller {

	/** the logger. */
	private static final Logger logger = LogManager.getLogger(Controller.class);

	/**
	 * Constructor.
	 */
	public Controller() {
		fileController = new FileController();
		annuityLoanMgr = new AnnuityLoanMgr();
		buildingLoanAgreementMgr = new BuildingLoanAgreementMgr();
		foundingMgr = new FoundingMgr(annuityLoanMgr, buildingLoanAgreementMgr);
		compareMgr = new CompareMgr(annuityLoanMgr, buildingLoanAgreementMgr, foundingMgr);
	}

	public void start(final Stage primaryStage) throws Exception {
		view = new View();

		view.setup(primaryStage);
		viewActionHandler = new ViewActionHandler(this);
		view.listenerRegister(viewActionHandler);

		handleRequestFileNew();
		setupExampleData();
	}

	/**
	 * @see ViewActionListener#requestAddAnnuityLoan(String, double, double, double, int, double)
	 */
	Loan handleRequestAddAnnuityLoan(final String name, final double amount, final double paymentRate, final double fixedDebitInterest,
			final int fixedInterestPeriod, final double estimatedDebitInterest) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to add loan " + name + ", " + amount + ", " + paymentRate + ", " + fixedDebitInterest + ", " + fixedInterestPeriod
					+ ", " + estimatedDebitInterest);
		}
		return annuityLoanMgr.add(name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest);
	}

	/**
	 * @see ViewActionListener#requestAddBuildingLoanAgreement(String, double, double, double, double, int, double, double, double)
	 */
	Loan handleRequestAddBuildingLoanAgreement(final String name, final double amount, final double creditInterest, final double regularSavingAmount,
			final double minimumSavings, final int savingDuration, final double savingPhaseInterest, final double debitInterest, final double contribution,
			final double aquisitonFee) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to add building loan agreement " + name + ", " + amount + ", " + creditInterest + ", " + regularSavingAmount + ", "
					+ minimumSavings + ", " + savingDuration + ", " + savingPhaseInterest + ", " + debitInterest + ", " + contribution + ", " + aquisitonFee);
		}
		return buildingLoanAgreementMgr.add(name, amount, creditInterest, regularSavingAmount, minimumSavings, savingDuration, savingPhaseInterest,
				debitInterest, contribution, aquisitonFee);
	}

	/**
	 * @see ViewActionListener#requestAddComparisonFounding(long)
	 */
	Comparison<Founding> handleRequestAddComparisonFounding(final long foundingId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to add comparison founding " + foundingId);
		}

		final Founding founding = foundingMgr.get(foundingId);
		if (founding == null) return null;
		return compareMgr.add(founding);
	}

	/**
	 * @see ViewActionListener#requestAddComparisonLoan(long)
	 */
	Comparison<Loan> handleRequestAddComparisonLoan(final long loanId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to add comparison loan " + loanId);
		}
		final Loan loan = getLoan(loanId);
		if (loan == null) return null;
		return compareMgr.add(loan);
	}

	/**
	 * @see ViewActionListener#requestAddFounding(String, String)
	 */
	Founding handleRequestAddFounding(final String name, final String bankName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to add founding " + name + ", " + bankName);
		}
		return foundingMgr.add(name, bankName);
	}

	/**
	 * @see ViewActionListener#requestComparisonAddFounding(long, long)
	 */
	void handleRequestComparisonAddFounding(final long comparisonId, final long foundingId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to remove comparison founding " + comparisonId + ", " + foundingId);
		}

		final Founding founding = foundingMgr.get(foundingId);
		if (founding == null) return;
		compareMgr.comparisonAdd(comparisonId, founding);
	}

	/**
	 * @see ViewActionListener#requestComparisonAddLoan(long, long)
	 */
	void handleRequestComparisonAddLoan(final long comparisonId, final long loanId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to add comparison loan " + comparisonId + ", " + loanId);
		}
		final Loan loan = getLoan(loanId);
		if (loan == null) return;
		compareMgr.comparisonAdd(comparisonId, loan);

	}

	/**
	 * @see ViewActionListener#requestComparisonRemoveFounding(long, long)
	 */
	void handleRequestComparisonRemoveFounding(final long comparisonId, final long foundingId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to remove comparison founding " + comparisonId + ", " + foundingId);
		}

		final Founding founding = foundingMgr.get(foundingId);
		if (founding == null) return;
		compareMgr.comparisonRemove(comparisonId, founding);

	}

	/**
	 * @see ViewActionListener#requestComparisonRemoveLoan(long, long)
	 */
	void handleRequestComparisonRemoveLoan(final long comparisonId, final long loanId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to remove comparison loan " + comparisonId + ", " + loanId);
		}
		final Loan loan = getLoan(loanId);
		if (loan == null) return;
		compareMgr.comparisonRemove(comparisonId, loan);
	}

	/**
	 * @see ViewActionListener#requestFileNew()
	 */
	void handleRequestFileNew() {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to create new file.");
		}
		if (fileController.hasUnsavedChanges()) {
			if (!view.showDialogRefuseUnsavedChanges()) return;
		}

		annuityLoanMgr = new AnnuityLoanMgr();
		buildingLoanAgreementMgr = new BuildingLoanAgreementMgr();
		foundingMgr = new FoundingMgr(annuityLoanMgr, buildingLoanAgreementMgr);
		compareMgr = new CompareMgr(annuityLoanMgr, buildingLoanAgreementMgr, foundingMgr);
		model = new BaseModel(annuityLoanMgr, buildingLoanAgreementMgr, foundingMgr, compareMgr);
		final LoanFile file = fileController.createNewFile(model);
		view.show(file);
	}

	/**
	 * @see ViewActionListener#requestFileOpen()
	 */
	void handleRequestFileOpen() {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to open a file.");
		}
		if (fileController.hasUnsavedChanges()) {
			if (!view.showDialogRefuseUnsavedChanges()) return;
		}
		final Path path = view.ShowDialogOpenFile();
		try {
			annuityLoanMgr = new AnnuityLoanMgr();
			buildingLoanAgreementMgr = new BuildingLoanAgreementMgr();
			foundingMgr = new FoundingMgr(annuityLoanMgr, buildingLoanAgreementMgr);
			compareMgr = new CompareMgr(annuityLoanMgr, buildingLoanAgreementMgr, foundingMgr);
			model = new BaseModel(annuityLoanMgr, buildingLoanAgreementMgr, foundingMgr, compareMgr);
			final LoanFile file = fileController.open(path, model);
			view.show(file);
		} catch (final IOException e) {
			logger.error("Cannot open file due to IOException " + e.getMessage());
			view.showDialogOpenFileFailed(path, e.getMessage());
		} catch (final JAXBException e) {
			logger.error("Cannot open file due to JAXBException " + e.getMessage());
			view.showDialogOpenFileFailed(path, e.getMessage());
		} catch (final RuntimeException e) {
			logger.error("Cannot open file due to runtime exception " + e.getMessage());
			view.showDialogOpenFileFailed(path, e.getMessage());
		}
	}

	/**
	 * @see ViewActionListener#requestFileSave()
	 */
	void handleRequestFileSave() {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to save current file.");
		}
		final LoanFile file = fileController.getCurrentFile();
		try {
			if (file.getPath() == null) {
				final Path path = view.showDialogSaveNewFile();
				fileController.saveAs(file, path);
			} else {
				fileController.save(file);
			}
			view.showDialogSaveFileSucceed(file);
		} catch (final IOException e) {
			logger.error("Cannot save file due to IOException " + e.getMessage());
			view.showDialogSaveFileFailed(file, e.getMessage());
		} catch (final JAXBException e) {
			logger.error("Cannot save file due to JAXBException " + e.getMessage());
			view.showDialogSaveFileFailed(file, e.getMessage());
		}
	}

	/**
	 * @see ViewActionListener#requestFoundingAddLoan(long, long)
	 */
	void handleRequestFoundingAddLoan(final long foundingId, final long loanId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to founding add loan " + foundingId + ", " + loanId);
		}
		final Loan loan = getLoan(loanId);
		if (loan == null) return;
		foundingMgr.addLoan(foundingId, loan);
	}

	/**
	 * @see ViewActionListener#requestFoundingRemoveLoan(long, long)
	 */
	void handleRequestFoundingRemoveLoan(final long foundingId, final long loanId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to founding remove loan " + foundingId + ", " + loanId);
		}
		final Loan loan = getLoan(loanId);
		if (loan == null) return;
		foundingMgr.removeLoan(foundingId, loan);
	}

	/**
	 * @see ViewActionListener#requestProgrammExit()
	 */
	void handleRequestProgrammExit() {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request exit the programm.");
		}
		if (fileController.hasUnsavedChanges()) {
			if (!view.showDialogRefuseUnsavedChanges()) return;
		}
		System.exit(0);
	}

	/**
	 * @see ViewActionListener#requestRemoveComparison(long)
	 */
	void handleRequestRemoveComparison(final long comparisonId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to remove comparison " + comparisonId);
		}
		compareMgr.remove(comparisonId);
	}

	/**
	 * @see ViewActionListener#requestRemoveFounding(long)
	 */
	void handleRequestRemoveFounding(final long foundingId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to remove founding " + foundingId);
		}
		foundingMgr.remove(foundingId);
	}

	/**
	 * @see ViewActionListener#requestRemoveLoan(long)
	 */
	void handleRequestRemoveLoan(final long loanId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to remove loan " + loanId);
		}
		annuityLoanMgr.remove(loanId);
		buildingLoanAgreementMgr.remove(loanId);
	}

	/**
	 * @see ViewActionListener#requestUpdateAnnuityLoan(long, String, double, double, double, int, double)
	 */
	void handleRequestUpdateAnnuityLoan(final long loanId, final String name, final double amount, final double paymentRate, final double fixedDebitInterest,
			final int fixedInterestPeriod, final double estimatedDebitInterest) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to update loan " + loanId + ", " + name + ", " + amount + ", " + paymentRate + ", " + fixedDebitInterest + ", "
					+ fixedInterestPeriod + ", " + estimatedDebitInterest);
		}
		annuityLoanMgr.update(loanId, name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest);
	}

	/**
	 * @see ViewActionListener#requestUpdateBuildingLoanAgreement(long, String, double, double, double, double, int, double, double, double,
	 *      double)
	 */
	void handleRequestUpdateBuildingLoanAgreement(final long loanId, final String name, final double amount, final double creditInterest,
			final double regularSavingAmount, final double minimumSavings, final int savingDuration, final double savingPhaseInterest,
			final double debitInterest, final double contribution, final double aquisitonFee) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to update building loan agreement " + loanId + ", " + name + ", " + amount + ", " + creditInterest + ", "
					+ regularSavingAmount + ", " + minimumSavings + ", " + savingDuration + ", " + savingPhaseInterest + ", " + debitInterest + ", "
					+ contribution + ", " + aquisitonFee);
		}
		buildingLoanAgreementMgr.update(loanId, name, amount, creditInterest, regularSavingAmount, minimumSavings, savingDuration, savingPhaseInterest,
				debitInterest, contribution, aquisitonFee);
	}

	/**
	 * @see ViewActionListener#requestUpdateFounding(long, String, String)
	 */
	void handleRequestUpdateFounding(final long foundingId, final String name, final String bankName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to update founding " + foundingId + ", " + name + ", " + bankName);
		}
		foundingMgr.update(foundingId, name, bankName);
	}

	private Loan getLoan(final long loanId) {
		final Loan loan = annuityLoanMgr.get(loanId);
		if (loan != null) return loan;
		return buildingLoanAgreementMgr.get(loanId);
	}

	/**
	 * Create some example data.
	 */
	private void setupExampleData() {
		final Loan l1 = handleRequestAddAnnuityLoan("2,5% komplett fest", 100000, 3.0, 2.5, 100, 2.5);
		final Loan l2 = handleRequestAddAnnuityLoan("2,5% 10 Jahre fest", 100000, 3.0, 2.5, 10, 5.0);
		final Loan l3 = handleRequestAddAnnuityLoan("2,5% 15 Jahre fest", 100000, 3.0, 2.5, 15, 5.0);

		handleRequestAddAnnuityLoan("5,0% komplett fest", 100000, 3.0, 5.0, 100, 5.0);
		handleRequestAddAnnuityLoan("5,0% 10 Jahre fest", 100000, 3.0, 5.0, 10, 7.5);
		handleRequestAddAnnuityLoan("5,0% 15 Jahre fest", 100000, 3.0, 5.0, 15, 7.5);

		final Founding f1 = handleRequestAddFounding("Test founding 1", "Testbank");
		final long f1Id = f1.getId();
		handleRequestFoundingAddLoan(f1Id, l1.getId());
		handleRequestFoundingAddLoan(f1Id, l2.getId());
		handleRequestFoundingAddLoan(f1Id, l3.getId());

		final Loan l4 = handleRequestAddAnnuityLoan("3,0% komplett fest", 100000, 3.0, 3.0, 100, 3.0);
		final Loan l5 = handleRequestAddAnnuityLoan("3,0% 10 Jahre fest", 100000, 3.0, 3.0, 10, 5.0);
		final Loan l6 = handleRequestAddAnnuityLoan("3,0% 15 Jahre fest", 100000, 3.0, 3.0, 15, 5.0);

		final Founding f2 = handleRequestAddFounding("Test founding 2", "Testbank");
		final long f2Id = f2.getId();
		handleRequestFoundingAddLoan(f2Id, l4.getId());
		handleRequestFoundingAddLoan(f2Id, l5.getId());
		handleRequestFoundingAddLoan(f2Id, l6.getId());

		final Comparison<Founding> comparison = handleRequestAddComparisonFounding(f1Id);
		final long comparisonId = comparison.getId();
		handleRequestComparisonAddFounding(comparisonId, f2Id);

		handleRequestAddBuildingLoanAgreement("Eigenheim Rente", 100000, 0.25, 5.0, 25.0, 10, 1.5, 1.5, 7.0, 1.0);

	}

	/** the {@link AnnuityLoanMgr}. */
	private AnnuityLoanMgr annuityLoanMgr;

	/** the {@link BuildingLoanAgreementMgr}. */
	private BuildingLoanAgreementMgr buildingLoanAgreementMgr;

	/** the {@link CompareMgr}. */
	private CompareMgr compareMgr;

	/** the {@link FileController}. */
	private final FileController fileController;

	/** the {@link FoundingMgr}. */
	private FoundingMgr foundingMgr;

	/** the {@link BaseModel}. */
	private BaseModel model;

	/** the {@link View}. */
	private View view;

	/** the {@link ViewActionHandler}. */
	private ViewActionHandler viewActionHandler;

}
