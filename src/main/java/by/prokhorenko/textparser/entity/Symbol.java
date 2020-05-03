package by.prokhorenko.textparser.entity;

import java.util.Iterator;
import java.util.List;

public class Symbol implements TextComponent {

    private TextComponentType textComponentType = TextComponentType.SYMBOL;
    private Character character;

    public Symbol(Character character){
        this.character = character;
    }


    @Override
    public void add(TextComponent textComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TextComponent getComponent(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(TextComponent textComponent){
        throw new UnsupportedOperationException();
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public TextComponentType getTextComponentType() {
        return textComponentType;
    }

    @Override
    public Iterator<TextComponent> getIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<TextComponent> getChild() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString(){
        return String.valueOf(character);
    }
}

