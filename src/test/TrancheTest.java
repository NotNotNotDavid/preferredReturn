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

    @BeforeEach
    public void runBefore() {
        month = 1;
        tranche1 = new Tranche(100, "Tranche A");
        tranche2 = new Tranche(200, "Tranche B");
    }

    @Test
    public void testConstructor() {
        assertEquals(100, tranche1.getPrincipleRemaining(), 1);
        assertEquals(tranche1.getPreferredReturn(), 0, 0);
    }
    
    @Test
    public void testCalculateInterestMonth2(){
        month = 1;
        double principleRemaining = tranche1.getPrincipleRemaining();
        double interest = Fund.calculationMPR(100, 1);
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
        double interest = Fund.calculationMPR(tranche1Amount, month);
        assertEquals(interest, tranche1.calculateInterest(13), 1);
        assertEquals(tranche1.getPreferredReturn(), 0, 1);
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

    @Test
    public void testMakePaymentPR() {
        // month 1
        assertEquals(tranche1.getPrincipleRemaining(), 100, 0.001);
        assertEquals(tranche1.getPreferredReturn(), 0, 0.001);

        // simulating next month (month = 2)
        tranche1.setMonthCounter(1);
        tranche1.makePayment(40);
        double interest = Fund.calculationMPR(100, 1);
        assertEquals(tranche1.getPreferredReturn(), interest, 0.001);
        assertEquals(tranche1.getPrincipleRemaining(), 60, 0.001);

        //simulating next month (month = 3)
        tranche1.setMonthCounter(1);
        tranche1.makePayment(60);
        double secondInterest = Fund.calculationMPR(60, 1);
        assertEquals(tranche1.getPreferredReturn(), interest + secondInterest, 0.001);
        assertEquals(tranche1.getPrincipleRemaining(), 0, 0.001);
    }

    @Test
    public void testMakePaymentPRLonger() {
        // month 1
        assertEquals(tranche1.getPrincipleRemaining(), 100, 0.001);
        assertEquals(tranche1.getPreferredReturn(), 0, 0.001);

        // simulating next month (month = 2)
        tranche1.setMonthCounter(1);
        tranche1.makePayment(40);
        double interest = Fund.calculationMPR(100, 1);
        assertEquals(tranche1.getPreferredReturn(), interest, 0.001);
        assertEquals(tranche1.getPrincipleRemaining(), 60, 0.001);

        //simulating next year (month = 14)
        tranche1.setMonthCounter(12);
        tranche1.makePayment(60);
        double secondInterest = Fund.calculationMPR(60, 12);
        assertEquals(tranche1.getPreferredReturn(), interest + secondInterest, 0.001);
        assertEquals(tranche1.getPrincipleRemaining(), 0, 0.001);
    }
}
