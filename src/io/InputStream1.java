package io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class InputStream1 implements InputStream {
    private RandomAccessFile fr;

    public void open(String f) throws FileNotFoundException {
        this.fr = new RandomAccessFile(f, "r");
    }

    public String readln() throws IOException {
        StringBuilder line = new StringBuilder();
        char c = ' ';
        while (true) {
            c = (char) fr.read();
            if (c == NEW_LINE) {
                break;
            }
            line.append(c);
        }
        return line.toString();
    }

    public void seek(long pos) throws IOException {
        this.fr.seek(pos);
    }

    public boolean end_of_stream() throws IOException {
        long previousPos = this.fr.getFilePointer();
        if (this.fr.read() == -1) {
            return true;
        } else {
            this.fr.seek(previousPos);
            return false;
        }
    }
}