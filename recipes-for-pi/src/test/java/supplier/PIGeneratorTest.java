package supplier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PIGeneratorTest {
    @Test
    void getApproximatePISlow() {
        assertEquals(3.1414926535900434d, PIGenerator.getApproximatePISlow(10000));
    }

    @Test
    void getApproximatePIFast() {
        assertEquals(3.141592653595635d, PIGenerator.getApproximatePIFast(21));
    }
}