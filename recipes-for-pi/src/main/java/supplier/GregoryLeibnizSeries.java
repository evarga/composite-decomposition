package supplier;

import java.util.function.DoubleSupplier;

/**
 * Generates an infinite sequence of terms of the Gregory-Leibniz series.
 */
public final class GregoryLeibnizSeries implements DoubleSupplier {
    private int k;

    @Override
    public double getAsDouble() {
        var term = Math.pow(-1, k) / (2 * k + 1);
        k++;
        return term;
    }
}
