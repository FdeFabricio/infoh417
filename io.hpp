#ifndef io_hpp
#define io_hpp

#include <fstream>
using namespace std;


class InputStream
{
private:
    string _fileName;
    ifstream _stream;
    
public:
    InputStream(string fileName){
        _fileName = fileName;
    }
    
    void open();
    void seek(int pos);
    bool end_of_stream();
    
    string readln1();
    string readln2();
    string readln3(int b);
    string readln4(int b);
};

class OutputStream
{
private:
    string _fileName;
    ofstream _stream;
    
public:
    OutputStream(string fileName){
        _fileName = fileName;
    }
    
    void create();
    void close();
    
    void writeln1();
    void writeln2();
    void writeln3(int b);
    void writeln4(int b);
};

#endif /* io_hpp */
