import java.io.FileNotFoundException;
import java.io.IOException;

public interface InputStreamInterface {
	
	public void open() throws FileNotFoundException;
	public String readln() throws IOException;
	public void seek(long pos) throws IOException;
	public boolean end_of_stream()throws IOException;
	public void close() throws IOException;
	public void setBuffer(int bufSize);
}
