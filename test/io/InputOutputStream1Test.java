package io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InputOutputStream1Test {
    private InputStream inputStream;
    private OutputStream outputStream;

    @BeforeEach
    void setUp() {
        inputStream = new InputStream1();
        outputStream = new OutputStream1();
    }

    @Test
    void test() throws IOException {
        String outputFile = "test/test_file1.txt";
        outputStream.create(outputFile);
        outputStream.writeln( "Hello");
        outputStream.writeln( "World");
        outputStream.close();

        inputStream.open(outputFile);
        assertFalse(inputStream.end_of_stream());
        assertEquals(inputStream.readln(), "Hello");
        assertFalse(inputStream.end_of_stream());
        inputStream.seek(3);
        assertEquals(inputStream.readln(), "lo");
        assertFalse(inputStream.end_of_stream());
        assertEquals(inputStream.readln(), "World");
        assertTrue(inputStream.end_of_stream());
    }
}