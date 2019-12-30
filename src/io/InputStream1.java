package io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class InputStream1 implements InputStream {
    private RandomAccessFile file;

    public void open(String filePath) throws FileNotFoundException {
        this.file = new RandomAccessFile(filePath, "r");
    }

    public String readln() throws IOException {
        StringBuilder line = new StringBuilder();
        char c;
        while (true) {
            c = (char) this.file.read();
            if (c == NEW_LINE) {
                break;
            }
            line.append(c);
        }
        return line.toString();
    }

    public void seek(long pos) throws IOException {
        this.file.seek(pos);
    }

    public boolean end_of_stream() throws IOException {
        long previousPos = this.file.getFilePointer();
        if (this.file.read() == -1) {
            return true;
        } else {
            this.file.seek(previousPos);
            return false;
        }
    }
}