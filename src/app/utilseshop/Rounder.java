package app.utilseshop;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Rounder {

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException(
              "Places only positive numbers, got " + places
        );

        BigDecimal bd = BigDecimal.valueOf(value);
        return  bd.setScale(places, RoundingMode.HALF_UP).doubleValue();
    }
//Test case
//    public static void main(String[] args) {
//        double test = round(45.56886886,-2);
//        System.out.println(test);
//
//    }
}


