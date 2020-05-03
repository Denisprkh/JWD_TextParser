package by.prokhorenko.textparser.entity;

import java.util.Iterator;
import java.util.List;

public interface TextComponent {

    void add(TextComponent textComponent);
    TextComponent getComponent(int index);
    boolean remove(TextComponent textComponent);
    int getSize();
    TextComponentType getTextComponentType();
    Iterator<TextComponent> getIterator();
    void clear();
    List<TextComponent> getChild();

}
