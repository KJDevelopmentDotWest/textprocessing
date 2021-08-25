package com.epam.jwd.parser;

import com.epam.jwd.text.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Parser {

    private static final Logger logger = LogManager.getLogger(Parser.class);
    private static final String REGEX_TEXT_TO_SENTENCE = "(((\\.)\s)|(\n))";
    private static final String STRING_TEXT_TO_SENTENCE_LIST_CONVERSION_STARTED = "Text to Sentence list conversion started";
    private static final String STRING_TEXT_TO_SENTENCE_LIST_CONVERSION_ENDED = "Text to Sentence list conversion ended";
    private static final String STRING_SENTENCE_TO_SENTENCE_CONTENT_LIST_CONVERSION_STARTED = "Sentence to SentenceContent list conversion started";
    private static final String STRING_SENTENCE_TO_SENTENCE_CONTENT_LIST_CONVERSION_ENDED = "Sentence to SentenceContent list conversion ended";
    private static final String SYMBOL_OPEN_BRACE = "{";
    private static final String SYMBOL_CLOSE_BRACE = "}";
    private static final String SYMBOL_NEW_LINE = "\n";

    public static List<Sentence> textToSentenceList(String text){
        logger.debug(STRING_TEXT_TO_SENTENCE_LIST_CONVERSION_STARTED);
        List<Sentence> result = new ArrayList<>();

        AtomicInteger counter = new AtomicInteger(0);
        StringBuilder sb = new StringBuilder();

        List<String> stringList = Arrays.asList(text.split(REGEX_TEXT_TO_SENTENCE));

        stringList.forEach(string -> {
            if(!string.contains(SYMBOL_OPEN_BRACE) && counter.get() == 0){
                result.add(new Sentence(string));
            } else {
                sb.append(string).append(SYMBOL_NEW_LINE);
                if (string.contains(SYMBOL_OPEN_BRACE)) counter.getAndIncrement();
                if (string.contains(SYMBOL_CLOSE_BRACE)) counter.getAndDecrement();
                if (counter.get() == 0) {
                    result.add(new Sentence(sb.toString().trim()));
                    sb.setLength(0);
                }
            }
        });

        logger.debug(STRING_TEXT_TO_SENTENCE_LIST_CONVERSION_ENDED);
        return result;
    }

    public static List<SentencePart> sentenceToSentenceContentList(String sentence){
        logger.debug(STRING_SENTENCE_TO_SENTENCE_CONTENT_LIST_CONVERSION_STARTED);
        List<SentencePart> result = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (char character: sentence.toCharArray()){
            if (Character.isLetterOrDigit(character)){
                stringBuilder.append(character);
            } else {
                if (!stringBuilder.isEmpty()){
                    result.add(new Word(stringBuilder.toString().trim()));
                }
                stringBuilder.setLength(0);
                result.add(new PunctuationMark(character));
            }
        }
        if (!stringBuilder.isEmpty()){
            result.add(new Word(stringBuilder.toString().trim()));
        }
        logger.debug(STRING_SENTENCE_TO_SENTENCE_CONTENT_LIST_CONVERSION_ENDED);
        return result;
    }
}
