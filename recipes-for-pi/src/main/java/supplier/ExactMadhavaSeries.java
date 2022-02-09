package supplier;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.function.Supplier;

/**
 * Generates an exact infinite sequence of terms of the Mādhava series.
 */
public final class ExactMadhavaSeries implements Supplier<BigDecimal> {
    private final MathContext mc;
    private long k;

    /**
     * Constructs a new instance of this generator.
     *
     * @param precision the required precision for floating point arithmetic.
     */
    public ExactMadhavaSeries(int precision) {
        mc = new MathContext(precision);
    }

    @Override
    public BigDecimal get() {
        var term = BigDecimal.valueOf(Math.pow(-1, k))
                .divide(BigDecimal.valueOf(2 * k + 1), mc)
                .divide(BigDecimal.valueOf(3).pow((int) k, mc), mc);
        k++;
        return term;
    }
}
