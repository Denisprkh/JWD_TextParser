package by.prokhorenko.textparser.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TextComposite implements TextComponent {
    private List<TextComponent> textComponents;
    private TextComponentType textComponentType;

    public TextComposite(TextComponentType textComponentType){
        this.textComponents = new ArrayList<>();
        this.textComponentType = textComponentType;
    }

    @Override
    public void add(TextComponent textComponent) {
         textComponents.add(textComponent);
    }

    @Override
    public TextComponent getComponent(int index) {
        return  textComponents.get(index);
    }

    @Override
    public boolean remove(TextComponent textComponent) {
        return textComponents.remove(textComponent);
    }

    @Override
    public int getSize() {
        return textComponents.size();
    }

    @Override
    public TextComponentType getTextComponentType() {
        return textComponentType;
    }

    @Override
    public Iterator<TextComponent> getIterator() {
        return textComponents.iterator();
    }

    @Override
    public void clear() {
        textComponents.clear();
    }

    @Override
    public List<TextComponent> getChild() {
        return textComponents;
    }


    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

        for(TextComponent component : textComponents){
           if(TextComponentType.PARAGRAPH.equals(component.getTextComponentType())){
               builder.append("\n\t");
           }
            if(TextComponentType.SENTENCE.equals(component.getTextComponentType())){
                builder.append(" ");
            }
            builder.append(component.toString());
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextComposite that = (TextComposite) o;

        if (textComponents != null ? !textComponents.equals(that.textComponents) : that.textComponents != null)
            return false;
        return textComponentType == that.textComponentType;
    }

    @Override
    public int hashCode() {
        int result = textComponents != null ? textComponents.hashCode() : 0;
        result = 31 * result + (textComponentType != null ? textComponentType.hashCode() : 0);
        return result;
    }
}
