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
        this.buffer = this.fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, this.bufferSize);
    }

    public void writeln(String line) throws IOException {
        // TODO fix https://stackoverflow.com/questions/59537674/how-to-prevent-mappedbytebuffer-put-from-writing-null-characters-at-the-end-of-f
        for (char c : line.toCharArray()) {
            if (!this.buffer.hasRemaining()) this.remapBuffer();
            this.buffer.put((byte) c);
        }
        if (!this.buffer.hasRemaining()) this.remapBuffer();
        this.buffer.put((byte) NEW_LINE);
    }

    public void close() throws IOException {
        this.buffer.clear();
        this.file.close();
        this.fileChannel.close();
    }

    private void remapBuffer() throws IOException {
        bufferCount += 1;
        this.buffer = this.fileChannel.map(FileChannel.MapMode.READ_WRITE, this.bufferCount * this.bufferSize, this.bufferSize);
    }
}
