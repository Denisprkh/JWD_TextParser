package by.prokhorenko.textparser.parser.impl;

import by.prokhorenko.textparser.entity.TextComponent;
import by.prokhorenko.textparser.entity.TextComponentType;
import by.prokhorenko.textparser.entity.Symbol;
import by.prokhorenko.textparser.exception.ParserException;
import by.prokhorenko.textparser.parser.AbstractTextParser;

public class SymbolTextParser extends AbstractTextParser {

    private static final int CHAR_INDEX = 0;


    public SymbolTextParser(){
        this.textComponentType = TextComponentType.SYMBOL;
    }

    @Override
    public TextComponent parse(String text)  {

       return new Symbol(text.charAt(CHAR_INDEX));
    }

}
