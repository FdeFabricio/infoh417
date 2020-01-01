package io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class OutputStream4 implements OutputStream {
    private MappedByteBuffer buffer;
    private RandomAccessFile file;
    private FileChannel fileChannel;
    private int bufferSize;
    private int bufferCount;

    public OutputStream4(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public void create(String filePath) throws IOException {
        this.file = new RandomAccessFile(filePath, "rw");
        this.file.setLength(0);
        this.fileChannel = this.file.getChannel();
        this.map();
    }

    public void writeln(String line) {
        for (char c : line.toCharArray()) {
            putChar(c);
        }
        putChar(NEW_LINE);
    }

    public void close() throws IOException {
        this.buffer.force();
        this.fileChannel.truncate(this.bufferCount * this.bufferSize - this.buffer.remaining());
        this.fileChannel.close();
        this.buffer.clear();
        this.file.close();
    }

    private void map() throws IOException {
        this.buffer = this.fileChannel.map(FileChannel.MapMode.READ_WRITE, this.bufferCount * this.bufferSize, this.bufferSize);
        this.bufferCount += 1;
    }

    private void putChar(char c) {
        if (!this.buffer.hasRemaining()) {
            try {
                this.map();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.buffer.put((byte) c);
    }
}
