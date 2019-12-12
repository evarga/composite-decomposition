package rs.exproit.source;

import java.util.function.DoubleSupplier;

/**
 * Generates an infinite sequence of terms of the Mādhava series.
 */
public final class MadhavaSeries implements DoubleSupplier {
    private int k;

    @Override
    public double getAsDouble() {
        final var term = Math.pow(-1, k) / (2 * k + 1) / Math.pow(3, k);
        k++;
        return term;
    }
}
