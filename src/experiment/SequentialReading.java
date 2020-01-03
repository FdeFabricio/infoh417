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

    public int run(int streamType, String filePath) throws IOException {
        switch (streamType) {
            case 1:
                return this.length(new InputStream1(), filePath);
            case 2:
                return this.length(new InputStream2(), filePath);
            default:
                throw new IllegalArgumentException("wrong argument");
        }
    }

    public int run(int streamType, String filePath, int bufferSize) throws IOException {
        switch (streamType) {
            case 3:
                return this.length(new InputStream3(bufferSize), filePath);
            case 4:
                return this.length(new InputStream4(bufferSize), filePath);
            default:
                throw new IllegalArgumentException("wrong argument");
        }
    }
}
