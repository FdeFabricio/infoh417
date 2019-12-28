package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputStream2 implements InputStream {
	private FileReader fr;
	private BufferedReader br;

	public void open(String filePath) throws FileNotFoundException {
		fr = new FileReader(new File(filePath));
		br = new BufferedReader(fr);
	}

	public String readln() throws IOException {
		return br.readLine();
	}

	public void seek(long pos) throws IOException {
		br.skip(pos); // TODO use seek
	}

	public boolean end_of_stream() throws IOException {
		br.mark(1);
	    int i = br.read();
	    br.reset();
	    return i < 0;
	}
}
