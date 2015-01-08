/**
 *
 */
package de.sambalmueslie.loan_calculator.model.founding;

import java.util.*;

import de.sambalmueslie.loan_calculator.model.generic.GenericModelEntryChangeListener;
import de.sambalmueslie.loan_calculator.model.loan.BaseRedemptionPlanEntry;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.model.loan.RedemptionPlanEntry;

/**
 * A base {@link Founding}.
 *
 * @author sambalmueslie 2015
 */
public class BaseFounding implements Founding {

	/**
	 * Constructor.
	 *
	 * @param name
	 *            {@link #name}
	 * @param bankName
	 *            {@link #bankName}
	 * @throws on
	 *             illegal argument
	 */
	public BaseFounding(final String name, final String bankName) throws IllegalArgumentException {
		id = UUID.randomUUID().getLeastSignificantBits();
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
		loan.register(this::updated);
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
	 * @see de.sambalmueslie.loan_calculator.model.founding.Founding#getId()
	 */
	@Override
	public long getId() {
		return id;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.founding.Founding#getLoans()
	 */
	@Override
	public List<Loan> getLoans() {
		return Collections.unmodifiableList(new LinkedList<Loan>(loans.values()));
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.founding.Founding#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.founding.Founding#getRedemptionPlan()
	 */
	@Override
	public List<RedemptionPlanEntry> getRedemptionPlan() {
		return Collections.unmodifiableList(redemptionPlan);
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
	 * @see de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry#register(de.sambalmueslie.loan_calculator.model.generic.GenericModelEntryChangeListener)
	 */
	@Override
	public void register(final GenericModelEntryChangeListener<Founding> listener) {
		if (listener == null || listeners.contains(listener)) return;
		listeners.add(listener);
	}

	/**
	 * Remove a {@link Loan} from the {@link Founding}.
	 *
	 * @param loan
	 *            the loan to remove
	 */
	public void remove(final Loan loan) {
		if (loan == null || !loans.containsKey(loan.getId())) return;
		loan.unregister(this::updated);
		loans.remove(loan.getId());
		update();
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.model.generic.GenericModelEntry#unregister(de.sambalmueslie.loan_calculator.model.generic.GenericModelEntryChangeListener)
	 */
	@Override
	public void unregister(final GenericModelEntryChangeListener<Founding> listener) {
		if (listener == null) return;
		listeners.remove(listener);
	}

	/**
	 * Update.
	 *
	 * @param name
	 *            {@link #name}
	 * @param bankName
	 *            {@link #bankName}
	 * @throws IllegalArgumentException
	 *             on invalid argument
	 */
	public void update(final String name, final String bankName) throws IllegalArgumentException {
		if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name '" + name + "' for founding cannot be null or empty.");
		this.name = name;
		if (bankName == null || bankName.isEmpty()) throw new IllegalArgumentException("Bank name '" + bankName + "' for founding cannot be null or empty.");
		this.bankName = bankName;
	}

	/**
	 * Notify that the founding has changed.
	 */
	private void notifyChanged() {
		listeners.forEach(l -> l.entryChanged(this));
	}

	/**
	 * Update the values.
	 */
	private void update() {
		amount = 0;
		totalInterest = 0;
		term = 0;
		totalPayment = 0;
		redemptionPlan = new LinkedList<>();

		for (final Loan loan : loans.values()) {
			amount += loan.getAmount();
			totalInterest += loan.getTotalInterest();
			term = Integer.max(term, loan.getTerm());
			totalPayment += loan.getTotalPayment();
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
	/** the id. */
	private final long id;
	/** the {@link GenericModelEntryChangeListener}s. */
	private final List<GenericModelEntryChangeListener<Founding>> listeners = new LinkedList<>();
	/** the {@link Loan} by id. */
	private final Map<Long, Loan> loans = new HashMap<>();
	/** the name. */
	private String name;
	/** the {@link List} if {@link RedemptionPlanEntry}s. */
	private List<RedemptionPlanEntry> redemptionPlan;
	/** the term. */
	private int term;
	/** the total interest. */
	private double totalInterest;

	/** the total payment. */
	private double totalPayment;
}
