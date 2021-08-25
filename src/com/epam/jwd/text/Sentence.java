package com.epam.jwd.text;

import com.epam.jwd.parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Sentence implements Comparable<Sentence> {

    private static final String REGEX_WHITE_SPACE = "\\s";
    private static final String EMPTY_STRING = "";

    private List<SentencePart> sentenceParts;

    public Sentence(String sentenceSource){
        initialize(sentenceSource);
    }

    public Sentence(List<SentencePart> sentenceCodePartsSource){
        sentenceParts = sentenceCodePartsSource;
    }

    public Sentence copy(){
        List<SentencePart> newSentenceParts = new ArrayList<>();
        sentenceParts.forEach(sentencePart -> newSentenceParts.add(sentencePart.copy()));
        return new Sentence(newSentenceParts);
    }

    public List<SentencePart> getAsParts() {
        return sentenceParts;
    }

    public boolean contains(String key){
        for(SentencePart sentencePart: sentenceParts){
            if (sentencePart.contains(key)) {
                return true;
            }
        }
        return false;
    }

    public void addWord(Word source){
        sentenceParts.add(source);
    }

    public void addWord(String source){
        sentenceParts.add(new Word(source.replaceAll(REGEX_WHITE_SPACE, EMPTY_STRING)));
    }

    public void addWord(Word source, int index){
        sentenceParts.add(index, source);
    }

    public void addWord(String source, int index){
        sentenceParts.add(index, new Word(source.replaceAll(REGEX_WHITE_SPACE, "")));
    }

    public void addSymbol(PunctuationMark source){
        sentenceParts.add(source);
    }

    public void addSymbol(String source){
        if (source.length() == 1 && !Character.isLetter(source.charAt(0)) && !Character.isDigit(source.charAt(0))){
            sentenceParts.add(new PunctuationMark(source.charAt(0)));
        }
    }

    public void addSymbol(PunctuationMark source, int index){
        sentenceParts.add(index, source);
    }

    public void addSymbol(String source, int index){
        if (source.length() == 1 && !Character.isLetter(source.charAt(0)) && !Character.isDigit(source.charAt(0))){
            sentenceParts.add(index, new PunctuationMark(source.charAt(0)));
        }
    }

    public SentencePart getFirstSentencePart(){
        return sentenceParts.get(0);
    }

    public SentencePart getLastSentencePart(){
        return sentenceParts.get(sentenceParts.size()-1);
    }

    public SentencePart getSentencePart(int index){
        return sentenceParts.get(index);
    }

    public Word getFirstWord(){
        for(SentencePart sentenceCodePart : sentenceParts){
            if (sentenceCodePart instanceof Word) return (Word) sentenceCodePart;
        }
        return null;
    }

    public Word getLastWord(){
        for(int i = sentenceParts.size() - 1; i >= 0; i--){
            if (sentenceParts.get(i) instanceof Word) return (Word) sentenceParts.get(i);
        }
        return null;
    }

    public Word getWord(int index){
        Word result = null;
        int counter = 0;
        while (counter <= index){
            if (sentenceParts.get(counter) instanceof Word) {
                result = (Word) sentenceParts.get(counter);
                counter++;
            }
        }
        return result;
    }

    public PunctuationMark getFirstSymbol(){
        for(SentencePart sentenceCodePart : sentenceParts){
            if (sentenceCodePart instanceof PunctuationMark) return (PunctuationMark) sentenceCodePart;
        }
        return null;
    }

    public PunctuationMark getLastSymbol(){
        for(int i = sentenceParts.size() - 1; i >= 0; i--){
            if (sentenceParts.get(i) instanceof PunctuationMark) return (PunctuationMark) sentenceParts.get(i);
        }
        return null;
    }

    public PunctuationMark getSymbol(int index){
        PunctuationMark result = null;
        int counter = 0;
        while (counter <= index){
            if (sentenceParts.get(counter) instanceof PunctuationMark) {
                result = (PunctuationMark) sentenceParts.get(counter);
                counter++;
            }
        }
        return result;
    }

    private void initialize(String sentenceSource){
        sentenceParts = Parser.sentenceToSentenceContentList(sentenceSource);
    }


    @Override
    public int hashCode() {
        return sentenceParts.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Sentence){
            return sentenceParts.equals(((Sentence) obj).getAsParts());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sentenceParts.forEach(sb::append);
        return sb.toString().trim();
    }

    @Override
    public int compareTo(Sentence o) {
        if (o != null){
            AtomicInteger counter1 = new AtomicInteger();
            sentenceParts.forEach(sentencePart -> {
                if (sentencePart instanceof Word){
                    counter1.getAndIncrement();
                }
            });
            AtomicInteger counter2 = new AtomicInteger();
            o.getAsParts().forEach(sentencePart -> {
                if (sentencePart instanceof Word){
                    counter2.getAndIncrement();
                }
            });
            return counter1.get() - counter2.get();
        } else {
            return -1;
        }
    }
}
