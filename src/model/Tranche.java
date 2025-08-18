package model;

public class Tranche {
    private double totalPrincipal = 0;
    private boolean ifPaid;
    private double principalRemaining; 
    private double preferredReturn; // TOTAL!!
    private final int startMonth;
    private final String trancheId;
    private static final double ANNUAL_RATE = 0.08;
    private static final double MONTHLY_RATE = ANNUAL_RATE / 12;

    // Creates a new Tranche
    public Tranche(double principal, int startMonth, String trancheId) {
        this.principalRemaining = principal;
        this.totalPrincipal = principal;
        this.startMonth = startMonth;
        this.trancheId = trancheId;
        this.preferredReturn = 0;
        this.ifPaid = false;
    }

    /**
     *  REQUIRES: NON-NEGATIVE NON-ZERO NUMBERS
     *  MODIFIES: principleRemaining, preferredReturn, ifPaid
     *  EFFECTS: See if the amount is > 0, if not, decrease principleRemaining by the amount, increase preferredReturn by the monthly interest
     */
    public double processPayment(double amount, int currentMonth) {
        if (principalRemaining <= 0) {
            return amount;
        }

        if (ifPaid) {
            return amount;
        }
        
        // Calculate interest since last payment
        int monthsActive = currentMonth - startMonth;
    
        // Counts the preferredReturn no matter what, follows the excel formula
        preferredReturn += principalRemaining * Math.pow((1 + MONTHLY_RATE), monthsActive) - principalRemaining;
        
        double paymentApplied = Math.min(amount, principalRemaining);
        principalRemaining -= paymentApplied;
        return amount - paymentApplied;
    }

    // Getters
    public double getTotalPrinciple() { return totalPrincipal; }
    public double getPrincipalRemaining() { return principalRemaining; }
    public double getPreferredReturn() { return preferredReturn; }
    public String getTrancheId() { return trancheId; }
    public int getStartMonth() { return startMonth; }
}