package experiment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SequentialReadingTest {
    private SequentialReading sequentialReading;
    private String inputFile = "test/test_file1.txt";

    @BeforeEach
    void setUp() {
        sequentialReading = new SequentialReading();
    }

    @Test
    void testLength1() throws IOException {
        assertEquals(sequentialReading.run(1, inputFile), 10);
    }

    @Test
    void testLength2() throws IOException {
        assertEquals(sequentialReading.run(2, inputFile), 10);
    }

    @Test
    void testLength3() throws IOException {
        assertEquals(sequentialReading.run(3, inputFile, 1), 10);
        assertEquals(sequentialReading.run(3, inputFile, 3), 10);
        assertEquals(sequentialReading.run(3, inputFile, 8000), 10);
    }

    @Test
    void testLength4() throws IOException {
        assertEquals(sequentialReading.run(4,inputFile, 1), 10);
        assertEquals(sequentialReading.run(4,inputFile, 3), 10);
        assertEquals(sequentialReading.run(4,inputFile, 8000), 10);
    }
}