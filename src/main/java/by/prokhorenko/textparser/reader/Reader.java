package by.prokhorenko.textparser.reader;

import by.prokhorenko.textparser.exception.ReaderException;

public interface Reader {
    String readAll(String path) throws ReaderException;
}
