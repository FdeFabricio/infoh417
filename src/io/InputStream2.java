package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class InputStream2 implements InputStream {
    private RandomAccessFile file;
    private BufferedReader br;

    public void open(String filePath) throws IOException {
        this.file = new RandomAccessFile(filePath, "r");
        this.br = new BufferedReader(new FileReader(this.file.getFD()));
    }

    public String readln() throws IOException {
        return br.readLine();
    }

    public void seek(long pos) throws IOException {
        this.file.seek(pos);
        this.br = new BufferedReader(new FileReader(this.file.getFD()));
    }

    public boolean end_of_stream() throws IOException {
        this.br.mark(1);
        int i = this.br.read();
        this.br.reset();
        return i == -1;
    }
}
