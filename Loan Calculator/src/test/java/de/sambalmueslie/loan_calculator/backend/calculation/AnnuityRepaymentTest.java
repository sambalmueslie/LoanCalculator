package de.sambalmueslie.loan_calculator.backend.calculation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.sambalmueslie.loan_calculator.backend.redemption_plan.RedemptionPlan;

public class AnnuityRepaymentTest {

	/**
	 * Test the complete repayment of a {@link AnnuityRepayment}.
	 */
	@Test
	public void testCompleteRepayment() {
		final double amount = 100000;
		final double debitInterest = 2.0;
		final double paymentRate = 3.0862;
		final AnnuityRepayment repayment = RepaymentFactory.createAnnuityRepayment(amount, debitInterest, paymentRate);

		assertEquals(amount, repayment.getAmount(), 0.1);
		assertEquals(debitInterest, repayment.getDebitInterest(), 0.1);
		assertEquals(paymentRate, repayment.getPaymentRate(), 0.1);
		assertEquals(BaseAnnuityRepayment.MAX_ITERATIONS, repayment.getPeriods());

		final RedemptionPlan redemptionPlan = repayment.getRedemptionPlan();
		final double totalInterest = 27156.69;
		assertEquals(totalInterest, redemptionPlan.getTotalInterest(), 0.1);
		assertEquals(amount + totalInterest, redemptionPlan.getTotalPayment(), 0.1);
		assertEquals(0, redemptionPlan.getRiskCapital(), 0.1);
		assertEquals(25, redemptionPlan.getTerm());
	}

	/**
	 * Test the never ending {@link AnnuityRepayment}.
	 */
	@Test
	public void testNeverEndingRepayment() {
		final double amount = 100000;
		final double debitInterest = 2.0;
		final double paymentRate = 0;
		final AnnuityRepayment repayment = RepaymentFactory.createAnnuityRepayment(amount, debitInterest, paymentRate);

		assertEquals(amount, repayment.getAmount(), 0.1);
		assertEquals(debitInterest, repayment.getDebitInterest(), 0.1);
		assertEquals(paymentRate, repayment.getPaymentRate(), 0.1);
		assertEquals(BaseAnnuityRepayment.MAX_ITERATIONS, repayment.getPeriods());

		final RedemptionPlan redemptionPlan = repayment.getRedemptionPlan();
		final double totalInterest = 300000;
		assertEquals(totalInterest, redemptionPlan.getTotalInterest(), 0.1);
		assertEquals(amount + totalInterest, redemptionPlan.getTotalPayment(), 0.1);
		assertEquals(100000, redemptionPlan.getRiskCapital(), 0.1);
		assertEquals(150, redemptionPlan.getTerm());
	}

	/**
	 * Test the partial repayment finish faster than periods {@link AnnuityRepayment}.
	 */
	@Test
	public void testPartialFinishBeforePeriodsEndRepayment() {
		final double amount = 100000;
		final double debitInterest = 2.0;
		final double paymentRate = 3.0862;
		final int periods = 30;
		final AnnuityRepayment repayment = RepaymentFactory.createAnnuityRepayment(amount, debitInterest, paymentRate, periods);

		assertEquals(amount, repayment.getAmount(), 0.1);
		assertEquals(debitInterest, repayment.getDebitInterest(), 0.1);
		assertEquals(paymentRate, repayment.getPaymentRate(), 0.1);
		assertEquals(periods, repayment.getPeriods());

		final RedemptionPlan redemptionPlan = repayment.getRedemptionPlan();
		final double totalInterest = 27156.69;
		assertEquals(totalInterest, redemptionPlan.getTotalInterest(), 0.1);
		assertEquals(amount + totalInterest, redemptionPlan.getTotalPayment(), 0.1);
		assertEquals(0, redemptionPlan.getRiskCapital(), 0.1);
		assertEquals(25, redemptionPlan.getTerm());
	}

	/**
	 * Test the partial repayment of a {@link AnnuityRepayment}.
	 */
	@Test
	public void testPartialRepayment() {
		final double amount = 100000;
		final double debitInterest = 2.0;
		final double paymentRate = 3.0862;
		final int periods = 10;
		final AnnuityRepayment repayment = RepaymentFactory.createAnnuityRepayment(amount, debitInterest, paymentRate, periods);

		assertEquals(amount, repayment.getAmount(), 0.1);
		assertEquals(debitInterest, repayment.getDebitInterest(), 0.1);
		assertEquals(paymentRate, repayment.getPaymentRate(), 0.1);
		assertEquals(periods, repayment.getPeriods());

		final RedemptionPlan redemptionPlan = repayment.getRedemptionPlan();
		final double totalInterest = 16728.72;
		assertEquals(totalInterest, redemptionPlan.getTotalInterest(), 0.1);
		assertEquals(amount + totalInterest, redemptionPlan.getTotalPayment(), 0.1);
		assertEquals(65866.72, redemptionPlan.getRiskCapital(), 0.1);
		assertEquals(periods, redemptionPlan.getTerm());
	}

}
