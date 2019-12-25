import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferReaderSize implements InputStreamInterface {
	private String filePath;
	private FileReader fr;
	private BufferedReader br;
	private int bufferSize = 8192; // default buffer size

	public BufferReaderSize(String filePath) {
		super();
		this.filePath = filePath;
	}

	@Override
	public void open() throws FileNotFoundException {
		fr = new FileReader(new File(filePath));
		br = new BufferedReader(fr, bufferSize);
	}

	@Override
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
		bufSize *=8; //in bytes
		this.bufferSize = bufSize;
	}

}
