package by.prokhorenko.textparser.finder.impl;

import by.prokhorenko.textparser.entity.TextComponent;
import by.prokhorenko.textparser.entity.TextComponentType;
import by.prokhorenko.textparser.exception.FinderException;
import by.prokhorenko.textparser.finder.TextFinder;
import by.prokhorenko.textparser.validation.AbstractValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.*;



public class SentenceTextFinder implements TextFinder {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public List<TextComponent> findSentencesWithLongestWord(TextComponent textComponent) throws FinderException {
        AbstractValidator validator = new AbstractValidator();
        if (validator.isNull(textComponent)) {
            LOG.error("TextComponent is null");
            throw new FinderException("TextComponent is null");
        }
        if(!TextComponentType.TEXT.equals(textComponent.getTextComponentType())){
            LOG.error("TextComponents type is not Text: " + textComponent.getTextComponentType());
            throw new FinderException("TextComponents type is not Text");
        }
        int sizeOfTheLongestWord = 0;

        List<TextComponent> paragraphs = textComponent.getChild();
        List<TextComponent> sentencesWithLongestWord = new ArrayList<>();

        for(TextComponent paragraph : paragraphs){
            List<TextComponent> sentences;
            sentences = paragraph.getChild();
            for(TextComponent sentence : sentences){
                List<TextComponent> lexemes;
                lexemes = sentence.getChild();
                for(TextComponent lexeme : lexemes){
                    List<TextComponent> words;
                    words = lexeme.getChild();
                    for(TextComponent word : words){
                        if (word.getSize() > sizeOfTheLongestWord) {
                            sizeOfTheLongestWord = word.getSize();
                            sentencesWithLongestWord.clear();
                            sentencesWithLongestWord.add(sentence);
                        } else if (word.getSize() == sizeOfTheLongestWord) {
                            sentencesWithLongestWord.add(sentence);
                        }
                    }
                }
            }

        }

        deleteSameComponents(sentencesWithLongestWord);

        return sentencesWithLongestWord;
    }

    private void deleteSameComponents(List<TextComponent> components) throws FinderException {
        AbstractValidator validator = new AbstractValidator();
        if (validator.isNull(components)) {
            LOG.error("TextComponent is null");
            throw new FinderException("TextComponent is null");
        }

        for(int i = 0; i < components.size(); i ++){
            for(int j = 0; j < components.size(); j++){
                if(i == j){
                    continue;
                }
                if(components.get(i).equals(components.get(j))){
                    components.remove(components.get(j));
                }
            }
        }
    }

    @Override
    public List<TextComponent> findSentencesLongerThanGivenLength(TextComponent textComponent, int numOfWords)
            throws FinderException {
        AbstractValidator validator = new AbstractValidator();
        if (validator.isNull(textComponent)) {
            LOG.error("TextComponent is null");
            throw new FinderException("TextComponent is null");
        }
        if(!TextComponentType.TEXT.equals(textComponent.getTextComponentType())){
            LOG.error("TextComponents type is not Text: " + textComponent.getTextComponentType());
            throw new FinderException("TextComponents type is not Text");
        }

        List<TextComponent> paragraphs = textComponent.getChild();
        List<TextComponent> sentencesLongerThanGivenLength = new ArrayList<>();
        for (TextComponent paragraph : paragraphs) {

            List<TextComponent> sentences = paragraph.getChild();
            for (TextComponent sentence : sentences) {
                if (sentence.getSize() > numOfWords) {
                    sentencesLongerThanGivenLength.add(sentence);
                }
            }
        }
        return sentencesLongerThanGivenLength;
    }

    private List<TextComponent> getAllWordsFromText(TextComponent textComponent) throws FinderException {
        AbstractValidator validator = new AbstractValidator();
        if (validator.isNull(textComponent)) {
            LOG.error("TextComponent is null");
            throw new FinderException("TextComponent is null");
        }
        if(!TextComponentType.TEXT.equals(textComponent.getTextComponentType())){
            LOG.error("TextComponents type is not Text: " + textComponent.getTextComponentType());
            throw new FinderException("TextComponents type is not Text");
        }

        List<TextComponent> allWords = new ArrayList<>();
        List<TextComponent> paragraphs = textComponent.getChild();

        for (TextComponent paragraph : paragraphs) {
            List<TextComponent> sentences = paragraph.getChild();
            for (TextComponent sentence : sentences) {
                List<TextComponent> lexemes = sentence.getChild();
                for(TextComponent lexeme : lexemes){
                    List<TextComponent> words = lexeme.getChild();
                    for (TextComponent word : words) {
                        allWords.add(word);
                    }
                }

            }
        }
        return allWords;
    }

    @Override
    public Map<String,Integer> findAndCountAllSameWords(TextComponent textComponent) throws FinderException {
        AbstractValidator validator = new AbstractValidator();
        if(validator.isNull(textComponent)){
            LOG.error("TextComponent is null");
            throw new FinderException("TextComponent is null");
        }
        if(!TextComponentType.TEXT.equals(textComponent.getTextComponentType())){
            LOG.error("TextComponents type is not Text: " + textComponent.getTextComponentType());
            throw new FinderException("TextComponents type is not Text");
        }

        Map<String, Integer> wordsCounter = new HashMap<>();
        List<TextComponent> allWords = getAllWordsFromText(textComponent);

        for(TextComponent word : allWords){
            String wordString = word.toString().toLowerCase().trim();
            if(wordString.equalsIgnoreCase("")){
                continue;
            }
            Integer count = wordsCounter.get(wordString);
            if(count == null){
                wordsCounter.put(wordString, 1);
            }else{
                wordsCounter.put(wordString, ++count);
            }
        }

        deleteUniqueElements(wordsCounter);

        return wordsCounter;
    }

    private Map<String, Integer> deleteUniqueElements(Map<String, Integer> wordsCounter) throws FinderException {
        AbstractValidator validator = new AbstractValidator();
        if(validator.isNull(wordsCounter)){
            LOG.error("TextComponent is null");
            throw new FinderException("TextComponent is null");
        }

        Iterator<Map.Entry<String, Integer>> iterator = wordsCounter.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if(entry.getValue().equals(1)) {
                iterator.remove();
            }
        }
        return wordsCounter;
    }

}
