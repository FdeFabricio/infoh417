import io.InputStream1;
import io.OutputStream1;

import java.io.IOException;

public class test {

    public static void main(String[] args) throws IOException {
        InputStream1 br = new InputStream1();
        OutputStream1 bw = new OutputStream1();
        br.open("C:\\Users\\Ledia\\Desktop\\Studies Brussels\\DSA\\project\\imdb\\comp_cast_type.csv");
        bw.create("C:\\Users\\Ledia\\Desktop\\Studies Brussels\\DSA\\project\\imdb\\write.csv");
        String line = br.readln();
        System.out.println(line);
        bw.writeln(line);
        br.seek(2);
        line = br.readln();
        System.out.println(line);
        bw.writeln(line);
        bw.close();
        //boolean eo = br.end_of_stream();
        //System.out.println(eo);
        //br.seek(2);
        //line = br.readln();
        //System.out.println(line);
    }

}
