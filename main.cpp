#include <iostream>
#include "io.hpp"

int main(int argc, const char * argv[]) {
    string testFileName = "../test.txt";

    OutputStream write(testFileName);
    write.create();
    write.close();

    ofstream testFile;
    testFile.open(testFileName);
    testFile << "this is a test\n";
    testFile.close();

    InputStream read(testFileName);
    read.open();
    read.seek(4);
    cout << boolalpha << read.end_of_stream() << "\n";
}

