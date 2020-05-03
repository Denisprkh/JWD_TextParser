package by.prokhorenko.textparser.parser;

import by.prokhorenko.textparser.entity.TextComponent;
import by.prokhorenko.textparser.entity.TextComponentType;
import by.prokhorenko.textparser.entity.TextComposite;
import by.prokhorenko.textparser.exception.ParserException;
import by.prokhorenko.textparser.validation.AbstractValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractTextParser {

    private static final Logger LOG = LogManager.getLogger();
    protected AbstractTextParser nextParser;
    protected TextComponentType textComponentType;



    public TextComponent parse(String text) throws ParserException {
        AbstractValidator validator = new AbstractValidator();
        if(validator.isNull(text)){
            LOG.error("Text is null");
            throw new ParserException("Text is null");
        }

        if(validator.isNull(nextParser) ){
            LOG.error("NextParser is null");
            throw new ParserException("NextParser is null");
        }

        Pattern pattern = Pattern.compile(textComponentType.getRegexOfNextComponent());
        Matcher matcher = pattern.matcher(text);
        TextComponent component = new TextComposite(textComponentType);

            while(matcher.find()){
                String current = matcher.group();
                component.add(nextParser.parse(current));

            }

        return component;
    }

}
