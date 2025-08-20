// package model;

// import java.util.ArrayList;
// import java.util.Comparator;
// import java.util.LinkedList;
// import java.util.List;
// import java.util.PriorityQueue;
// import java.util.Queue;

// public class ListOfTranche {

//     private ArrayList<Tranche> activeTrancheQueue; // For FIFO processing
//     private ArrayList<Tranche> trancheList; // For tracking all tranches

//     /*
//      * REQUIES: none
//      * MODIFIES: none
//      * EFFECTS: Creates a new list of tranches
//      */
//     public ListOfTranche() {
//         // trancheList = new ArrayList<>(Comparator.comparingInt(t ->
//         // t.getStartMonth()));
//         // activeTrancheQueue = new ArrayList<>(Comparator.comparingInt(t ->
//         // t.getStartMonth()));
//         trancheList = new ArrayList<>();
//     }

//     /*
//      * REQUIES: none
//      * MODIFIES: trancheList
//      * EFFECTS: Creates a new list of tranches
//      */
//     public void addTranche(Tranche tranche) {
//         trancheList.add(tranche);
//     }

//     /*
//      * REQUIES: requires non-negative non-zero amount
//      * MODIFIES: trancheList
//      * EFFECTS: Makes payments into the next tranche in FIFO order
//      */
//     public void makePayment(double amount) {

//         double remainingAmount = amount;

//         while (remainingAmount > 0 && !trancheList.isEmpty()) {
//             for (Tranche tranche : trancheList) {
//                 double excess = tranche.makePayment(remainingAmount);
//                 if (excess > 0) {
//                     // This tranche is fully paid, use excess for next tranche
//                     remainingAmount = excess;
//                 } else {
//                     // Entire remaining amount was used by this tranche
//                     remainingAmount = 0;
//                     break;
//                 }
//             }
//         }
//     }

// }