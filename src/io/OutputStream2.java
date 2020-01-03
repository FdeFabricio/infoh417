package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputStream2 implements OutputStream {
    private BufferedWriter bw;

    public void create(String filePath) throws IOException {
        this.bw = new BufferedWriter(new FileWriter(filePath));
    }

    public void writeln(String line) throws IOException {
        this.bw.write(line + NEW_LINE);
    }

    public void close() throws IOException {
        this.bw.close();
    }
}
