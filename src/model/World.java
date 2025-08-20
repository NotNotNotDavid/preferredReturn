package model;

import java.util.ArrayList;

public class World {

    ListOfTranche listOfTranches;
    private int currentMonth;

    /*
     * REQUIES: none
     * MODIFIES: none
     * EFFECTS: Creates a new world with tranches
     */

    public World() {

    }

    /*
     * REQUIES: none
     * MODIFIES: tranche.principle 
     * EFFECTS: processes payments to individual tranches, FIFO style
     */

    public void addPayment(double amount) {

    }

    /*
     * REQUIES: none
     * MODIFIES: month
     * EFFECTS: changes the month by one
     */
    public void nextMonth() {

    }

    /*
     * REQUIES: none
     * MODIFIES: month
     * EFFECTS: changes the month by 12
     */
    public void nextYear() {

    }

    /*
     * REQUIES: none
     * MODIFIES: none
     * EFFECTS: shows the summary so far. I think this should be for the application and not in this class
     */

    public void showSummary() {

    }

    // Setters and Getters:
    public void setCurrentMonth(int month) {
        this.currentMonth = month;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

}
