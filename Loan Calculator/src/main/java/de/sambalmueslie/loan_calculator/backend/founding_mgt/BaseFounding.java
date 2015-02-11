/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.founding_mgt;

import java.time.LocalDate;
import java.util.*;

import de.sambalmueslie.loan_calculator.backend.common.BaseBusinessObject;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.BaseRedemptionPlanEntry;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.RedemptionPlanEntry;

/**
 * A base {@link Founding}.
 *
 * @author sambalmueslie 2015
 */
public class BaseFounding extends BaseBusinessObject implements Founding {

	/**
	 * Constructor.
	 *
	 * @param id
	 *            {@link #id}
	 * @param name
	 *            {@link #name}
	 * @param bankName
	 *            {@link #bankName}
	 */
	public BaseFounding(final long id, final String name, final String bankName) {
		super(id, name);
		update(name, bankName);
	}

	/**
	 * Add a {@link Loan} to the {@link Founding}.
	 *
	 * @param loan
	 *            the loan
	 */
	public void add(final Loan loan) {
		if (loan == null || loans.containsKey(loan.getId())) return;
		loans.put(loan.getId(), loan);
		loan.register(l -> updated((Loan) l));
		update();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.founding.Founding#getAmount()
	 */
	@Override
	public double getAmount() {
		return amount;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.founding.Founding#getBankName()
	 */
	@Override
	public String getBankName() {
		return bankName;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.founding_mgt.Founding#getEndDate()
	 */
	@Override
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.founding.Founding#getLoans()
	 */
	@Override
	public List<Loan> getLoans() {
		return Collections.unmodifiableList(new LinkedList<Loan>(loans.values()));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.founding.Founding#getRedemptionPlan()
	 */
	@Override
	public List<RedemptionPlanEntry> getRedemptionPlan() {
		return Collections.unmodifiableList(redemptionPlan);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.founding.Founding#getRiskCapital()
	 */
	@Override
	public double getRiskCapital() {
		return riskCapital;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.backend.founding_mgt.Founding#getStartDate()
	 */
	@Override
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.founding.Founding#getTerm()
	 */
	@Override
	public int getTerm() {
		return term;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.founding.Founding#getTotalInterest()
	 */
	@Override
	public double getTotalInterest() {
		return totalInterest;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.founding.Founding#getTotalPayment()
	 */
	@Override
	public double getTotalPayment() {
		return totalPayment;
	}

	/**
	 * Remove a {@link Loan} from the {@link Founding}.
	 *
	 * @param loan
	 *            the loan to remove
	 */
	void remove(final Loan loan) {
		if (loan == null || !loans.containsKey(loan.getId())) return;
		loan.unregister(l -> updated((Loan) l));
		loans.remove(loan.getId());
		update();
	}

	/**
	 * Update.
	 *
	 * @param name
	 *            {@link #name}
	 * @param bankName
	 *            {@link #bankName}
	 */
	void update(final String name, final String bankName) {
		setName(name);
		this.bankName = bankName;
	}

	/**
	 * Update the values.
	 */
	private void update() {
		amount = 0;
		totalInterest = 0;
		term = 0;
		totalPayment = 0;
		riskCapital = 0;
		redemptionPlan = new LinkedList<>();
		startDate = LocalDate.now();

		for (final Loan loan : loans.values()) {
			amount += loan.getAmount();
			totalInterest += loan.getTotalInterest();
			term = Integer.max(term, loan.getTerm());
			totalPayment += loan.getTotalPayment();
			riskCapital += loan.getRiskCapital();
			final List<RedemptionPlanEntry> loanRedPlan = loan.getRedemptionPlan();
			for (int i = 0; i < loanRedPlan.size(); i++) {
				final RedemptionPlanEntry e1 = loanRedPlan.get(i);
				if (i >= redemptionPlan.size()) {
					final double interest = e1.getInterest();
					final double redemption = e1.getRedemption();
					final double residualDebt = e1.getResidualDebt();
					final RedemptionPlanEntry copy = new BaseRedemptionPlanEntry(residualDebt, interest, redemption);
					redemptionPlan.add(copy);
				} else {
					final RedemptionPlanEntry e2 = redemptionPlan.get(i);
					final double interest = e1.getInterest() + e2.getInterest();
					final double redemption = e1.getRedemption() + e2.getRedemption();
					final double residualDebt = e1.getResidualDebt() + e2.getResidualDebt();
					final RedemptionPlanEntry merge = new BaseRedemptionPlanEntry(residualDebt, interest, redemption);
					redemptionPlan.set(i, merge);
				}
			}
			if (loan.getStartDate().isBefore(startDate)) {
				startDate = loan.getStartDate();
			}
			if (endDate == null || loan.getEndDate().isAfter(endDate)) {
				endDate = loan.getEndDate();
			}
		}
		notifyChanged();
	}

	/**
	 * A {@link Loan} has been updated.
	 *
	 * @param loan
	 *            the {@link Loan}
	 */
	private void updated(final Loan loan) {
		update();
	}

	/** the amount. */
	private double amount;
	/** the name of the bank. */
	private String bankName;
	/** the end {@link LocalDate}. */
	private LocalDate endDate;
	/** the {@link Loan} by id. */
	private final Map<Long, Loan> loans = new LinkedHashMap<>();
	/** the {@link List} if {@link RedemptionPlanEntry}s. */
	private List<RedemptionPlanEntry> redemptionPlan = new LinkedList<>();
	/** the risk capital. */
	private double riskCapital;
	/** the start {@link LocalDate}. */
	private LocalDate startDate;
	/** the term. */
	private int term;
	/** the total interest. */
	private double totalInterest;
	/** the total payment. */
	private double totalPayment;
}
