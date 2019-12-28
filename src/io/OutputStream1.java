package io;

import java.io.FileWriter;
import java.io.IOException;

public class OutputStream1 implements OutputStream {
    private FileWriter fw;

    public void create(String filePath) throws IOException {
        fw = new FileWriter(filePath);
    }

    public void writeln(String line) throws IOException {
        for (char c : line.toCharArray()) {
            fw.write(c);
        }
        fw.write(NEW_LINE);
    }

    public void close() throws IOException {
        fw.close();
    }
}
