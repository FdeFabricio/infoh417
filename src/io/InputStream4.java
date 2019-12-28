package io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// TODO
public class InputStream4 implements InputStream {
    private FileReader fr;

    public InputStream4(int bufferSizeByte) {
    }

    public void open(String filePath) throws FileNotFoundException {

    }

    public String readln() throws IOException {
        return null;
    }

    public void seek(long pos) throws IOException {

    }

    public boolean end_of_stream() throws IOException {
        return false;
    }
}