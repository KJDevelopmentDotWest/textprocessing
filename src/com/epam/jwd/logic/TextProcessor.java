package com.epam.jwd.logic;

import com.epam.jwd.text.Text;
import com.epam.jwd.text.Word;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

public class TextProcessor {
    static void sortByWordsAmount(Text source){
        Collections.sort(source.getAsSentences());
    }

    static String findUniqueWord(Text source){
        final String[] result = new String[1];

        source.getFirstSentence().getAsParts().forEach(sentencePart -> {
            if (sentencePart instanceof Word){
                AtomicBoolean flag = new AtomicBoolean(true);
                for(int i = 1; i < source.getAsSentences().size(); i++){
                    source.getAsSentences().get(i).getAsParts().forEach(innerSentencePart -> {
                        if (innerSentencePart instanceof Word){
                            if (sentencePart.equals(innerSentencePart)){
                                flag.set(false);
                            }
                        }
                    });
                }
                if (flag.get()){
                    result[0] = sentencePart.toString();
                } else {
                    flag.set(true);
                }
            }
        });

        return result[0];
    }

    static void swapFirstAndLast(Text source){
        source.getAsSentences().forEach(sentence -> {
            Word buffer = sentence.getFirstWord();
            sentence.getAsParts().set(sentence.getAsParts().indexOf(sentence.getFirstWord()), sentence.getLastWord());
            sentence.getAsParts().set(sentence.getAsParts().lastIndexOf(sentence.getLastWord()), buffer);
        });
    }
}
