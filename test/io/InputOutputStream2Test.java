package io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InputOutputStream2Test {
    private InputStream inputStream;
    private OutputStream outputStream;

    @BeforeEach
    void setUp() {
        inputStream = new InputStream2();
        outputStream = new OutputStream2();
    }

    @Test
    void test() throws IOException {
        String outputFile = "test/test_file2.txt";
        outputStream.create(outputFile);
        outputStream.writeln( "Hello");
        outputStream.writeln( "World");
        outputStream.close();

        inputStream.open(outputFile);
        assertFalse(inputStream.end_of_stream());
        assertEquals("Hello", inputStream.readln());
        assertFalse(inputStream.end_of_stream());
        inputStream.seek(3);
        assertEquals("lo", inputStream.readln());
        assertFalse(inputStream.end_of_stream());
        assertEquals("World", inputStream.readln());
        assertTrue(inputStream.end_of_stream());
    }
}