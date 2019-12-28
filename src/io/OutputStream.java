package io;

import java.io.IOException;

public interface OutputStream {

    char NEW_LINE = '\n';

    void create(String FilePath) throws IOException;

    void writeln(String string) throws IOException;

    void close() throws IOException;
}
