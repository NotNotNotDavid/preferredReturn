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

    PriorityQueue<Tranche> listOfTranches;
    

    @BeforeEach
    public void runBefore() {
        world1 = new World();
        month = 1;
        PriorityQueue<Tranche> listOfTranches = new PriorityQueue<>(Comparator.comparingInt(t -> t.getStartMonth()));
        tranche1 = new Tranche(100, 1, "Tranche C");
        listOfTranches.add(tranche1);
    }

    @Test
    public void testAddPayment() {
        //note: month is 1 here
        world1.addPayment(100);
        assertEquals(0, tranche1.getPrincipleRemaining(), 0.1);
        assertTrue(!tranche1.getifPaid());
    }

    @Test
    public void testAddPaymentSecondMonth() {
        world1.nextMonth();
        world1.addPayment(50);
        assertEquals(50, tranche1.getPrincipleRemaining(), 0.1);
        assertTrue(!tranche1.getifPaid());
    }

    @Test
    public void testAddPaymentThirdMonthWithInterest() {
        // month 1
        world1.addPayment(50);
        world1.nextMonth();

        // month 2
        assertEquals(50, tranche1.getPrincipleRemaining(), 1);
        assertTrue(!tranche1.getifPaid());

        world1.nextMonth();

        //month 3                       (2nd Month)                         (3rd Month)
        double tranche1Interest = (100 * (1 + MONTHLY_RATE) - 100) + (50 * (1 + MONTHLY_RATE) - 50); // calculates month 1 and 2 and 3
        assertEquals(tranche1.getPreferredReturn(), tranche1Interest, 0.1);
    }

    @Test
    public void testAddPaymentPastFirst() {
        tranche2 = new Tranche(200, 2, "Tranche B");
        listOfTranches.add(tranche2);
        world1.nextMonth();
        world1.nextMonth();
        world1.addPayment(101);
        assertEquals(0, tranche1.getPrincipleRemaining(), 0.1);
        assertEquals(299, tranche2.getPrincipleRemaining(), 0.1);
        assertTrue(tranche1.getifPaid());
        assertTrue(!tranche2.getifPaid());
    } 

    @Test
    public void testAddPaymentWhichIsFirst() {

        // NOTE THAT THERE WILL ALWAYS BE INTEREST COVERED:
        // i.e. there will not be a scenario where there is an amount of payment that won't be counted

        // month = 1
        world1.nextMonth();

        // month = 2
        tranche2 = new Tranche(200, 2, "Tranche B");
        listOfTranches.add(tranche2);

        world1.addPayment(101);
        assertEquals(0, tranche1.getPrincipleRemaining(), 0.1);
        assertEquals(299, tranche2.getPrincipleRemaining(), 0.1);
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
    public void testNextYear() {
        world1.nextYear();
        assertEquals(13, world1.getCurrentMonth());
    }

    

}
