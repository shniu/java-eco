package io.github.shniu.arts.algothrim.search;

public class SquareRoot {

    public static double sqrt(double x, double precision) {
        if (x < 0) return Double.NaN;
        if (x == 0) return 0;

        double low = 0;
        double high = x;
        if (x < 1) {
            low = x;
            high = 1;
        }

        double mid = low + (high - low) / 2;
        while (high - low > precision) {
            if (mid  > x / mid) {
                high = mid;
            } else if (mid < x / mid) {
                low = mid;
            } else {
                return mid;
            }

            mid = low + (high - low) / 2;
        }

        return mid;
    }
}
