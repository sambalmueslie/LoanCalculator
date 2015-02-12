/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

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
		final AnnuityLoanSettings settings = new AnnuityLoanSettings("Name", 100000, LocalDate.now(), 3.00, 2.00, 50, 5.00, 0.00);
		final AnnuityLoan loan = new BaseAnnuityLoan(0, settings);

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
		final AnnuityLoanSettings settings = new AnnuityLoanSettings("Name", 100000, LocalDate.now(), 3.00, 2.00, 10, 5.00, 0.00);
		final AnnuityLoan loan = new BaseAnnuityLoan(0, settings);
		assertEquals(settings.getName(), loan.getName());
		assertEquals(settings.getAmount(), loan.getAmount(), 0.01);
		assertEquals(settings.getPaymentRate(), loan.getPaymentRate(), 0.01);
		assertEquals(settings.getFixedDebitInterest(), loan.getFixedDebitInterest(), 0.01);
		assertEquals(settings.getFixedInterestPeriod(), loan.getFixedInterestPeriod());
		assertEquals(settings.getEstimatedDebitInterest(), loan.getEstimatedDebitInterest(), 0.01);

		// check first redemption
		final RedemptionPlanEntry redemption = loan.getRedemptionPlan().get(1);
		assertEquals(settings.getAmount() * settings.getPaymentRate() / 100, redemption.getRedemption(), 0.01);
		assertEquals(settings.getAmount() * settings.getFixedDebitInterest() / 100, redemption.getInterest(), 0.01);

		// check annuity
		final double annuity = settings.getAmount() * (settings.getPaymentRate() + settings.getFixedDebitInterest()) / 100;
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
		final AnnuityLoanSettings settings = new AnnuityLoanSettings("Name", 100000, LocalDate.now(), 3.00, 2.00, 10, 5.00, 5.00);
		final BaseAnnuityLoan loan = new BaseAnnuityLoan(0, settings);
		final AnnuityLoanSettings update = new AnnuityLoanSettings("New-Name", 50000, LocalDate.now(), 4.00, 3.00, 15, 6.00, 5.00);
		loan.update(update);
		assertEquals(update.getName(), loan.getName());
		assertEquals(update.getAmount(), loan.getAmount(), 0.01);
		assertEquals(update.getPaymentRate(), loan.getPaymentRate(), 0.01);
		assertEquals(update.getFixedDebitInterest(), loan.getFixedDebitInterest(), 0.01);
		assertEquals(update.getFixedInterestPeriod(), loan.getFixedInterestPeriod());
		assertEquals(update.getEstimatedDebitInterest(), loan.getEstimatedDebitInterest(), 0.01);
	}

}
