package io;

import java.io.*;

public class InputStream3 implements InputStream {
    private FileReader fr;
    private BufferedReader br;
    private int bufferSize;

    public InputStream3(int bufferSizeBytes) {
        this.bufferSize = bufferSizeBytes * 8;
    }

    public void open(String filePath) throws FileNotFoundException {
        fr = new FileReader(new File(filePath));
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
        br.skip(pos); // TODO use seek
    }

    public boolean end_of_stream() throws IOException {
        br.mark(1);
        int i = br.read();
        br.reset();
        return i < 0;
    }
}
