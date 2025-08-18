package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;


public class TrancheTest {
    Tranche tranche1;
    Tranche tranche2;
    private static final double ANNUAL_RATE = 0.08;
    private static final double MONTHLY_RATE = ANNUAL_RATE / 12;

    @BeforeEach
    public void runBefore() {
        tranche1 = new Tranche(100000, 1, "Tranche A");
        tranche2 = new Tranche(200000, 3, "Tranche B");
    }

    @Test
    public void testConstructor() {
        assertEquals(100000, tranche1.getPrincipalRemaining(), 1);
    }

    @Test
    public void testProcessPaymentAmountZero() {
        tranche1.processPayment(0, 1);
        assertEquals(tranche1.getPrincipalRemaining(), 100000, 1);
        assertEquals(tranche1.getPreferredReturn(), 0, 1);
    }

    @Test
    public void testProcessPaymentAmountNegative() {
        tranche1.processPayment(-1, 1);
        assertEquals(tranche1.getPrincipalRemaining(), 100000, 1);
        assertEquals(tranche1.getPreferredReturn(), 0, 1);
    }

    @Test
    public void testProcessPaymentMultiMonths() {
        tranche1.processPayment(0, 3);
        assertEquals(tranche1.getPreferredReturn(), 100000 * Math.pow((1 + MONTHLY_RATE), 2) - 100000, 0.01);
    }

    @Test
    public void testProcessPaymentMultiMonthsSeparate() {
        tranche1.processPayment(0, 3);
        tranche1.processPayment(0, 2);
        tranche1.processPayment(0, 1);
        assertEquals(tranche1.getPreferredReturn(), 100000 * Math.pow((1 + MONTHLY_RATE), 2) - 100000, 0.01);
    }



}
