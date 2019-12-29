package experiment;
import io.*;
import java.io.IOException;
import java.io.RandomAccessFile;
public class RandomReading {
	
	private int randjump(InputStream input, String filePath, int j) throws IOException {
		RandomAccessFile fr = new RandomAccessFile(filePath, "r");
		long lengthFile = fr.length();
		fr.close();
		input.open(filePath);
        int sum = 0;
        for (int i = 0; i < j; i++) {
        	long p = (long) (Math.random()*lengthFile);
        	input.seek(p);
            String line = input.readln();
            sum += line.length();
            
        }    
		return sum;
    }

    public int randjump1(String f, int j) throws IOException {
        return this.randjump(new InputStream1(), f, j);
    }

    public int randjump2(String f, int j) throws IOException {
        return this.randjump(new InputStream2(), f, j);
    }

    public int randjump3(String f, int j, int b) throws IOException {
        return this.randjump(new InputStream3(b), f, j);
    }

    public int randjump4(String f, int j, int b) throws IOException {
        return this.randjump(new InputStream3(b), f, j);
    }
}
