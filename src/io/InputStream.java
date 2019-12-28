package io;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface InputStream {

    char NEW_LINE = '\n';

    void open(String filePath) throws FileNotFoundException;

    String readln() throws IOException;

    void seek(long pos) throws IOException;

    boolean end_of_stream() throws IOException;
}