package model;

public class Fund {
    public static final double ANNUAL_RATE = 0.08;
    public static final double MPR = (Math.pow((1 + ANNUAL_RATE),(1.0/12.0))) - 1;

    public static double calculationMPR (double principle, int months) {
        return principle * Math.pow((1 + Fund.MPR), months) - principle;
    }
}
