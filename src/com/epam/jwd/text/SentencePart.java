package com.epam.jwd.text;

public interface SentencePart {
    boolean contains(String key);
    SentencePart copy();
}
