package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputStream3 implements OutputStream {
    private BufferedWriter bw;
    private int bufferSize;

    public OutputStream3(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public void create(String filePath) throws IOException {
        bw = new BufferedWriter(new FileWriter(filePath), bufferSize);
    }

    public void writeln(String line) throws IOException {
        for (char ch : line.toCharArray()) {
            bw.write(ch);
        }
        bw.write(NEW_LINE);
    }

    public void close() throws IOException {
        bw.close();
    }
}
