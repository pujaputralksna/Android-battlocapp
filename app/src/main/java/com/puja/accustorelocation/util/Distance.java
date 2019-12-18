package com.puja.accustorelocation.util;

public class Distance {
    public static double calculate(double lat1, double lng1, double lat2, double lng2) {
        double p = 0.017453292519943295;
        double a = 0.5 - Math.cos((lat1 - lat2) * p)/2 + Math.cos(lat1*p) *
                Math.cos(lat2 * p) * (1 - Math.cos((lng2 - lng1) * p))/2;

        return 12742 * Math.asin(Math.sqrt(a));
    }
}
