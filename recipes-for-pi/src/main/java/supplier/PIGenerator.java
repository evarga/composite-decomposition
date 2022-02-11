package supplier;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.function.Supplier;
import pipeline.SeriesAdder;

public final class PIGenerator implements Supplier<BigDecimal> {
    private int precision = 100;
    private int n;
    private String prev = "3";

    public static double getApproximatePISlow(int n) {
        return 4 * SeriesAdder.sum(new GregoryLeibnizSeries(), n);
    }

    public static double getApproximatePIFast(int n) {
        return Math.sqrt(12) * SeriesAdder.sum(new MadhavaSeries(), n);
    }

    /**
        Generates a new value of Pi containing only significant digits. The code applies a very
        simple heuristic: first n digits of Pi are stable if they are the same between two iterations.

        @return significant digits of Pi according to the above rule.
     */
    @Override
    public BigDecimal get() {
        n += 100;
        // A rudimentary heuristic when to increase precision. Naturally, the number of terms will always
        // be greater than the number of produced significant digits.
        if (n > precision)
            precision <<= 1;

        var mc = new MathContext(precision);
        var curr = BigDecimal.valueOf(12)
                .sqrt(mc)
                .multiply(SeriesAdder.sum(new ExactMadhavaSeries(precision), n))
                .toString();

        // Find the longest prefix of digits between the previous and current values of Pi.
        var output = prev;
        for (var i = 0; i < prev.length(); i++)
            if (prev.charAt(i) != curr.charAt(i)) {
                output = curr.substring(0, i);
                break;
            }
        prev = curr;
        return new BigDecimal(output, mc);
    }
}
