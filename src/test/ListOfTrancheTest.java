// package test;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import model.*;

// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertNull;
// import static org.junit.Assert.assertTrue;
// import static org.junit.jupiter.api.Assertions.assertAll;

// import java.util.ArrayList;
// import java.util.Comparator;
// import java.util.PriorityQueue;

// public class ListOfTrancheTest {
    
//     int month;
//     Tranche tranche1;
//     Tranche tranche2;
//     Tranche tranche3;
//     double tranche1Amount = 100;
//     double tranche2Amount = 200;
//     private static final double ANNUAL_RATE = 0.08;
//     private static final double MONTHLY_RATE = ANNUAL_RATE / 12;
//     ArrayList<Tranche> listOfTranche;
    

//     @BeforeEach
//     public void runBefore() {
//         month = 3;
//         listOfTranche = new ArrayList<>();
//         tranche1 = new Tranche(100, 1, "Tranche C");
//         tranche2 = new Tranche(200, 2, "Tranche B");
//         tranche3 = new Tranche(200, 3, "Tranche A");
//         listOfTranche.addTranche(tranche1);
//         listOfTranche.addTranche(tranche2);
//         listOfTranche.addTranche(tranche3);
//     }

//     @Test
//     public void testConstructor() {
//         assertEquals(3, listOfTranche.size());
//     }

    // @Test
    // public void testOrder(){
    //     Tranche first = listOfTranches.get(0);
    //     assertEquals(1, first.getStartMonth());
    //     assertEquals("Tranche C", first.getTrancheId());

    //     Tranche second = listOfTranches.get(1);
    //     assertEquals(2, second.getStartMonth());
    //     assertEquals("Tranche B", second.getTrancheId());

    //     Tranche third = listOfTranches.get(2);
    //     assertEquals(3, third.getStartMonth());
    //     assertEquals("Tranche A", third.getTrancheId());
    // }

//     @Test
//     public void testAddPaymentOneTranche() {
//         listOfTranches.makePayment(100);
//     }

// }
