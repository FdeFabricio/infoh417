package experiment;

import io.InputStream;
import io.InputStream2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MultiWayMergeTest {
    private String inputFile = "test/sort_file.txt";
    private String outputFile = "test/test_output4.txt";

    @BeforeEach
    void setUp() {
        File output = new File(outputFile);
        output.delete();
    }

    @Test
    void test() throws IOException {
        MultiWayMerge multiWayMerge = new MultiWayMerge();
        multiWayMerge.run(inputFile, outputFile, 3, 25, 3);

        InputStream inputStream = new InputStream2();
        inputStream.open(outputFile);
        assertEquals("4,video movie,a", inputStream.readln());
        assertEquals("1,movie,b", inputStream.readln());
        assertEquals("2,tv series,c", inputStream.readln());
        assertEquals("7,episode,d", inputStream.readln());
        assertEquals("5,tv mini series,e", inputStream.readln());
        assertEquals("3,tv movie,f", inputStream.readln());
        assertEquals("6,video game,g", inputStream.readln());
        assertTrue(inputStream.end_of_stream());
    }

    @Test
    void testInvalidK() {
        MultiWayMerge multiWayMerge = new MultiWayMerge();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> multiWayMerge.run(inputFile, outputFile, 4, 25, 3));
        assertEquals("ERROR: k is greater than the number of columns", exception.getMessage());
    }
}