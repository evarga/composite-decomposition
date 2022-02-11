package supplier;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.function.Supplier;
import pipeline.SeriesAdder;

public final class PIGenerator implements Supplier<BigDecimal> {
    private int precision = 500;
    private int n;

    public static double getApproximatePISlow(int n) {
        return 4 * SeriesAdder.sum(new GregoryLeibnizSeries(), n);
    }

    public static double getApproximatePIFast(int n) {
        return Math.sqrt(12) * SeriesAdder.sum(new MadhavaSeries(), n);
    }

    @Override
    public BigDecimal get() {
        n += 100;
        // A rudimentary heuristic when to increase precision. The idea is that the number of terms will always
        // be greater than the number of produced significant digits. A more careful approach would be to also
        // check for a potential arithmetic exception.
        if (n > precision)
            precision <<= 1;

        return BigDecimal.valueOf(12)
                .sqrt(new MathContext(precision))
                .multiply(SeriesAdder.sum(new ExactMadhavaSeries(precision), n));
    }
}
