/**
 *
 */
package de.sambalmueslie.loan_calculator.controller;

import javafx.application.Application;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.loan_calculator.model.BaseModel;
import de.sambalmueslie.loan_calculator.model.compare.BaseComparison;
import de.sambalmueslie.loan_calculator.model.compare.Comparison;
import de.sambalmueslie.loan_calculator.model.founding.BaseFounding;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry;
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
	Loan handleRequestAddAnnuityLoan(final String name, final double amount, final double paymentRate, final double fixedDebitInterest,
			final int fixedInterestPeriod, final double estimatedDebitInterest) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to add loan " + name + ", " + amount + ", " + paymentRate + ", " + fixedDebitInterest + ", " + fixedInterestPeriod
					+ ", " + estimatedDebitInterest);
		}
		try {
			final AnnuityLoan annuityLoan = new BaseAnnuityLoan(name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest);
			model.add(annuityLoan);
			return annuityLoan;
		} catch (final IllegalArgumentException e) {
			logger.error("Cannot add loan " + name + ", " + amount + ", " + paymentRate + ", " + fixedDebitInterest + ", " + fixedInterestPeriod + ", "
					+ estimatedDebitInterest + " cause " + e.getMessage());
			// TODO handle error
		}
		return null;
	}

	/**
	 * @see ViewActionListener#requestAddComparisonFounding(long)
	 */
	Comparison<Founding> handleRequestAddComparisonFounding(final long foundingId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to add comparison founding " + foundingId);
		}

		final Founding founding = model.getFounding(foundingId);
		if (founding == null) return null;

		final String name = "Comparison-" + model.getAllComparisons().size();
		final BaseComparison<Founding> comparison = new BaseComparison<>(name, Founding.class);
		comparison.add(founding);
		model.add(comparison);
		return comparison;
	}

	/**
	 * @see ViewActionListener#requestAddComparisonLoan(long)
	 */
	Comparison<Loan> handleRequestAddComparisonLoan(final long loanId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to add comparison loan " + loanId);
		}
		final Loan loan = model.getLoan(loanId);
		if (loan == null) return null;
		final String name = "Comparison-" + model.getAllComparisons().size();
		final BaseComparison<Loan> comparison = new BaseComparison<>(name, Loan.class);
		comparison.add(loan);
		model.add(comparison);
		return comparison;
	}

	/**
	 * @see ViewActionListener#requestAddFounding(String, String)
	 */
	Founding handleRequestAddFounding(final String name, final String bankName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to add founding " + name + ", " + bankName);
		}
		try {
			final BaseFounding founding = new BaseFounding(name, bankName);
			model.add(founding);
			return founding;
		} catch (final IllegalArgumentException e) {
			logger.error("Cannot add founding " + name + ", " + bankName + " cause " + e.getMessage());
			// TODO handle error
		}
		return null;
	}

	/**
	 * @see ViewActionListener#requestComparisonAddFounding(long, long)
	 */
	void handleRequestComparisonAddFounding(final long comparisonId, final long foundingId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to remove comparison founding " + comparisonId + ", " + foundingId);
		}

		final Founding founding = model.getFounding(foundingId);
		if (founding == null) return;

		comparisonAddEntry(comparisonId, founding, Founding.class);
	}

	/**
	 * @see ViewActionListener#requestComparisonAddLoan(long, long)
	 */
	void handleRequestComparisonAddLoan(final long comparisonId, final long loanId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to add comparison loan " + comparisonId + ", " + loanId);
		}
		final Loan loan = model.getLoan(loanId);
		if (loan == null) return;

		comparisonAddEntry(comparisonId, loan, Loan.class);

	}

	/**
	 * @see ViewActionListener#requestComparisonRemoveFounding(long, long)
	 */
	void handleRequestComparisonRemoveFounding(final long comparisonId, final long foundingId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to remove comparison founding " + comparisonId + ", " + foundingId);
		}

		final Founding founding = model.getFounding(foundingId);
		if (founding == null) return;

		comparisonRemoveEntry(comparisonId, founding, Founding.class);

	}

	/**
	 * @see ViewActionListener#requestComparisonRemoveLoan(long, long)
	 */
	void handleRequestComparisonRemoveLoan(final long comparisonId, final long loanId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to remove comparison loan " + comparisonId + ", " + loanId);
		}
		final Loan loan = model.getLoan(loanId);
		if (loan == null) return;

		comparisonRemoveEntry(comparisonId, loan, Loan.class);
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
		// remove from other foundings
		model.getAllFoundings().stream().filter(f -> f.getLoans().contains(loan)).filter(f -> f != founding).forEach(f -> ((BaseFounding) f).remove(loan));
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
	 * @see ViewActionListener#requestRemoveComparison(long)
	 */
	void handleRequestRemoveComparison(final long comparisonId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to remove comparison " + comparisonId);
		}
		final Comparison<?> comparison = model.getComparison(comparisonId);
		if (comparison == null) return;
		model.remove(comparison);
	}

	/**
	 * @see ViewActionListener#requestRemoveFounding(long)
	 */
	void handleRequestRemoveFounding(final long foundingId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to remove founding " + foundingId);
		}
		final Founding founding = model.getFounding(foundingId);
		if (founding == null) return;
		model.remove(founding);
		founding.getLoans().forEach(model::remove);
		comparisonsRemoveEntry(founding);
	}

	/**
	 * @see ViewActionListener#requestRemoveLoan(long)
	 */
	void handleRequestRemoveLoan(final long loanId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Handle request to remove loan " + loanId);
		}
		final Loan loan = model.getLoan(loanId);
		if (loan == null) return;
		model.remove(loan);
		comparisonsRemoveEntry(loan);
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
	 * Handle {@link Comparison} add entry.
	 *
	 * @param comparisonId
	 *            the comparison id
	 * @param entry
	 *            the entry
	 * @param type
	 *            the entry type
	 */
	@SuppressWarnings("unchecked")
	private <T extends GenericModelEntry<T>> void comparisonAddEntry(final long comparisonId, final T entry, final Class<T> type) {
		final Comparison<?> comparison = model.getComparison(comparisonId);
		if (comparison == null || !comparison.getType().equals(type)) return;

		final BaseComparison<T> bc = (BaseComparison<T>) comparison;
		bc.add(entry);
	}

	/**
	 * Handle {@link Comparison} remove entry.
	 *
	 * @param comparisonId
	 *            the comparison id
	 * @param entry
	 *            the entry
	 * @param type
	 *            the entry type
	 */
	@SuppressWarnings("unchecked")
	private <T extends GenericModelEntry<T>> void comparisonRemoveEntry(final long comparisonId, final T entry, final Class<T> type) {
		final Comparison<?> comparison = model.getComparison(comparisonId);
		if (comparison == null || !comparison.getType().equals(type)) return;

		final BaseComparison<T> bc = (BaseComparison<T>) comparison;
		bc.remove(entry);

		if (comparison.getElements().isEmpty()) {
			model.remove(comparison);
		}
	}

	/**
	 * Remove a entry from all {@link Comparison}s.
	 *
	 * @param entry
	 *            the entry
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private <T extends GenericModelEntry<T>> void comparisonsRemoveEntry(final T entry) {
		model.getAllComparisons().forEach(c -> ((BaseComparison) c).remove(entry));
		model.getAllComparisons().stream().filter(c -> c.getElements().isEmpty()).forEach(model::remove);
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

	}

	/** the {@link BaseModel}. */
	private BaseModel model;
	/** the {@link View}. */
	private View view;
	/** the {@link ViewActionHandler}. */
	private ViewActionHandler viewActionHandler;

}
