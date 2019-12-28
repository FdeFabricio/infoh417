package experiment;

import io.*;

import java.io.IOException;

public class SequentialReading {
    private int length(InputStream input, String filePath) throws IOException {
        input.open(filePath);
        int sum = 0;
        while (!input.end_of_stream()) {
            String line = input.readln();
            sum += line.length();
        }
        return sum;
    }

    public int length1(String f) throws IOException {
        return this.length(new InputStream1(), f);
    }

    public int length2(String f) throws IOException {
        return this.length(new InputStream2(), f);
    }

    public int length3(String f, int b) throws IOException {
        return this.length(new InputStream3(b), f);
    }

    public int length4(String f, int b) throws IOException {
        return this.length(new InputStream4(b), f);
    }
}
