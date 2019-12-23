import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class InputStream {
    private RandomAccessFile file;
    private String filePath;

    public InputStream(String path) {
        this.filePath = path;
    }

    public void open() throws FileNotFoundException {
        this.file = new RandomAccessFile(this.filePath, "r");
    }

    public void seek(long pos) throws IOException {
        this.file.seek(pos);
    }

    public boolean end_of_stream() throws IOException {
       long previousPos =  this.file.getFilePointer();
       try {
            this.file.read();
        } catch (IOException e) {
            return true;
        }
        this.file.seek(previousPos);
        return false;
    }

    public String readln1() {
        return "";
    }

    public String readln2() {
    	BufferedReader br=new BufferedReader(this.file);
	      String line;
	      return br.readLine();
    }

    public String readln3() {
        return "";
    }

    public String readln4() {
        return "";
    }
}

