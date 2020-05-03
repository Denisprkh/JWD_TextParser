package by.prokhorenko.textparser;


import by.prokhorenko.textparser.entity.TextComponent;
import by.prokhorenko.textparser.entity.TextComponentType;
import by.prokhorenko.textparser.exception.FinderException;
import by.prokhorenko.textparser.exception.ParserException;
import by.prokhorenko.textparser.exception.ReaderException;
import by.prokhorenko.textparser.exception.SorterException;
import by.prokhorenko.textparser.finder.TextFinder;
import by.prokhorenko.textparser.finder.impl.SentenceTextFinder;
import by.prokhorenko.textparser.parser.AbstractTextParser;
import by.prokhorenko.textparser.parser.impl.ComponentTextParser;
import by.prokhorenko.textparser.parser.impl.SymbolTextParser;
import by.prokhorenko.textparser.reader.Reader;
import by.prokhorenko.textparser.reader.impl.FileReaderImpl;
import by.prokhorenko.textparser.sorter.TextSorter;
import by.prokhorenko.textparser.sorter.impl.ParagraphBySentenceAmountTextSorter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class Main {

    private static final Logger log = LogManager.getLogger();


    public static void main(String[] args) throws ReaderException, ParserException, SorterException, FinderException {
        /*
        Reader reader = new FileReaderImpl();
        String text = reader.readAll("file/data.txt");
        //System.out.println(text);
        */


        AbstractTextParser symbolParser = new SymbolTextParser();
        AbstractTextParser wordParser = new ComponentTextParser(symbolParser, TextComponentType.WORD);
       AbstractTextParser lexemeParser = new ComponentTextParser(wordParser,TextComponentType.LEXEME);
        AbstractTextParser sentenceParser = new ComponentTextParser(lexemeParser, TextComponentType.SENTENCE);

        AbstractTextParser paragraphParser = new ComponentTextParser(sentenceParser, TextComponentType.PARAGRAPH);

        AbstractTextParser textParser = new ComponentTextParser(paragraphParser, TextComponentType.TEXT);
        /*
        TextComponent textComponent = textParser.parse(text);



        System.out.println(textComponent);



        TextFinder sen = new SentenceTextFinder();

        Map<String, Integer> a = sen.findAndCountAllSameWords(textComponent);

        Set<Map.Entry<String, Integer>> b = a.entrySet();
        for(Map.Entry<String, Integer> c : b ){
            System.out.println(c.getKey() + c.getValue());
        }

        List<TextComponent> v = sen.findSentencesWithLongestWord(textComponent);


        for(TextComponent f : v){
            System.out.println(f);
        }





        TextSorter textSorter = new ParagraphBySentenceAmountTextSorter();
        textSorter.sort(textComponent);
        System.out.println(textComponent);

         */

        String source = "    good good good !    ";
        TextComponent comp = textParser.parse(source);
        TextFinder sen = new SentenceTextFinder();

        Map<String, Integer> a = sen.findAndCountAllSameWords(comp);

        Set<Map.Entry<String, Integer>> b = a.entrySet();
        for(Map.Entry<String, Integer> c : b ){
            System.out.println(c.getKey() + c.getValue());
        }








    }
}
