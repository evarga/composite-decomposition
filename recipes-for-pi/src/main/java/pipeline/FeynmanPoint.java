package pipeline;

import supplier.PIGenerator;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

/**
 * Finds the Feynman point in decimals of Pi by generating as many Pis as necessary.
 */
public final class FeynmanPoint {
    public record Result(String pi, long numTrials) {};

    /*
        We must be careful not to prematurely conclude that we have found a Feynman point. Namely,
        only significant digits of Pi must be taken into account. Here, the code applies a very
        simple heuristic: first n digits of Pi are stable if they are the same between two iterations.
        So, if the current value of Pi does contain a sequence of 999999 and if all digits up to and
        including this sequence are also the same in the previous value of Pi, then we announce success.
     */
    private static Optional<String> compareAndGet(BigDecimal prev, BigDecimal curr) {
        var s = curr.toString();
        var i = s.indexOf("999999");
        try {
            if (i != -1 && prev.toString().substring(0, i + 6).equals(s.substring(0, i + 6)))
                return Optional.of(s.substring(0, i + 6));
        } catch (IndexOutOfBoundsException ignored) {}
        return Optional.empty();
    }

    /**
     * Instantiates the stream pipeline to produce Pis until reaching the Feynman point.
     *
     * @return the first value of Pi containing the Feynman point together with the number of trials.
     */
    public static Result findFeynmanPoint() {
        /*
           Pipelines are usually free of side effects, but this one uses peek to preserve the current value for the
           next "iteration." This is an unconventional usage of Java Streams, so you must extensively document your
           decision. Furthermore, this approach works only for sequential streams. If you need to refer back to
           previously seen elements, then iterating over indexes of a collection is a better option, if attainable.
         */
        AtomicReference<BigDecimal> prev = new AtomicReference<>(BigDecimal.ZERO);
        var numTrials = Stream.generate(new PIGenerator())
                .takeWhile(curr -> FeynmanPoint.compareAndGet(prev.get(), curr).isEmpty())
                .peek(prev::set)
                .count();
        // Calling 'get' below is safe, since by the time we reach this line, prev is guaranteed to contain
        // the desired value of Pi with enough significant digits. The line below signals to IntelliJ that we
        // know what is going on.
        //noinspection OptionalGetWithoutIsPresent
        return new Result(FeynmanPoint.compareAndGet(prev.get(), prev.get()).get() + "...", numTrials);
    }
}
