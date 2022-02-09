package pipeline;

import supplier.PIGenerator;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

/**
 * Finds the Feynman point in decimals of Pi by generating as many Pis as necessary.
 */
public final class FeynmanPoint {
    public record Result(String pi, long numTrials) {};

    /**
     * Instantiates the stream pipeline to produce Pis until reaching the Feynman point.
     *
     * @return the first value of Pi containing the Feynman point together with the number of trials.
     */
    public static Result findFeynmanPoint() {
        // This is a general pattern applicable for parallel streams, too.
        var feynmanPoint = new AtomicReference<BigDecimal>();
        var numTrials = Stream.generate(new PIGenerator())
                .peek(feynmanPoint::set)
                .takeWhile(pi -> !pi.toString().contains("999999"))
                .count();
        var output = feynmanPoint.get().toString();
        return new Result(output.substring(0, output.indexOf("999999") + 6) + "...", numTrials);
    }
}
