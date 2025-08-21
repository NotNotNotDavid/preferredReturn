package test;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import org.junit.jupiter.api.BeforeEach;

import model.*;

public class WorldTest {

    int month;
    World world1;
    Tranche tranche1;
    Tranche tranche2;
    Tranche tranche3;
    double tranche1Amount = 100;
    double tranche2Amount = 200;
    private static final double ANNUAL_RATE = 0.08;
    private static final double MONTHLY_RATE = ANNUAL_RATE / 12;

    ArrayList<Tranche> listOfTranches;

    @BeforeEach
    public void runBefore() {
        world1 = new World();
        month = 1;
        listOfTranches = new ArrayList<>();
        tranche1 = new Tranche(100, "Tranche C");
        world1.addTranche(tranche1);
    }

    @Test
    public void testAddPayment() {
        // note: month is 1 here
        world1.makePayment(100);
        assertEquals(0, tranche1.getPrincipleRemaining(), 0.1);
        assertTrue(tranche1.getifPaid());
    }

    @Test
    public void testAddPaymentSecondMonth() {
        world1.nextMonth();
        world1.makePayment(50);
        assertEquals(50, tranche1.getPrincipleRemaining(), 0.1);
        assertTrue(!tranche1.getifPaid());
    }

    @Test
    public void testAddPaymentPastFirst() {
        tranche2 = new Tranche(200, "Tranche B");
        world1.addTranche(tranche2);
        world1.nextMonth();
        world1.nextMonth();
        world1.makePayment(101);
        assertEquals(0, tranche1.getPrincipleRemaining(), 0.1);
        assertEquals(199, tranche2.getPrincipleRemaining(), 0.1);
        assertTrue(tranche1.getifPaid());
        assertTrue(!tranche2.getifPaid());
    }

    @Test
    public void testNextMonth() {
        world1.nextMonth();
        assertEquals(2, world1.getCurrentMonth());
    }

    @Test
    public void testNextMonthInterest() {
        world1.nextMonth();
        double tranche1Interest = Fund.calculationMPR(100, 1);
        assertEquals(tranche1Interest, tranche1.calculateInterest(), 0.01);
    }

    @Test
    public void testNextNextMonthInterest() {
        // month 1
        world1.nextMonth();
        world1.nextMonth();

        // month 3
        double tranche1Interest = Fund.calculationMPR(100, 2);
        assertEquals(tranche1Interest, tranche1.calculateInterest(), 0.01);

    }

    @Test
    public void testNextYear() {
        world1.nextYear();
        assertEquals(13, world1.getCurrentMonth());
    }

    @Test
    public void testGetNextCurrentMonth() {
        world1.nextMonth();
        assertEquals(tranche1.getMonthCounter(), 1);
    }

    @Test
    public void testMakePaymentWithThreePayments() {
        // month 1
        assertEquals(0, tranche1.getPreferredReturn(), 0.01);
        world1.nextMonth();

        // month 2
        world1.makePayment(10);
        double tranche1InterestFirstMonth = Fund.calculationMPR(100, 1);
        assertEquals(tranche1InterestFirstMonth, tranche1.getPreferredReturn(), 0.01);
        assertEquals(90, tranche1.getPrincipleRemaining(), 0.01);
        world1.nextMonth();

        // month 3
        world1.makePayment(20);
        double tranche1InterestSecondMonth = Fund.calculationMPR(90, 1);
        assertEquals(tranche1InterestFirstMonth + tranche1InterestSecondMonth, tranche1.getPreferredReturn(), 0.01);
        assertEquals(70, tranche1.getPrincipleRemaining(), 0.01);
        world1.nextMonth();

        // month 4
        world1.makePayment(30);
        double tranche1InterestThirdMonth = Fund.calculationMPR(70, 1);
        assertEquals(tranche1InterestFirstMonth + tranche1InterestSecondMonth + tranche1InterestThirdMonth,
                tranche1.getPreferredReturn(), 0.01);
        assertEquals(40, tranche1.getPrincipleRemaining(), 0.01);
    }

    @Test
    public void testNextYearInterestPayment() {

        // month 1
        world1.nextYear();

        // month 13
        world1.makePayment(100);
        double tranche1Interest = Fund.calculationMPR(100, 12);
        assertEquals(tranche1Interest, tranche1.getPreferredReturn(), 0.01);
    }

    @Test
    public void tesLastOverPayment() {

        // month 1
        world1.nextYear();

        // month 13
        world1.makePayment(10000000);
        double tranche1Interest = Fund.calculationMPR(100, 12);
        assertEquals(tranche1Interest, tranche1.getPreferredReturn(), 0.01);
    }

    @Test
    public void testAddPaymentWhichIsFirst() {

        // NOTE THAT THERE WILL ALWAYS BE INTEREST COVERED:
        // i.e. there will not be a scenario where there is an amount of payment that
        // won't be counted

        // month = 1
        world1.nextMonth();

        // month = 2
        tranche2 = new Tranche(200, "Tranche B");
        world1.addTranche(tranche2);

        world1.makePayment(101);
        double tranche1Interest = Fund.calculationMPR(100, 1);
        double tranche2Interest = Fund.calculationMPR(199, 0);
        assertEquals(0, tranche1.getPrincipleRemaining(), 0.1);
        assertEquals(199, tranche2.getPrincipleRemaining(), 0.1);
        assertTrue(tranche1.getifPaid());
        assertTrue(!tranche2.getifPaid());

        assertEquals(tranche1.getPreferredReturn(), tranche1Interest, 0.01);
        assertEquals(tranche2.getPreferredReturn(), tranche2Interest, 0.01);

        // month 3
        world1.nextMonth();
        world1.makePayment(101);
        double tranche2InterestSecondMonth = Fund.calculationMPR(199, 1);
        assertEquals(tranche2.getPrincipleRemaining(), 98, 0.1);
        assertTrue(!tranche2.getifPaid());
        assertEquals(tranche2.getPreferredReturn(), tranche2Interest + tranche2InterestSecondMonth, 0.01);
        assertEquals(tranche1.getPreferredReturn(), tranche1Interest, 0.01);

    }

    @Test
    public void testGetTotalPreferredReturn() {
        // month = 1
        world1.nextMonth();

        // month = 2
        tranche2 = new Tranche(200, "Tranche B");
        world1.addTranche(tranche2);
        world1.nextMonth();

        // month = 3
        tranche3 = new Tranche(300, "Tranche C");
        world1.addTranche(tranche3);
        world1.nextMonth();

        // month = 4
        double tranche1Interest = Fund.calculationMPR(100, 3);
        double tranche2Interest = Fund.calculationMPR(200, 2);
        double tranche3Interest = Fund.calculationMPR(300,1);

        double allPR = tranche1Interest + tranche2Interest + tranche3Interest;
        
        assertEquals(allPR, world1.showAllPreferredReturns(), 0.01);
    }

}
