package experiment;

import io.InputStream2;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CombinedReadWriteTest {
    private List<String> inputFiles = Arrays.asList("test/rw_1.txt", "test/rw_2.txt", "test/rw_3.txt");
    private String outputFile = "test/rw_o.txt";

    @Test
    void test() throws IOException {
        CombinedReadWrite combinedRW = new CombinedReadWrite();
        combinedRW.run(inputFiles, outputFile, 2, 2);

        InputStream2 input = new InputStream2();
        input.open(outputFile);
        assertEquals("one 1", input.readln());
        assertEquals("two 2", input.readln());
        assertEquals("three 3", input.readln());
        assertEquals("four 4", input.readln());
        assertEquals("five 5", input.readln());
        assertEquals("six 6", input.readln());
        assertEquals("seven 7", input.readln());
        assertTrue(input.end_of_stream());
    }

}