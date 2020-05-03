package by.prokhorenko.textparser.finder;


import by.prokhorenko.textparser.entity.TextComponent;
import by.prokhorenko.textparser.entity.TextComponentType;
import by.prokhorenko.textparser.exception.FinderException;
import by.prokhorenko.textparser.exception.ParserException;
import by.prokhorenko.textparser.exception.ReaderException;
import by.prokhorenko.textparser.finder.impl.SentenceTextFinder;
import by.prokhorenko.textparser.parser.AbstractTextParser;
import by.prokhorenko.textparser.parser.impl.ComponentTextParser;
import by.prokhorenko.textparser.parser.impl.SymbolTextParser;
import by.prokhorenko.textparser.reader.Reader;
import by.prokhorenko.textparser.reader.impl.FileReaderImpl;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SentenceTextFinderTest {

    String sourceText;
    String linkToTestFile;
    Reader fileReader;
    AbstractTextParser paragraphParser;
    AbstractTextParser sentenceParser;
    AbstractTextParser lexemeParser;
    AbstractTextParser wordParser;
    AbstractTextParser symbolParser;
    AbstractTextParser textParser;
    TextComponent text;
    TextFinder textFinder;
    List<TextComponent> expectedSentencesWithLongestWord;
    List<TextComponent> expectedSentencesLongerThanGivenLength;
    Map<String,Integer> expectedDuplicateWords;
    String linkToFileWithDuplicates;
    String duplicatesSourceText;
    TextComponent textWithDuplicates;


    @BeforeClass
    public void setUp() throws ReaderException, ParserException {
        linkToTestFile = "file/SorterFinderDataTest";
        fileReader = new FileReaderImpl();
        symbolParser = new SymbolTextParser();
        wordParser = new ComponentTextParser(symbolParser, TextComponentType.WORD);
        lexemeParser = new ComponentTextParser(wordParser, TextComponentType.LEXEME);
        sentenceParser = new ComponentTextParser(lexemeParser, TextComponentType.SENTENCE);
        paragraphParser = new ComponentTextParser(sentenceParser, TextComponentType.PARAGRAPH);
        textParser = new ComponentTextParser(paragraphParser,TextComponentType.TEXT);
        sourceText = fileReader.readAll(linkToTestFile);
        textFinder = new SentenceTextFinder();
        text = textParser.parse(sourceText);
        expectedSentencesWithLongestWord = new ArrayList<>();
        expectedSentencesWithLongestWord.add(text.getComponent(1).getComponent(1));
        expectedSentencesLongerThanGivenLength = new ArrayList<>();
        expectedSentencesLongerThanGivenLength.add(text.getComponent(0).getComponent(1));
        expectedDuplicateWords = new HashMap<>();
        linkToFileWithDuplicates = "file/DataForDuplicatesTest";
        duplicatesSourceText = fileReader.readAll(linkToFileWithDuplicates);
        textWithDuplicates = textParser.parse(duplicatesSourceText);
        expectedDuplicateWords.put("good",3);



    }

    @Test
    public void findSentencesWithLongestWordTest() throws FinderException {
        List<TextComponent> actualSentencesWithLongestWord = textFinder.findSentencesWithLongestWord(text);
        Assert.assertEquals(expectedSentencesWithLongestWord,actualSentencesWithLongestWord);
    }

    @Test
    public void findSentencesLongerThanGivenLengthTest() throws FinderException {
        List<TextComponent> actualSentencesLongerThanGivenLength =
                textFinder.findSentencesLongerThanGivenLength(text,30);
        Assert.assertEquals(expectedSentencesLongerThanGivenLength,actualSentencesLongerThanGivenLength);
    }

    @Test
    public void findAndCountAllSameWordsTest() throws FinderException {
        Map<String,Integer> actualDuplicateWords = textFinder.findAndCountAllSameWords(textWithDuplicates);

        Assert.assertEquals(actualDuplicateWords,expectedDuplicateWords);
    }

    @AfterClass
    public void tierDown(){
        String sourceText = null;
        String linkToTestFile = null;
        Reader fileReader = null;
        AbstractTextParser paragraphParser = null;
        AbstractTextParser sentenceParser = null;
        AbstractTextParser lexemeParser = null;
        AbstractTextParser wordParser = null;
        AbstractTextParser symbolParser = null;
        AbstractTextParser textParser = null;
        TextComponent text = null;
        TextFinder textFinder = null;
        List<TextComponent> expectedSentencesWithLongestWord = null;
        List<TextComponent> expectedSentencesLongerThanGivenLength = null;
        Map<String,Integer> expectedDuplicateWords = null;
        String linkToFileWithDuplicates = null;
        String duplicatesSourceText = null;
        TextComponent textWithDuplicates = null;
    }





}
