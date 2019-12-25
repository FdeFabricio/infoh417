import java.io.FileNotFoundException;
import java.io.IOException;

public interface OutputStreamInterface {
	public void create() throws IOException ;
	
	public void writeln(String line) throws IOException;
	
	public void close() throws IOException;
	public void setBuffer(int bufSize);
}
