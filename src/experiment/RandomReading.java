package experiment;

import io.*;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class RandomReading {
    private Random random = new Random();

    private int randjump(String filePath, InputStream input, int j) throws IOException {
        RandomAccessFile fr = new RandomAccessFile(filePath, "r");
        long lengthFile = fr.length();
        fr.close();
        System.out.println(lengthFile);
        input.open(filePath);
        int sum = 0;
        for (int i = 0; i < j; i++) {
            long p = (random.nextInt((int) lengthFile));
            System.out.println(p);
            input.seek(p);
            String line = input.readln();
            sum += line.length();
        }

        return sum;
    }

    public int run(String filePath, int streamType, int randomJumps) throws IOException {
        switch (streamType) {
            case 1:
                return this.randjump(filePath, new InputStream1(), randomJumps);
            case 2:
                return this.randjump(filePath, new InputStream2(), randomJumps);
            default:
                throw new IllegalArgumentException("wrong argument");
        }
    }

    public int run(String filePath, int streamType, int randomJumps, int bufferSize) throws IOException {
        switch (streamType) {
            case 3:
                return this.randjump(filePath, new InputStream3(bufferSize), randomJumps);
            case 4:
                return this.randjump(filePath, new InputStream4(bufferSize), randomJumps);
            default:
                throw new IllegalArgumentException("wrong argument");
        }
    }
}
