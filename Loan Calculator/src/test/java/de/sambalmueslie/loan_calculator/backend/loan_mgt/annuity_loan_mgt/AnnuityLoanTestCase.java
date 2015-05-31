/**
 *
 */
package de.sambalmueslie.loan_calculator.backend.loan_mgt.annuity_loan_mgt;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlan;
import de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlanEntry;

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

		final RedemptionPlan redemptionPlan = loan.getRedemptionPlan();
		final RedemptionPlanEntry entry = redemptionPlan.getEntries().get(1);
		assertEquals(1972.35, entry.getInterest(), 0.1);
		assertEquals(3027.65, entry.getRedemption(), 0.1);

		assertEquals(100000, loan.getAmount(), 0.1);
		assertEquals(27812.86, redemptionPlan.getTotalInterest(), 0.1);
		assertEquals(127812.86, redemptionPlan.getTotalPayment(), 0.1);
		assertEquals(26, redemptionPlan.getTerm());
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
		final RedemptionPlan redemptionPlan = loan.getRedemptionPlan();
		final RedemptionPlanEntry redemption = redemptionPlan.getEntries().get(1);
		assertEquals(settings.getAmount() * settings.getPaymentRate() / 100, redemption.getRedemption(), 50.0);
		assertEquals(settings.getAmount() * settings.getFixedDebitInterest() / 100, redemption.getInterest(), 50.0);

		// check annuity
		final double annuityFixed = settings.getAmount() * (settings.getPaymentRate() + settings.getFixedDebitInterest()) / 100;
		for (int i = 1; i < settings.getFixedInterestPeriod(); i++) {
			final RedemptionPlanEntry r = redemptionPlan.getEntries().get(i);
			assertEquals(annuityFixed, r.getInterest() + r.getRedemption(), 0.01);
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
