package io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class InputStream4 implements InputStream {
    private MappedByteBuffer buffer;
    private RandomAccessFile file;
    private long bufferSize, nextPosition;

    public InputStream4(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public void open(String filePath) throws IOException {
        this.file = new RandomAccessFile(filePath, "r");
        this.map();
    }

    public String readln() {
        StringBuilder line = new StringBuilder();
        char c;
        while (true) {
            c = this.readChar();
            if (c == NEW_LINE) {
                break;
            }
            line.append(c);
        }
        return line.toString();
    }

    public void seek(long pos) throws IOException {
        this.nextPosition = pos;
        this.map();
    }

    public boolean end_of_stream() throws IOException {
        return !this.buffer.hasRemaining() && this.nextPosition >= this.file.length();
    }

    private void map() throws IOException {
        long size = Math.min(this.file.length() - this.nextPosition, this.bufferSize);
        this.buffer = this.file.getChannel().map(FileChannel.MapMode.READ_ONLY, this.nextPosition, size);
        this.nextPosition += size;
    }

    private char readChar() {
        if (!this.buffer.hasRemaining()) {
            try {
                this.map();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return (char) this.buffer.get();
    }
}