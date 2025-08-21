package model;

public class Tranche {
    private double principleRemaining;
    private double preferredReturn; // TOTAL TRACKER
    private int monthCounter;
    private String trancheId;
    private boolean ifPaid;

    /*
     * REQUIES: none
     * MODIFIES: none
     * EFFECTS: Creates a new tranche
     */
    public Tranche(double principle, String trancheId) {
        this.principleRemaining = principle;
        this.trancheId = trancheId;
        this.monthCounter = 0;
    }

    public void addMonth() {
        monthCounter++;
    }

    /*
     * REQUIES: POSITIVE Number, 0 is okay
     * MODIFIES: principleRemaining, ifPaid, preferredReturn
     * EFFECTS: makes the payment for this trench, changes ifPaid to true if fully
     * paid off.
     * returns excess amounts.
     * 
     */
    public double makePayment(double amount) {
        double returnAmount = 0;

        if (amount >= principleRemaining) {
            findInterestForThePeriod();
            returnAmount = amount - principleRemaining;
            principleRemaining = 0;
            ifPaid = true;
            return returnAmount;
        } else {
            findInterestForThePeriod();
            principleRemaining -= amount;
            monthCounter = 0;
            return 0;
        }

    }

    /*
     * REQUIES: none
     * MODIFIES: preferredReturn
     * EFFECTS: helper method to add on to the preferredReturn
     * 
     */
    public void findInterestForThePeriod() {
        double accuredInterest = 0.0;
        accuredInterest = calculateInterest();
        preferredReturn += accuredInterest;
    }

    /*
     * REQUIES: none
     * MODIFIES: preferredReturn
     * EFFECTS: Calculates the total accumulated intrest for that month
     * 
     */
    public double calculateInterest(int currentMonth) {
        double interestAccumulated = 0;
        if (ifPaid) {
            interestAccumulated = 0;
            return interestAccumulated;
        } else {
            interestAccumulated = Fund.calculationMPR(principleRemaining, currentMonth);
            return interestAccumulated;
        }
    }

    /*
     * REQUIES: none
     * MODIFIES: preferredReturn
     * EFFECTS: Calculates the total accumulated intrest for that month
     * 
     */
    public double calculateInterest() {

        double interestAccumulated = 0;

        if (ifPaid) {
            interestAccumulated = 0;
            return interestAccumulated;
        } else {
            interestAccumulated = Fund.calculationMPR(principleRemaining, monthCounter);
            return interestAccumulated;
        }
    }

    // Getters
    public double getPrincipleRemaining() {
        return principleRemaining;
    }

    public double getPreferredReturn() {
        return preferredReturn;
    }

    public String getTrancheId() {
        return trancheId;
    }

    public int getMonthCounter() {
        return monthCounter;
    }

    public void setMonthCounter(int counter) {
        this.monthCounter = counter;
    }

    public boolean getifPaid() {
        return ifPaid;
    }

    public void setPrincipleRemaining(double amount) {
        this.principleRemaining = amount;
    }

    public void setIfPaid(boolean operation) {
        this.ifPaid = operation;
    }

}