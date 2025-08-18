package model;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListOfTranche {
    private final List<Tranche> tranches = new ArrayList<>();
    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();

    public void addTranche(double principal, int startMonth, String trancheId) {
        tranches.add(new Tranche(principal, startMonth, trancheId));
    }

    /**
     * Processes payment in FIFO order
     * @return list of affected tranches
     */
    public List<PaymentResult> processPayment(double amount, int currentMonth) {
        List<PaymentResult> results = new ArrayList<>();
        tranches.sort(Comparator.comparingInt(Tranche::getStartMonth));
        
        for (Tranche tranche : tranches) {
            if (amount <= 0) break;
            
            double totalAmountPaid = 0;
            double initialAmount = amount;
            amount = tranche.processPayment(amount, currentMonth);
            double paid = initialAmount - amount;
            
            if (paid > 0) {
                results.add(new PaymentResult(
                    tranche.getTrancheId(),
                    totalAmountPaid += paid,
                    tranche.getPrincipalRemaining(),
                    tranche.getPreferredReturn()
                ));
            }
        }
        return results;
    }

    
    public void processPayment(ListOfTranche portfolio, double amount, int month) {
        System.out.println("----------------");
        System.out.println("Processing " + currency.format(amount) + " in month " + month);
        System.out.println("----------------");
        System.out.println();
        var results = portfolio.processPayment(amount, month);
        
        if (results.isEmpty()) {
            System.out.println("  No tranches affected");
            return;
        }
        
        System.out.println("  Tranche   | Amount Remaining  | Preferred Return");
        System.out.println("  ----------|-------------------|-----------------");
        
        for (var result : results) {
            System.out.printf("  %-9s | %-10s | %-15s%n",
                result.trancheId,
                currency.format(result.remainingPrincipal),
                currency.format(result.unpaidInterest));
        }
    }

    public List<Tranche> getTranches() {
        return tranches;
    }

    public void displayInformation(ListOfTranche portfolio) {
        // Print table header
        System.out.println("┌───────────┬─────────── ─┬────────────┬─────────────────┐");
        System.out.println("│ Tranche   │ Principal   │ Remaining  │ Preferred Return│");
        System.out.println("├───────────┼─────────── ─┼────────────┼─────────────────┤");
        
        // Print each tranche's information
        for (Tranche tranche : portfolio.getTranches()) {
            System.out.printf(
                "│ %-9s │ %-11s │ %-10s │ %-15s │%n",
                tranche.getTrancheId(),
                formatCurrency(tranche.getTotalPrinciple()),
                formatCurrency(tranche.getPrincipalRemaining()),
                formatCurrency(tranche.getPreferredReturn()) 
            );
        }
        
        // Print table footer
        System.out.println("└───────────┴─────────── ─┴────────────┴─────────────────┘");
        
        // // Print summary
        // System.out.printf("%nMonth: %d | Total Preferred Return Due: %s%n%n",
        //     month,
        //     formatCurrency(portfolio.getTotalHurdleAmount())
        // );
    }
    
    private String formatCurrency(double amount) {
        return NumberFormat.getCurrencyInstance().format(amount);
    }

}