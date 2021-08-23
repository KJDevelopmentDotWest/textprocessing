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

    public static List<Sentence> textToSentenceList(String text){
        logger.debug("Text to Sentence list conversion started");
        List<Sentence> result = new ArrayList<>();

        AtomicInteger counter = new AtomicInteger(0);
        StringBuilder sb = new StringBuilder();

        List<String> stringList = Arrays.asList(text.split(REGEX_TEXT_TO_SENTENCE));

        stringList.forEach(string -> {
            if(!string.contains("{") && counter.get() == 0){
                result.add(new Sentence(string));
            } else {
                sb.append(string).append("\n");
                if (string.contains("{")) counter.getAndIncrement();
                if (string.contains("}")) counter.getAndDecrement();
                if (counter.get() == 0) {
                    result.add(new Sentence(sb.toString().trim()));
                    sb.setLength(0);
                }
            }
        });

        logger.debug("Text to Sentence list conversion ended");
        return result;
    }

    public static List<SentencePart> sentenceToSentenceContentList(String sentence){
        logger.debug("Sentence to SentenceContent list conversion started");
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
                result.add(new Symbol(character));
            }
        }
        if (!stringBuilder.isEmpty()){
            result.add(new Word(stringBuilder.toString().trim()));
        }
        logger.debug("Sentence to SentenceContent list conversion ended");
        return result;
    }
}
