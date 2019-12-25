import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferReader implements InputStreamInterface {
	private String filePath;
	private FileReader fr;
	private BufferedReader br;

	public BufferReader(String filePath) {
		super();
		this.filePath = filePath;
	}

	@Override
	public void open() throws FileNotFoundException {
		fr = new FileReader(new File(filePath));
		br = new BufferedReader(fr);
	}

	@Override
	public String readln() throws IOException {
		return br.readLine();
	}

	public void seek(long pos) throws IOException {
		br.skip(pos);
	}

	@Override
	public boolean end_of_stream() throws IOException {
		br.mark(1);
	    int i = br.read();
	    br.reset();
	    return i < 0;
	}

	@Override
	public void close() throws IOException {
		fr.close();
		br.close();
	}

	@Override
	public void setBuffer(int bufSize) {
		
	}

}
