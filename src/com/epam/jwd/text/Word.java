package com.epam.jwd.text;

public class Word implements SentencePart {

    private String word;

    public Word(String source){
        word = source;
    }

    @Override
    public boolean contains(String key) {
        return word.contains(key);
    }

    @Override
    public SentencePart copy() {
        return new Word(word);
    }

    public String substring(int beginIndex, int endIndex) {
        return word.substring(beginIndex, endIndex);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Word){
            return word.equals(((Word)obj).toString());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }

    @Override
    public String toString(){
        return word.trim();
    }
}
