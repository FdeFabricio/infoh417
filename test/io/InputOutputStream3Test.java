package io;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InputOutputStream3Test {
    private String outputFile = "test/test_file3.txt";
    private InputStream3 inputStream;
    private OutputStream3 outputStream;

    @Test
    void testSmall() throws IOException {
        inputStream = new InputStream3(3);
        outputStream = new OutputStream3(3);

        outputStream.create(outputFile);
        outputStream.writeln("Hello");
        outputStream.writeln("World");
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

    @Test
    void testLarge() throws IOException {
        inputStream = new InputStream3(300);
        outputStream = new OutputStream3(300);

        outputStream.create(outputFile);
        outputStream.writeln("Hello");
        outputStream.writeln("World");
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
