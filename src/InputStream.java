import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputStream implements InputStreamInterface {
	private String filePath;
	private FileReader fr;


	public InputStream(String filePath) {
		super();
		this.filePath = filePath;
	}

	@Override
	public void open() throws FileNotFoundException {
		fr = new FileReader(new File(filePath));
	}

	@Override
	public String readln() throws IOException {
		StringBuilder response = new StringBuilder();
		char r;

		do {
			r = (char) fr.read();
			if (r != '\n')
				response.append(r);
		} while (r != '\n');
		return response.toString();
	}

	public void seek(long pos) throws IOException {
		fr.skip(pos);
	}

	@Override
	public boolean end_of_stream() throws IOException {
		//To DO
		fr.mark(1);
	    int i = fr.read();
	    fr.reset();
	    return i < 0;
	}

	@Override
	public void close() throws IOException {
		fr.close();
	}

	@Override
	public void setBuffer(int bufSize) {
	}

}