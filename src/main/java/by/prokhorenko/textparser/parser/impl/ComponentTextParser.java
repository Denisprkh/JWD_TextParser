package by.prokhorenko.textparser.parser.impl;

import by.prokhorenko.textparser.entity.TextComponentType;
import by.prokhorenko.textparser.parser.AbstractTextParser;

public class ComponentTextParser extends AbstractTextParser {

    public ComponentTextParser(AbstractTextParser nextParser, TextComponentType textComponentType) {
        this.nextParser = nextParser;
        this.textComponentType = textComponentType;
    }

}
