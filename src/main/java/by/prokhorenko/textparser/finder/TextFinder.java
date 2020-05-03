package by.prokhorenko.textparser.finder;

import by.prokhorenko.textparser.entity.TextComponent;
import by.prokhorenko.textparser.exception.FinderException;
import by.prokhorenko.textparser.validation.AbstractValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public interface TextFinder {

    List<TextComponent> findSentencesWithLongestWord(TextComponent text) throws FinderException;
    List<TextComponent> findSentencesLongerThanGivenLength(TextComponent textComponent, int numOfWords) throws
            FinderException;
    Map<String, Integer> findAndCountAllSameWords(TextComponent textComponent) throws FinderException;

}


