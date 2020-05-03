package by.prokhorenko.textparser.sorter.impl;

import by.prokhorenko.textparser.entity.TextComponent;
import by.prokhorenko.textparser.entity.TextComponentType;
import by.prokhorenko.textparser.exception.SorterException;
import by.prokhorenko.textparser.sorter.TextSorter;
import by.prokhorenko.textparser.sorter.comparator.ParagraphComparator;
import by.prokhorenko.textparser.validation.AbstractValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParagraphBySentenceAmountTextSorter implements TextSorter {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public void sort(TextComponent textComponent) throws SorterException {
        AbstractValidator validator = new AbstractValidator();
        if(validator.isNull(textComponent)){
            LOG.error("TextComponent is null");
            throw new SorterException("TextComponent is null");
        }

        if(!TextComponentType.TEXT.equals(textComponent.getTextComponentType())){
            LOG.error("TextComponents type is not Text " + textComponent.getTextComponentType());
            throw new SorterException("TextComponents type is not Text");
        }

        List<TextComponent> paragraphs = new ArrayList<>();
        Iterator<TextComponent> paragraphIterator = textComponent.getIterator();

        while(paragraphIterator.hasNext()){
            TextComponent component = paragraphIterator.next();
            if(TextComponentType.PARAGRAPH.equals(component.getTextComponentType())){
                paragraphs.add(component);
            }
        }

        paragraphs.sort(new ParagraphComparator());

        textComponent.clear();

        for(TextComponent paragraph: paragraphs){
            textComponent.add(paragraph);
        }

    }


}
