package pipeline;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * Sums together the first N number of terms from the input series.
 */
public final class SeriesAdder {
    private static void checkArguments(Object terms, int n) {
        Objects.requireNonNull(terms);
        if (n < 1)
            throw new IllegalArgumentException("You cannot add up less than a single element!");
    }

    /**
     * Returns the sum of the first given number of terms from the input sequence.
     *
     * @param terms the infinite sequence generator.
     * @param n the number of terms to generate.
     * @return the sum of the first n elements in the series.
     * @throws NullPointerException if the input sequence is null.
     * @throws IllegalArgumentException if n is not a natural number.
     */
    public static double sum(DoubleSupplier terms, int n) {
        checkArguments(terms, n);
        return DoubleStream.generate(terms).limit(n).sum();
    }

    /**
     * Returns the sum of the first given number of terms from the exact input sequence.
     *
     * @param terms the exact infinite sequence generator.
     * @param n the number of terms to generate.
     * @return the sum of the first n elements in the series.
     * @throws NullPointerException if the input sequence is null.
     * @throws IllegalArgumentException if n is not a natural number.
     */
    public static BigDecimal sum(Supplier<BigDecimal> terms, int n) {
        checkArguments(terms, n);
        //noinspection OptionalGetWithoutIsPresent
        return Stream.generate(terms).limit(n).reduce(BigDecimal::add).get();
    }
}
