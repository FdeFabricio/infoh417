#include "io.hpp"

void InputStream::open() {
    _stream.open(_fileName, ifstream::in);
}

void InputStream::seek(int pos) {
    _stream.seekg(pos);
}

bool InputStream::end_of_stream() {
    return _stream.eof();
}

string InputStream::readln1() {
    return readln3(1);
}

string InputStream::readln2() {
    //TODO: implement readln2
    return "";
}

string InputStream::readln3(int b) {
    //TODO: implement readln3
    return "";
}

string InputStream::readln4(int b) {
    //TODO: implement readln4
    return "";
}

void OutputStream::create() {
    _stream.open(_fileName, ifstream::out);
}

void OutputStream::close() {
    _stream.close();
}

void OutputStream::writeln1() {
    //TODO: implement writeln1
}

void OutputStream::writeln2() {
    //TODO: implement writeln2
}

void OutputStream::writeln3(int b) {
    //TODO: implement writeln3
}

void OutputStream::writeln4(int b) {
    //TODO: implement writeln4
}
