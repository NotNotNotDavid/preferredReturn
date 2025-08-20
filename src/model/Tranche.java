package model;

public class Tranche {
    private double principleRemaining;
    private double preferredReturn; // TOTAL TRACKER
    private int startMonth;
    private String trancheId;
    private static final double ANNUAL_RATE = 0.08;
    private static final double MONTHLY_RATE = ANNUAL_RATE / 12;
    private boolean ifPaid;

    /*
     * REQUIES: none
     * MODIFIES: none
     * EFFECTS: Creates a new tranche
     */
    public Tranche(double principle, int startMonth, String trancheId) {
        this.principleRemaining = principle;
        this.startMonth = startMonth;
        this.trancheId = trancheId;
    }


    /*
     * REQUIES: POSITIVE NON-ZERO NUMBER
     * MODIFIES: principleRemaining, ifPaid
     * EFFECTS: makes the payment for this trench, changes ifPaid to true if fully paid off.
     *          returns excess amounts
     * 
     */
    public double makePayment(double amount) {
        if (principleRemaining <= amount) {
            double returnAmount = amount - principleRemaining;
            principleRemaining = 0;
            ifPaid = true;
            return returnAmount;
        }
        else {
            principleRemaining -= amount;
            return 0;
        }

    }

    /*
     * REQUIES: none
     * MODIFIES: preferredReturn
     * EFFECTS: Calculates the total accumulated intrest for that month and adds to pR if not paid
     * 
     */
    public double calculateInterest(int currentMonth) {
        double interestAccumulated = 0;
        if (ifPaid) {
            interestAccumulated = 0;
            return interestAccumulated;
        } else {
            interestAccumulated = principleRemaining * Math.pow((1 + MONTHLY_RATE), currentMonth) - principleRemaining;
            preferredReturn += interestAccumulated;
            return interestAccumulated;
        }
    }

    /*
     * REQUIES: none
     * MODIFIES: preferredReturn
     * EFFECTS: Calculates the total accumulated intrest for that month and adds to pR if not paid
     * 
     */
    public double calculateInterest() {

        double interestAccumulated = 0;
        if (ifPaid) {
            interestAccumulated = 0;
            return interestAccumulated;
        } else {
            interestAccumulated = principleRemaining * (1 + MONTHLY_RATE) - principleRemaining;
            preferredReturn += interestAccumulated;
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

    public int getStartMonth() {
        return startMonth;
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