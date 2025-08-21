package main;

import model.Fund;
import model.Tranche;
import model.World;

public class Main {
    public static void main(String[] args) {

        World excelWorld = new World();

        Tranche tranche1Apr = new Tranche(253, "Trench 1, April 2024"); // month 1
        Tranche tranche1May = new Tranche(720, "Trench 1, May 2024");
        Tranche tranche1Jun = new Tranche(38.5, "Trench 1, June 2024"); // month 3
        Tranche tranche1Jul = new Tranche(93.5, "Trench 1, July 2024");
        Tranche tranche1Aug = new Tranche(33, "Trench 1, August 2024");
        Tranche tranche1Nov = new Tranche(11, "Trench 1, November 2024"); // month 8
        Tranche tranche1Jan = new Tranche(11, "Trench 1, January 2025"); // month 10
        Tranche tranche3 = new Tranche(2321, "Trench 3, Jun 2025");
        Tranche tranche4 = new Tranche(1214, "Trench 4, Sep 2025");
        Tranche tranche5 = new Tranche(1320, "Trench 5, Dec 2025");
        Tranche tranche6 = new Tranche(1214, "Trench 6, Mar 2025");
        Tranche tranche7 = new Tranche(1320, "Trench 7, Jun 2025");
        Tranche tranche8 = new Tranche(1214, "Trench 8, Sep 2025");
        Tranche tranche9 = new Tranche(1320, "Trench 9, Dec 2025");
        Tranche tranche10 = new Tranche(1214, "Trench 10, Mar 2026");
        Tranche tranche11 = new Tranche(1214, "Trench 11, Jun 2026");
        Tranche tranche12 = new Tranche(1214, "Trench 12, Sep 2026");
        Tranche tranche13 = new Tranche(1214, "Trench 13, Dec 2026");
        Tranche tranche14 = new Tranche(1214, "Trench 14, Mar 2027");
        Tranche tranche15 = new Tranche(1214, "Trench 15, Jun 2027");
        Tranche tranche16 = new Tranche(1214, "Trench 16, Sep 2027");
        Tranche tranche17 = new Tranche(1514, "Trench 17, Dec 2027");

        // we assume month 1 is currently April 2024

        excelWorld.addTranche(tranche1Apr);
        excelWorld.nextMonth();

        // month 2
        excelWorld.addTranche(tranche1May);
        excelWorld.nextMonth();

        // month 3
        excelWorld.addTranche(tranche1Jun);
        excelWorld.nextMonth();

        // month 4
        excelWorld.addTranche(tranche1Jul);
        excelWorld.nextMonth();

        // month 5
        excelWorld.addTranche(tranche1Aug);

        for (int i = 0; i < 3; i++) {
            excelWorld.nextMonth();
        }

        // month 8, Nov 2024
        excelWorld.addTranche(tranche1Nov);

        for (int i = 0; i < 2; i++) {
            excelWorld.nextMonth();
        }

        // month 10, Jan 2025
        excelWorld.addTranche(tranche1Jan);

        for (int i = 0; i < 5; i++) {
            excelWorld.nextMonth();
        }

        // month 15, Jun 2025
        excelWorld.addTranche(tranche3);

        for (int i = 0; i < 3; i++) {
            excelWorld.nextMonth();
        }

        // month 18
        excelWorld.addTranche(tranche4);

        for (int i = 0; i < 3; i++) {
            excelWorld.nextMonth();
        }

        // month 21
        excelWorld.addTranche(tranche5);

        for (int i = 0; i < 3; i++) {
            excelWorld.nextMonth();
        }

        // month 24
        excelWorld.addTranche(tranche6);

        for (int i = 0; i < 3; i++) {
            excelWorld.nextMonth();
        }

        // month 27
        excelWorld.addTranche(tranche7);

        for (int i = 0; i < 3; i++) {
            excelWorld.nextMonth();
        }

        // month 30
        excelWorld.addTranche(tranche8);
        excelWorld.makePayment(334);
        for (int i = 0; i < 3; i++) {
            excelWorld.nextMonth();
        }

        // month 33
        excelWorld.addTranche(tranche9);
        excelWorld.makePayment(393);
        for (int i = 0; i < 3; i++) {
            excelWorld.nextMonth();
        }

        // month 36
        excelWorld.addTranche(tranche10);
        excelWorld.makePayment(451);
        for (int i = 0; i < 3; i++) {
            excelWorld.nextMonth();
        }

        // month 39
        excelWorld.addTranche(tranche11);
        excelWorld.makePayment(510);
        for (int i = 0; i < 3; i++) {
            excelWorld.nextMonth();
        }

        // month 42
        excelWorld.addTranche(tranche12);
        excelWorld.makePayment(796);
        for (int i = 0; i < 3; i++) {
            excelWorld.nextMonth();
        }

        // month 45
        excelWorld.addTranche(tranche13);
        excelWorld.makePayment(878);
        for (int i = 0; i < 3; i++) {
            excelWorld.nextMonth();
        }

        // month 48
        excelWorld.addTranche(tranche14);
        excelWorld.makePayment(960);
        for (int i = 0; i < 3; i++) {
            excelWorld.nextMonth();
        }

        // month 51
        excelWorld.addTranche(tranche15);
        excelWorld.makePayment(1042);
        for (int i = 0; i < 3; i++) {
            excelWorld.nextMonth();
        }

        // 54
        excelWorld.addTranche(tranche16);
        excelWorld.makePayment(1445);
        for (int i = 0; i < 3; i++) {
            excelWorld.nextMonth();
        }

        // 57
        excelWorld.addTranche(tranche17);
        excelWorld.makePayment(1550);
        for (int i = 0; i < 3; i++) {
            excelWorld.nextMonth();
        }

        // 60
        excelWorld.makePayment(1779);
        for (int i = 0; i < 3; i++) {
            excelWorld.nextMonth();
        }

        // 63
        excelWorld.makePayment(1782);
        for (int i = 0; i < 3; i++) {
            excelWorld.nextMonth();
        }

        // 66
        excelWorld.makePayment(1786);
        for (int i = 0; i < 3; i++) {
            excelWorld.nextMonth();
        }

        // 69
        excelWorld.makePayment(1789);
        for (int i = 0; i < 3; i++) {
            excelWorld.nextMonth();
        }

        // 72
        excelWorld.makePayment(1793);
        for (int i = 0; i < 3; i++) {
            excelWorld.nextMonth();
        }

        // 75
        excelWorld.makePayment(1796);
        for (int i = 0; i < 3; i++) {
            excelWorld.nextMonth();
        }

        // 78
        excelWorld.makePayment(2601); // BREAKING POINT
        System.out.println(excelWorld.getCurrentMonth());
        System.out.println(excelWorld.showAllPreferredReturns());

        World world2 = new World();

        Tranche testTranche2 = new Tranche(2321, "Trench 2, Jun 2025");
        world2.addTranche(testTranche2);

        for (int i = 0; i < 20; i++) {
            // System.out.println(i);
            world2.nextMonth();
        }
        // System.out.println(world2.getCurrentMonth());
        // System.out.println(world2.showAllPreferredReturns());

        World world3 = new World();
        Tranche testTranche3 = new Tranche(1214, "Trench 3, Sept 2025");
        world3.addTranche(testTranche3);
        for (int i = 0; i < 29; i++) {
            // System.out.println(i);
            world3.nextMonth();
        }
        // System.out.println(world3.getCurrentMonth());
        // System.out.println(world3.showAllPreferredReturns());
        // System.out.println(Fund.calculationMPR(1214, 29));
    }
}