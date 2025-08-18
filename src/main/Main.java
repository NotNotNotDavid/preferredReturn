package main;

import model.ListOfTranche;

public class Main {

    public static void main(String[] args) {
        ListOfTranche portfolio = new ListOfTranche();
        portfolio.addTranche(100_000, 1, "Tranche A");
        portfolio.addTranche(50_000, 3, "Tranche B");
        // portfolio.addTranche(50_000, 3, "Tranche D");
        // portfolio.addTranche(30_000, 3, "Tranche B");
        // // Test Case 1: Partial payment (generates unpaid interest)
        // System.out.println("=== MONTH 3: $80,000 PAYMENT ===");
        portfolio.processPayment(portfolio, 80_000, 2);
        portfolio.processPayment(portfolio, 50_000, 4);

        

        System.out.println();
        System.out.println();

        // System.out.println("\n=== MONTH 5: $70,000 PAYMENT ===");
        // portfolio.processPayment(portfolio, 30_000, 5);

        portfolio.displayInformation(portfolio);


        // System.out.println("\n=== MONTH 6: $50,000 PAYMENT ===");
        // processPayment(portfolio, 50_000, 6);
    }
}