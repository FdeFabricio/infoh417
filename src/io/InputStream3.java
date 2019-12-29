package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class InputStream3 implements InputStream {
    private RandomAccessFile raf;
    private FileReader fr;
    private BufferedReader br;
    private int bufferSize;

    public InputStream3(int bufferSizeBytes) {
        this.bufferSize = bufferSizeBytes * 8;
    }

    public void open(String filePath) throws IOException {
        this.raf = new RandomAccessFile(filePath, "r");
        this.fr = new FileReader(raf.getFD());
        br = new BufferedReader(fr, bufferSize);
    }

    public String readln() throws IOException {
        StringBuilder response = new StringBuilder();
        char r;

        do {
            r = (char) br.read();
            if (r != '\n')
                response.append(r);
        } while (r != '\n');
        return response.toString();
    }

    public void seek(long pos) throws IOException {
        raf.seek(pos);
        this.br = new BufferedReader(fr);
    }

    public boolean end_of_stream() throws IOException {
        br.mark(1);
        int i = br.read();
        br.reset();
        return i < 0;
    }
}
