import java.io.FileNotFoundException;
import java.io.IOException;

public class test {

	public static void main(String[] args) throws IOException {
		InputStream br = new InputStream("C:\\Users\\Ledia\\Desktop\\Studies Brussels\\DSA\\project\\imdb\\comp_cast_type.csv");
		br.open();
		String line = br.readln();
		System.out.println(line);
		br.seek(2);
		line = br.readln();
		System.out.println(line);
		//boolean eo = br.end_of_stream();
		//System.out.println(eo);
		//br.seek(2);
		//line = br.readln();
		//System.out.println(line);
		
	}

}
