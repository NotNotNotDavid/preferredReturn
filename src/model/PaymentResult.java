package model;


public class PaymentResult {
    public final String trancheId;
    public final double amountPaid;
    public final double remainingPrincipal;
    public final double unpaidInterest;
    
    public PaymentResult(String trancheId, double amountPaid, 
                      double remainingPrincipal, double unpaidInterest) {
        this.trancheId = trancheId;
        this.amountPaid = amountPaid;
        this.remainingPrincipal = remainingPrincipal;
        this.unpaidInterest = unpaidInterest;
    }
}
