/**
 *
 */
package de.sambalmueslie.loan_calculator.controller;

import javafx.application.Application;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.loan_calculator.model.BaseModel;
import de.sambalmueslie.loan_calculator.model.founding.BaseFounding;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.loan.AnnuityLoan;
import de.sambalmueslie.loan_calculator.model.loan.BaseAnnuityLoan;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.View;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;

/**
 * The controller.
 *
 * @author sambalmueslie 2015
 */
public class Controller extends Application {

	/** the logger. */
	private static final Logger logger = LogManager.getLogger(Controller.class);

	/**
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(final Stage primaryStage) throws Exception {
		model = new BaseModel();
		view = new View(model);

		view.setup(primaryStage);
		viewActionHandler = new ViewActionHandler(this);
		view.listenerRegister(viewActionHandler);

		setupExampleData();
	}

	/**
	 * @see ViewActionListener#requestAddAnnuityLoan(String, double, double, double, int, double)
	 */
	void handleRequestAddAnnuityLoan(final String name, final double amount, final double paymentRate, final double fixedDebitInterest,
			final int fixedInterestPeriod, final double estimatedDebitInterest) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to add loan " + name + ", " + amount + ", " + paymentRate + ", " + fixedDebitInterest + ", " + fixedInterestPeriod
					+ ", " + estimatedDebitInterest);
		}
		try {
			final AnnuityLoan annuityLoan = new BaseAnnuityLoan(name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest);
			model.add(annuityLoan);
		} catch (final IllegalArgumentException e) {
			logger.error("Cannot add loan " + name + ", " + amount + ", " + paymentRate + ", " + fixedDebitInterest + ", " + fixedInterestPeriod + ", "
					+ estimatedDebitInterest + " cause " + e.getMessage());
			// TODO handle error
		}
	}

	/**
	 * @see ViewActionListener#requestAddFounding(String, String)
	 */
	void handleRequestAddFounding(final String name, final String bankName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to add founding " + name + ", " + bankName);
		}
		try {
			final BaseFounding founding = new BaseFounding(name, bankName);
			model.add(founding);
		} catch (final IllegalArgumentException e) {
			logger.error("Cannot add founding " + name + ", " + bankName + " cause " + e.getMessage());
			// TODO handle error
		}
	}

	/**
	 * @see ViewActionListener#requestFoundingAddLoan(long, long)
	 */
	void handleRequestFoundingAddLoan(final long foundingId, final long loanId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to founding add loan " + foundingId + ", " + loanId);
		}
		final BaseFounding founding = (BaseFounding) model.getFounding(foundingId);
		final Loan loan = model.getLoan(loanId);
		if (founding == null || loan == null) return;
		founding.add(loan);
	}

	/**
	 * @see ViewActionListener#requestFoundingRemoveLoan(long, long)
	 */
	void handleRequestFoundingRemoveLoan(final long foundingId, final long loanId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to founding remove loan " + foundingId + ", " + loanId);
		}
		final BaseFounding founding = (BaseFounding) model.getFounding(foundingId);
		final Loan loan = model.getLoan(loanId);
		if (founding == null || loan == null) return;
		founding.remove(loan);
	}

	/**
	 * @see ViewActionListener#requestRemoveFounding(long)
	 */
	void handleRequestRemoveFounding(final long foundingId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to remove founding " + foundingId);
		}
		final Founding founding = model.getFounding(foundingId);
		if (founding != null) {
			model.remove(founding);
		}
	}

	/**
	 * @see ViewActionListener#requestRemoveLoan(long)
	 */
	void handleRequestRemoveLoan(final long loanId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to remove loan " + loanId);
		}
		final Loan loan = model.getLoan(loanId);
		if (loan != null) {
			model.remove(loan);
		}
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
		try {
			final Loan loan = model.getLoan(loanId);
			if (loan == null || !(loan instanceof BaseAnnuityLoan)) return;
			final BaseAnnuityLoan annuityLoan = (BaseAnnuityLoan) loan;
			annuityLoan.update(name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest);
		} catch (final IllegalArgumentException e) {
			logger.error("Cannot update loan " + name + ", " + amount + ", " + paymentRate + ", " + fixedDebitInterest + ", " + fixedInterestPeriod + ", "
					+ estimatedDebitInterest + " cause " + e.getMessage());
			// TODO handle error
		}
	}

	/**
	 * @see ViewActionListener#requestUpdateFounding(long, String, String)
	 */
	void handleRequestUpdateFounding(final long foundingId, final String name, final String bankName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to update founding " + foundingId + ", " + name + ", " + bankName);
		}
		try {
			final BaseFounding founding = (BaseFounding) model.getFounding(foundingId);
			if (founding == null) return;
			founding.update(name, bankName);
		} catch (final IllegalArgumentException e) {
			logger.error("Cannot update fouding " + name + ", " + bankName + " cause " + e.getMessage());
			// TODO handle error
		}
	}

	/**
	 * Create some example data.
	 */
	private void setupExampleData() {
		handleRequestAddAnnuityLoan("2,5% komplett fest", 100000, 3.0, 2.5, 100, 2.5);
		handleRequestAddAnnuityLoan("2,5% 10 Jahre fest", 100000, 3.0, 2.5, 10, 5.0);
		handleRequestAddAnnuityLoan("2,5% 15 Jahre fest", 100000, 3.0, 2.5, 15, 5.0);

		handleRequestAddAnnuityLoan("5,0% komplett fest", 100000, 3.0, 5.0, 100, 5.0);
		handleRequestAddAnnuityLoan("5,0% 10 Jahre fest", 100000, 3.0, 5.0, 10, 7.5);
		handleRequestAddAnnuityLoan("5,0% 15 Jahre fest", 100000, 3.0, 5.0, 15, 7.5);
	}

	/** the {@link BaseModel}. */
	private BaseModel model;

	/** the {@link View}. */
	private View view;

	/** the {@link ViewActionHandler}. */
	private ViewActionHandler viewActionHandler;

}
