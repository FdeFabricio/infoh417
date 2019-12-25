import java.io.FileWriter;
import java.io.IOException;

public class OutputStream implements OutputStreamInterface {
	private String filePath;
	private FileWriter fw;
	
	
	public OutputStream(String filePath) {
		super();
		this.filePath = filePath;
	}

	@Override
	public void create() throws IOException {
		fw = new FileWriter(filePath);
	}

	@Override
	public void writeln(String line) throws IOException{
		
		for (char ch: line.toCharArray()) {
			fw.write(ch);
		}
		fw.write('\n');
	}

	@Override
	public void close() throws IOException {
		fw.close();
	}


	@Override
	public void setBuffer(int bufSize) {
		// TODO Auto-generated method stub
		
	}

}
