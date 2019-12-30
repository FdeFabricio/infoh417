package io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class InputStream4 implements InputStream {
    private MappedByteBuffer buffer;
    private RandomAccessFile file;
    private int bufferSize;

    public InputStream4(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public void open(String filePath) throws IOException {
        this.file = new RandomAccessFile(filePath, "r");
        this.buffer = file.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, Math.min(this.bufferSize, file.length()));
    }

    public String readln() {
        StringBuilder line = new StringBuilder();
        char c;
        while (true) {
            c = (char) this.buffer.get();
            if (c == NEW_LINE) {
                break;
            }
            line.append(c);
        }
        return line.toString();
    }

    public void seek(long pos) throws IOException {
        this.buffer = this.file.getChannel().map(FileChannel.MapMode.READ_ONLY, pos, Math.min(this.bufferSize, this.file.length() - pos));
    }

    public boolean end_of_stream() throws IOException {
        return this.buffer.position() >= this.file.length();
    }
}