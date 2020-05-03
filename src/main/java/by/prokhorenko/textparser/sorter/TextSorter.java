package by.prokhorenko.textparser.sorter;

import by.prokhorenko.textparser.entity.TextComponent;
import by.prokhorenko.textparser.exception.SorterException;

public interface TextSorter {
    void sort(TextComponent textComponent) throws SorterException;
}
