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

    }


    /*
     * REQUIES: POSITIVE NON-ZERO NUMBER
     * MODIFIES: principleRemaining, ifPaid
     * EFFECTS: Calculates the total accumulated intrest for that month and adds to pR
     * 
     */
    public double makePayment(double amount) {
        return 0;
    }

    /*
     * REQUIES: none
     * MODIFIES: preferredReturn
     * EFFECTS: Calculates the total accumulated intrest for that month and adds to pR
     * 
     */
    public double calculateInterest(int currentMonth) {
        return 0;
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