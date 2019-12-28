import java.io.FileNotFoundException;
import java.io.IOException;

public class test {

	public static void main(String[] args) throws IOException {
		InputStream br = new InputStream("C:\\Users\\Ledia\\Desktop\\Studies Brussels\\DSA\\project\\imdb\\comp_cast_type.csv");
		OutputStream bw = new OutputStream("C:\\Users\\Ledia\\Desktop\\Studies Brussels\\DSA\\project\\imdb\\write.csv");
		br.open();
		bw.create();
		String line = br.readln();
		System.out.println(line);
		bw.writeln(line);
		br.seek(2);
		line = br.readln();
		System.out.println(line);
		bw.writeln(line);
		br.close();
		bw.close();
		//boolean eo = br.end_of_stream();
		//System.out.println(eo);
		//br.seek(2);
		//line = br.readln();
		//System.out.println(line);
		
	}

}
