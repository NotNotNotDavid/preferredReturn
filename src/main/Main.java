package main;

import model.Fund;
import model.Tranche;
import model.World;

public class Main {
    public static void main(String[] args) {
        

        World excelWorld = new World();

        Tranche tranche1 = new Tranche(826, null);
        double tranche1Principle = tranche1.getPrincipleRemaining();

        excelWorld.addTranche(tranche1);

        System.out.println((tranche1Principle * Math.pow((1 + Fund.MPR),1) - tranche1Principle));


        // System.out.println((tranche1Principle * Math.pow((1 + Fund.MPR),1) - tranche1Principle)*3);

        // double apple = 14.818258*3;
        // System.out.println(apple);

    }
}