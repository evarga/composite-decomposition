package pipeline;

import supplier.PIGenerator;

import java.util.stream.Stream;

/**
 * Finds the Feynman point in decimals of Pi by generating as many Pis as necessary.
 */
public final class FeynmanPoint {
    /**
     * Instantiates the stream pipeline to produce Pis until reaching the Feynman point.
     *
     * @return the printout of the value of Pi containing the Feynman point.
     */
    public static String findFeynmanPoint() {
        var feynmanPoint = Stream.generate(new PIGenerator())
                // Uncomment the next line for debugging purposes to see what Pis were generated.
                // .peek(System.out::println)
                .dropWhile(pi -> !pi.toString().contains("999999"))
                .findFirst()
                .orElseThrow()
                .toString();
        return feynmanPoint.substring(0, feynmanPoint.indexOf("999999") + 6) + "...";
    }
}
