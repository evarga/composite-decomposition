package rs.exproit;

import rs.exproit.processor.PIGenerator;
import rs.exproit.processor.SeriesAdder;
import rs.exproit.source.GregoryLeibnizSeries;
import rs.exproit.source.MadhavaSeries;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

/**
 * The main application class for calculating Π using various recipes.
 */
public final class App {
    /**
     * Prints the result depending on the choice given as a command line argument.
     *
     * @param args the arguments to this program. The first case-insensitive argument can be a, b, or c
     *             (see the Exploration 4.2 at http://computingbook.org/Problems.pdf). The second argument is an
     *             integer that specifies the number of terms to sum (doesn't apply for task c).
     */
    public static void main(final String[] args) {
        if (args == null || !args[0].equals("c") && args.length != 2 || args[0].equals("c") && args.length != 1) {
            System.err.println("Usage: java rs.exproit.App (a|b|c) [n]");
            System.exit(1);
        }

        var option = args[0].toLowerCase();
        if (option.equals("a"))
            System.out.printf(
                    "\u03c0 = %.16f\n",
                    4 * SeriesAdder.calculateSum(new GregoryLeibnizSeries(), Integer.parseInt(args[1])));
        else if (option.equals("b"))
            System.out.printf(
                    "\u03c0 = %.16f\n",
                    Math.sqrt(12) * SeriesAdder.calculateSum(new MadhavaSeries(), Integer.parseInt(args[1])));
        else if (option.equals("c")) {
            final AtomicReference<BigDecimal> feynmanPI = new AtomicReference<>();
            final var numTrials = Stream.generate(new PIGenerator())
                                        .peek(feynmanPI::set)
                                        .takeWhile(pi -> !pi.toString().contains("999999"))
                                        .count();
            System.out.printf("\u03c0 = %s\nNumber of trials = %d\n", feynmanPI.get(), numTrials);
        }
    }
}
