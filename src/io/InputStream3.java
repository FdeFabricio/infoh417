package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class InputStream3 implements InputStream {
    private RandomAccessFile raf;
    private BufferedReader br;
    private int bufferSize;

    public InputStream3(int bufferSizeBytes) {
        this.bufferSize = bufferSizeBytes;
    }

    public void open(String filePath) throws IOException {
        this.raf = new RandomAccessFile(filePath, "r");
        this.br = new BufferedReader(new FileReader(this.raf.getFD()), this.bufferSize);
    }

    public String readln() throws IOException {
        StringBuilder response = new StringBuilder();
        char r;
        do {
            r = (char) this.br.read();
            if (r != NEW_LINE)
                response.append(r);
        } while (r != NEW_LINE);
        return response.toString();
    }

    public void seek(long pos) throws IOException {
        this.raf.seek(pos);
        this.br = new BufferedReader(new FileReader(this.raf.getFD()));
    }

    public boolean end_of_stream() throws IOException {
        this.br.mark(1);
        int i = this.br.read();
        this.br.reset();
        return i == -1;
    }
}
