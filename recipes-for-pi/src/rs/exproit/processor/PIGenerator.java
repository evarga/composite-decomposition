package rs.exproit.processor;

import rs.exproit.source.ExactMadhavaSeries;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.function.Supplier;

public final class PIGenerator implements Supplier<BigDecimal> {
    private int precision = 500;
    private int n = 100;

    @Override
    public BigDecimal get() {
        final var pi = BigDecimal.valueOf(12)
                                 .sqrt(new MathContext(precision))
                                 .multiply(SeriesAdder.calculateSum(new ExactMadhavaSeries(precision), n));
        n += 100;
        precision = (n % 1000 == 0) ? 2 * precision : precision;
        return pi;
    }
}
