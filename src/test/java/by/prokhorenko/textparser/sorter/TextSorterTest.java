package by.prokhorenko.textparser.sorter;

import by.prokhorenko.textparser.entity.TextComponent;
import by.prokhorenko.textparser.entity.TextComponentType;
import by.prokhorenko.textparser.exception.ParserException;
import by.prokhorenko.textparser.exception.ReaderException;
import by.prokhorenko.textparser.exception.SorterException;
import by.prokhorenko.textparser.parser.AbstractTextParser;
import by.prokhorenko.textparser.parser.impl.ComponentTextParser;
import by.prokhorenko.textparser.parser.impl.SymbolTextParser;
import by.prokhorenko.textparser.reader.Reader;
import by.prokhorenko.textparser.reader.impl.FileReaderImpl;
import by.prokhorenko.textparser.sorter.impl.ParagraphBySentenceAmountTextSorter;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TextSorterTest {


    String sourceText;
    String linkToTestFile;
    TextSorter textSorter;
    Reader fileReader;
    AbstractTextParser paragraphParser;
    AbstractTextParser sentenceParser;
    AbstractTextParser lexemeParser;
    AbstractTextParser wordParser;
    AbstractTextParser symbolParser;
    AbstractTextParser textParser;
    TextComponent text;
    String sortedText;


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
        textSorter = new ParagraphBySentenceAmountTextSorter();
        text = textParser.parse(sourceText);
        sortedText = "\t It is a established fact that a reader will be of a page when looking at its layout...\n" +
                "\t Bye бандерлоги.\n" +
                "\t It has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged.  It was popularised in the “Динамо” (Рига) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum!\n" +
                "\t It is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout.  The point of using  Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English?";




    }

    @Test
    public void sortTest() throws SorterException, ReaderException, ParserException {

        textSorter.sort(text);
        String actualText = text.toString();
        Assert.assertEquals(actualText,text);
    }

    @AfterClass
        public void tierDown() {
        String sourceText = null;
        String linkToTestFile = null;
        TextSorter textSorter = null;
        Reader fileReader = null;
        String expectedText = null;
        AbstractTextParser paragraphParser = null;
        AbstractTextParser sentenceParser = null;
        AbstractTextParser lexemeParser = null;
        AbstractTextParser wordParser = null;
        AbstractTextParser symbolParser = null;
        AbstractTextParser textParser = null;
        TextComponent text = null;
    }

}
