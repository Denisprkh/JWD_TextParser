package by.prokhorenko.textparser.entity;

public enum TextComponentType {
    TEXT("(.+?)\\s{4}"),
    PARAGRAPH("(.+?[.!?]+\\s)"),
    SENTENCE("([^ ]+)( *)"),
    LEXEME("([^,.!:]+)([,.!:]*)"),
    WORD("."),
    SYMBOL("");


    private String nextComponentRegex;

    TextComponentType(String nextComponentRegex){
        this.nextComponentRegex = nextComponentRegex;
    }

    public String getRegexOfNextComponent(){
        return nextComponentRegex;
    }
}
