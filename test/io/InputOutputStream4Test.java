package io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InputOutputStream4Test {
    private InputStream4 inputStream;
    private OutputStream4 outputStream;

    @BeforeEach
    void setUp() {
        int bufferSize = 50;
        inputStream = new InputStream4(bufferSize);
        outputStream = new OutputStream4(bufferSize);
    }

    @Test
    void test() throws IOException {
        String outputFile = "test/test_file2.txt";
        outputStream.create(outputFile);
        outputStream.writeln( "World");
        outputStream.writeln( "Hello");
        outputStream.close();

        inputStream.open(outputFile);
        assertFalse(inputStream.end_of_stream());
        assertEquals(inputStream.readln(), "World");
        assertFalse(inputStream.end_of_stream());
        inputStream.seek(2);
        assertEquals(inputStream.readln(), "rld");
        assertFalse(inputStream.end_of_stream());
        assertEquals(inputStream.readln(), "Hello");
        assertTrue(inputStream.end_of_stream()); // TODO fix trailing NULL characters
    }
}