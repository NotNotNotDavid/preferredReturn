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
    public void testAddPaymentThirdMonthWithInterest() {
        // month 1
        world1.nextMonth();

        // month 2
        world1.makePayment(50);
        assertEquals(50, tranche1.getPrincipleRemaining(), 1);
        assertTrue(!tranche1.getifPaid());

        world1.nextMonth();

        // month 3 (2nd Month) (3rd Month)
        double tranche1Interest = (100 * (1 + MONTHLY_RATE) - 100) + (50 * (1 + MONTHLY_RATE) - 50); 
        assertEquals(tranche1.getPreferredReturn(), tranche1Interest, 0.1);
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
        assertEquals(0, tranche1.getPrincipleRemaining(), 0.1);
        assertEquals(199, tranche2.getPrincipleRemaining(), 0.1);
        assertTrue(tranche1.getifPaid());
        assertTrue(!tranche2.getifPaid());
        world1.nextMonth();

        // month = 3
        double tranche1Interest = (100 * (1 + MONTHLY_RATE) - 100); // calculates month 2 and 3
        double tranche2Interest = (299 * (1 + MONTHLY_RATE) - 299); // calculates month 3

        assertEquals(tranche1.getPreferredReturn(), tranche1Interest, 0.1);
        assertEquals(tranche2.getPreferredReturn(), tranche2Interest, 1);

    }

    @Test
    public void testNextMonth() {
        world1.nextMonth();
        assertEquals(2, world1.getCurrentMonth());
    }

    @Test
    public void testNextMonthInterest() {
        world1.nextMonth();
        double tranche1Interest = (100 * (1 + Fund.MPR) - 100);
        assertEquals(tranche1Interest, tranche1.calculateInterest(), 0.01);
    }

    @Test
    public void testNextNextMonthInterest() {
        world1.nextMonth();
        world1.nextMonth();
        double tranche1Interest = (100 * Math.pow((1 + Fund.MPR),1) - 100) * 2;
        assertEquals(tranche1Interest, tranche1.calculateInterest(), 0.01);
        
    }

    @Test
    public void testNextYear() {
        world1.nextYear();
        assertEquals(13, world1.getCurrentMonth());
    }

    @Test
    public void testNextYearInterest() {
        world1.nextYear();
        double tranche1Interest = (100 * Math.pow(1 + Fund.MPR, 12) - 100);
        assertEquals(tranche1Interest, tranche1.getPreferredReturn(), 0.01);
    }

}
