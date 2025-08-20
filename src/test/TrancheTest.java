package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;


public class TrancheTest {
    int month;
    Tranche tranche1;
    Tranche tranche2;
    double tranche1Amount = 100;
    double tranche2Amount = 200;
    private static final double ANNUAL_RATE = 0.08;
    private static final double MONTHLY_RATE = ANNUAL_RATE / 12;

    @BeforeEach
    public void runBefore() {
        month = 1;
        tranche1 = new Tranche(100, month, "Tranche A");
        tranche2 = new Tranche(200, month, "Tranche B");
    }

    @Test
    public void testConstructor() {
        assertEquals(100, tranche1.getPrincipleRemaining(), 1);
        assertEquals(tranche1.getPreferredReturn(), 0, 0);
        assertEquals(tranche1.getStartMonth(), 1, 0);
    }
    
    @Test
    public void testCalculateInterestMonth2(){
        month = 1;
        double principleRemaining = tranche1.getPrincipleRemaining();
        double interest = principleRemaining * (1 + MONTHLY_RATE) - principleRemaining;
        assertEquals(interest, tranche1.calculateInterest(2), 1);
        assertEquals(tranche1.getPreferredReturn(), interest, 1);
    }

    @Test
    public void testCalculateInterestMonthNone(){
        month = 1;
        double interest = 0;
        assertEquals(interest, tranche1.calculateInterest(1), 1);
        assertEquals(tranche1.getPreferredReturn(), interest, 1);
    }

    @Test
    public void testCalculateInterestMonthNoneFurther(){
        month = 13;
        double interest = 100 * Math.pow((1 + MONTHLY_RATE), 13) - 100;
        assertEquals(interest, tranche1.calculateInterest(13), 1);
        assertEquals(tranche1.getPreferredReturn(), interest, 1);
    }
    
    @Test
    public void testMakePayment() {
        tranche1.makePayment(100);
        assertEquals(tranche1.getPrincipleRemaining(), 0, 0.1);
        assertTrue(tranche1.getifPaid());
    }

    @Test
    public void testMakePaymentFalse() {
        tranche1.makePayment(99);
        assertEquals(tranche1.getPrincipleRemaining(), 1, 0.1);
        assertTrue(!tranche1.getifPaid());
    }

    @Test
    public void testMakePaymentTrueEdge() {
        assertEquals(tranche1.makePayment(101.1), 1.1, 0.001);
        assertEquals(tranche1.getPrincipleRemaining(), 0, 0.001);
        assertTrue(tranche1.getifPaid());
    }
}
