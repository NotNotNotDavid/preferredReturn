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
    int tranche1Amount = 100;
    int tranche2Amount = 200;
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
        assertEquals(tranche1.getStartMonth(), 0, 0);
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
        month = 12;
        double interest = 0;
        assertEquals(interest, tranche1.calculateInterest(12), 1);
        assertEquals(tranche1.getPreferredReturn(), interest, 1);
    }


}
