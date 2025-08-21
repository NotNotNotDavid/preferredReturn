package model;

import java.util.ArrayList;

public class World {

    ArrayList<Tranche> trancheList;
    private int currentMonth;

    /*
     * REQUIES: none
     * MODIFIES: none
     * EFFECTS: Creates a new world with tranches
     */

    public World() {
        trancheList = new ArrayList<>();
        currentMonth = 1;

    }

    /*
     * REQUIES: none
     * MODIFIES: none
     * EFFECTS: adds new tranche to list
     */

    public void addTranche(Tranche tranche) {
        trancheList.add(tranche);
    }

    /*
     * REQUIES: requires non-negative non-zero amount
     * MODIFIES: trancheList
     * EFFECTS: Makes payments into the next tranche in FIFO order
     */
    public void makePayment(double amount) {

        double remainingAmount = amount;
        for (Tranche tranche : trancheList) {
            if (!tranche.getifPaid()) {
                double excess = tranche.makePayment(remainingAmount);
                if (excess > 0) {
                    remainingAmount = excess;
                } else {
                    remainingAmount = 0;
                    break;
                }
            } else {
                continue;
            }
        }
    }

    /*
     * REQUIES: none
     * MODIFIES: month
     * EFFECTS: changes the month by one
     */
    public void nextMonth() {
        currentMonth++;
        for (Tranche tranche : trancheList) {
            // tranche.calculateInterest(); // NOT YET
            tranche.addMonth();
        }

    }

    /*
     * REQUIES: none
     * MODIFIES: month
     * EFFECTS: changes the month by 12
     */
    public void nextYear() {
        for (int i = 0; i < 12; i++) {
            currentMonth++;
            for (Tranche tranche : trancheList) {
                // tranche.calculateInterest(); // NOT YET
                tranche.addMonth();
            }
        }
    }

    /*
     * REQUIES: none
     * MODIFIES: none
     * EFFECTS: shows all the preferredReturns from all the trenches so far.
     */

    public double showAllPreferredReturns() {
        double total = 0;
        for (Tranche tranche : trancheList) {
            tranche.makePayment(0);
            total += tranche.getPreferredReturn();
        }
        return total;
    }

    // Setters and Getters:
    public void setCurrentMonth(int month) {
        this.currentMonth = month;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

}
