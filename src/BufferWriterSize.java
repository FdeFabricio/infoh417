import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class BufferWriterSize implements OutputStreamInterface{
	private String filePath;
	private FileWriter fw;
	private BufferedWriter bw;
	private int bufferSize = 8192; // default buffer size
	
	public BufferWriterSize(String filePath) {
		super();
		this.filePath = filePath;
	}

	@Override
	public void create() throws IOException {
		fw = new FileWriter(filePath);
		bw = new BufferedWriter(fw,bufferSize);
		
	}

	@Override
	public void writeln(String line) throws IOException {
		for (char ch: line.toCharArray()) {
			bw.write(ch);
		}
		bw.write('\n');
		
	}

	@Override
	public void close() throws IOException {
		bw.close();
		
	}
	@Override
	public void setBuffer(int bufSize) {
		bufSize *=8; //in bytes
		this.bufferSize = bufSize;
	}

}
