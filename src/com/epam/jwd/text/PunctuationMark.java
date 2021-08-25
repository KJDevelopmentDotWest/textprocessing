package com.epam.jwd.text;

import java.util.Objects;

public class PunctuationMark implements SentencePart {

    private char symbol;

    public PunctuationMark(char source){
        symbol = source;
    }

    @Override
    public boolean contains(String key) {
        return key.length() == 1 && key.charAt(0) == symbol;
    }

    @Override
    public SentencePart copy() {
        return new PunctuationMark(symbol);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PunctuationMark){
            return symbol == obj.toString().charAt(0);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }

    @Override
    public String toString(){
        return String.valueOf(symbol);
    }
}
