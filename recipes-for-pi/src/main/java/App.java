import pipeline.FeynmanPoint;
import supplier.PIGenerator;

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
    public static void main(String[] args) {
        if (args == null
                || args.length == 0
                || !args[0].equals("c") && args.length != 2
                || args[0].equals("c") && args.length != 1) {
            System.err.println("Usage: java App (a n|b n|c)");
            System.exit(0x1);
        }

        switch (args[0].toLowerCase()) {
            case "a" -> System.out.printf("\u03c0 = %.16f\n",
                    PIGenerator.getApproximatePISlow(Integer.parseInt(args[1])));

            case "b" -> System.out.printf("\u03c0 = %.16f\n",
                    PIGenerator.getApproximatePIFast(Integer.parseInt(args[1])));

            default -> {
                var result = FeynmanPoint.findFeynmanPoint();
                System.out.printf("\u03c0 = %s\nNumber of trials = %d\n", result.pi(), result.numTrials());
            }
        }
    }
}
