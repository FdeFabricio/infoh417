package io;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class InputOutputStream4Test {
    String filePath = "test/test_file4.txt";

    @Test
    void testInputSmall() throws IOException {
        inputTest(3);
    }

    @Test
    void testInputMedium() throws IOException {
        inputTest(10);
    }

    @Test
    void testInputLarge() throws IOException {
        inputTest(100);
    }

    @Test
    void testOutputSmall() throws IOException {
        outputTest(3);
    }

    @Test
    void testOutputMedium() throws IOException {
        outputTest(10);
    }

    @Test
    void testOutputLarge() throws IOException {
        outputTest(100);
    }

    void inputTest(int bufferSize) throws IOException {
        this.setupForInput();
        InputStream4 inputStream = new InputStream4(bufferSize);
        inputStream.open(filePath);
        assertFalse(inputStream.end_of_stream());
        assertEquals("Bonjour", inputStream.readln());
        assertFalse(inputStream.end_of_stream());
        inputStream.seek(3);
        assertEquals("jour", inputStream.readln());
        assertFalse(inputStream.end_of_stream());
        assertEquals("Monde", inputStream.readln());
        assertTrue(inputStream.end_of_stream());
    }

    void setupForInput() throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write("Bonjour\n");
        fw.write("Monde\n");
        fw.close();
    }

    void outputTest(int bufferSize) throws IOException {
        OutputStream4 outputStream = new OutputStream4(bufferSize);
        outputStream.create(this.filePath);
        outputStream.writeln("Hallo");
        outputStream.writeln("Wereld");
        outputStream.close();

        BufferedReader br = new BufferedReader(new FileReader(new File(this.filePath)));
        assertEquals("Hallo", br.readLine());
        assertEquals("Wereld", br.readLine());
        assertEquals(-1, br.read());
        br.close();
    }
}