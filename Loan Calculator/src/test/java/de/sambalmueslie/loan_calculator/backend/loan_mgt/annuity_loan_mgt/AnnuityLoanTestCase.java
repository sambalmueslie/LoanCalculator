/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.sambalmueslie.loan_calculator.backend.loan_mgt.RedemptionPlanEntry;

/**
 * @author sambalmueslie 2015
 */
public class AnnuityLoanTestCase {

	/**
	 * Test the calculation of a {@link AnnuityLoan}.
	 */
	@Test
	public void testAnnuityLoanCalculation() {
		final String name = "Name";
		final double amount = 100000;
		final double paymentRate = 3.00;
		final double fixedDebitInterest = 2.00;
		final int fixedInterestPeriod = 50;
		final double estimatedDebitInterest = 5.00;
		final AnnuityLoan loan = new BaseAnnuityLoan(0, name, amount, null, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest);

		final RedemptionPlanEntry entry = loan.getRedemptionPlan().get(1);
		assertEquals(2000, entry.getInterest(), 0.001);
		assertEquals(3000, entry.getRedemption(), 0.001);

		assertEquals(100000, loan.getAmount(), 0.001);
		assertEquals(128987.28, loan.getTotalPayment(), 0.01);
		assertEquals(28987.28, loan.getTotalInterest(), 0.01);
		assertEquals(26, loan.getTerm());
	}

	/**
	 * Test the creation of a {@link AnnuityLoan}.
	 */
	@Test
	public void testAnnuityLoanCreation() {
		final String name = "Name";
		final double amount = 100000;
		final double paymentRate = 3.00;
		final double fixedDebitInterest = 2.00;
		final int fixedInterestPeriod = 10;
		final double estimatedDebitInterest = 5.00;
		final AnnuityLoan loan = new BaseAnnuityLoan(0, name, amount, null, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest);
		assertEquals(name, loan.getName());
		assertEquals(amount, loan.getAmount(), 0.01);
		assertEquals(paymentRate, loan.getPaymentRate(), 0.01);
		assertEquals(fixedDebitInterest, loan.getFixedDebitInterest(), 0.01);
		assertEquals(fixedInterestPeriod, loan.getFixedInterestPeriod());
		assertEquals(estimatedDebitInterest, loan.getEstimatedDebitInterest(), 0.01);

		// check first redemption
		final RedemptionPlanEntry redemption = loan.getRedemptionPlan().get(1);
		assertEquals(amount * paymentRate / 100, redemption.getRedemption(), 0.01);
		assertEquals(amount * fixedDebitInterest / 100, redemption.getInterest(), 0.01);

		// check annuity
		final double annuity = amount * (paymentRate + fixedDebitInterest) / 100;
		for (int i = 1; i < loan.getRedemptionPlan().size(); i++) {
			final RedemptionPlanEntry r = loan.getRedemptionPlan().get(i);
			assertEquals(annuity, r.getInterest() + r.getRedemption(), 0.01);
		}

	}

	/**
	 * Test the update of a {@link AnnuityLoan}.
	 */
	@Test
	public void testAnnuityLoanUpdate() {
		String name = "Name";
		double amount = 100000;
		double paymentRate = 3.00;
		double fixedDebitInterest = 2.00;
		int fixedInterestPeriod = 10;
		double estimatedDebitInterest = 5.00;
		final BaseAnnuityLoan loan = new BaseAnnuityLoan(0, name, amount, null, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest);
		name = "New-Name";
		amount = 50000;
		paymentRate = 4.00;
		fixedDebitInterest = 3.00;
		fixedInterestPeriod = 15;
		estimatedDebitInterest = 6.00;
		loan.update(name, amount, null, paymentRate, fixedDebitInterest, fixedInterestPeriod, estimatedDebitInterest);
		assertEquals(name, loan.getName());
		assertEquals(amount, loan.getAmount(), 0.01);
		assertEquals(paymentRate, loan.getPaymentRate(), 0.01);
		assertEquals(fixedDebitInterest, loan.getFixedDebitInterest(), 0.01);
		assertEquals(fixedInterestPeriod, loan.getFixedInterestPeriod());
		assertEquals(estimatedDebitInterest, loan.getEstimatedDebitInterest(), 0.01);
	}

}
