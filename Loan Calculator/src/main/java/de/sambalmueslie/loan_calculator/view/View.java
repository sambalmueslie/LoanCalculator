/**
 *
 */
package de.sambalmueslie.loan_calculator.view;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import de.sambalmueslie.loan_calculator.model.Loan;
import de.sambalmueslie.loan_calculator.model.Model;
import de.sambalmueslie.loan_calculator.view.chart.AnnuityCart;
import de.sambalmueslie.loan_calculator.view.chart.ResidualDebtChart;
import de.sambalmueslie.loan_calculator.view.chart.TotalAmountChart;
import de.sambalmueslie.loan_calculator.view.component.LoanManager;
import de.sambalmueslie.loan_calculator.view.component.LoanManagerChangeListener;

/**
 * The view.
 *
 * @author sambalmueslie 2015
 */
public class View extends BorderPane {

	/**
	 * Constructor.
	 *
	 * @param model
	 */
	public View(final Model model) {
		this.model = model;

		modelChangeHandler = new ModelChangeHandler(this);
		residualDebtChart = new ResidualDebtChart();
		totalAmountChart = new TotalAmountChart();
		annuityCart = new AnnuityCart();

		loanManager = new LoanManager();
		loanManagerChangeHandler = new LoanManagerChangeHandler(this);
		loanManager.register(loanManagerChangeHandler);
	}

	/**
	 * Register a {@link ViewActionListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	public void listenerRegister(final ViewActionListener listener) {
		if (listener != null && !listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	/**
	 * Unregister a {@link ViewActionListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	public void listenerUnregister(final ViewActionListener listener) {
		if (listener != null) {
			listeners.remove(listener);
		}
	}

	/**
	 * Setup.
	 *
	 * @param primaryStage
	 *            the primary {@link Stage}
	 */
	public void setup(final Stage primaryStage) {
		primaryStage.setTitle("Loan calculator by sambalmueslie!");

		setLeft(loanManager);
		setCenter(residualDebtChart);

		final HBox box = new HBox(Constants.DEFAULT_SPACING);
		box.getChildren().addAll(totalAmountChart, annuityCart);
		setRight(box);

		primaryStage.setScene(new Scene(this, 1024, 768));
		primaryStage.show();

		model.getAll().forEach(loan -> handleLoanAdded(loan));

		modelChangeHandler.register(model);

	}

	/**
	 * Teardown.
	 */
	public void teardown() {
		modelChangeHandler.unregister(model);
	}

	/**
	 * Handle the addition of a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	void handleLoanAdded(final Loan loan) {
		loanManager.add(loan);
		residualDebtChart.add(loan);
		totalAmountChart.add(loan);
		annuityCart.add(loan);
	}

	/**
	 * Handle the removal of a {@link Loan}.
	 *
	 * @param loan
	 *            the loan
	 */
	void handleLoanRemoved(final Loan loan) {
		loanManager.remove(loan);
		residualDebtChart.remove(loan);
		totalAmountChart.remove(loan);
		annuityCart.remove(loan);
	}

	/**
	 * @see LoanManagerChangeListener#requestAddAnnuityLoan(LoanManager, String, double, double, double, int, double)
	 */
	void notifyRequestAddAnnuityLoan(final LoanManager manager, final String name, final double amount, final double paymentRate,
			final double fixedDebitInterest, final int fixedInterestPeriod, final double estimatedDebitInterest) {
		listeners.forEach(l -> l.requestAddLoan(name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest));
	}

	/**
	 * @see LoanManagerChangeListener#requestRemoveLoan(LoanManager, long)
	 */
	void notifyRequestRemoveLoan(final LoanManager manager, final long loanId) {
		listeners.forEach(l -> l.requestRemoveLoan(loanId));
	}

	/**
	 * @see LoanManagerChangeListener#requestUpdateAnnuityLoan(LoanManager, long, String, double, double, double, int, double)
	 */
	void notifyRequestUpdateAnnuityLoan(final LoanManager manager, final long loanId, final String name, final double amount, final double paymentRate,
			final double fixedDebitInterest, final int fixedInterestPeriod, final double estimatedDebitInterest) {
		listeners.forEach(l -> l.requestUpdateLoan(loanId, name, amount, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest));
	}

	/** the {@link AnnuityCart}. */
	private final AnnuityCart annuityCart;
	/** the {@link ViewActionListener}. */
	private final List<ViewActionListener> listeners = new LinkedList<>();
	/** the {@link LoanManager}. */
	private final LoanManager loanManager;
	/** the {@link LoanManagerChangeListener}. */
	private final LoanManagerChangeHandler loanManagerChangeHandler;
	/** the {@link Model}. */
	private final Model model;
	/** the {@link ModelChangeHandler}. */
	private final ModelChangeHandler modelChangeHandler;
	/** the {@link ResidualDebtChart}. */
	private final ResidualDebtChart residualDebtChart;
	/** the {@link TotalAmountChart}. */
	private final TotalAmountChart totalAmountChart;

}
