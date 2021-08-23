package com.epam.jwd.logic;

import com.epam.jwd.io.FileInput;
import com.epam.jwd.io.UI;
import com.epam.jwd.io.UserInput;
import com.epam.jwd.text.Text;
import com.epam.jwd.text.Word;
import com.epam.jwd.util.Performer;
import com.epam.jwd.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Logic {

    private final Text text;
    private static Map<Integer, Performer> taskTypeMap;
    private static final Logger logger = LogManager.getLogger(Logic.class);

    public Logic(){
        text = new Text(FileInput.getFileAsString());
        taskTypeMap = new HashMap<>();
        taskTypeMap.put(1, this::firstOption);
        taskTypeMap.put(2, this::secondOption);
        taskTypeMap.put(3, this::thirdOption);
        taskTypeMap.put(4, this::fourthOption);
        taskTypeMap.put(5, this::fifthOption);
    }

    public void start(){
        UI.startMessage();
        loop();
    }

    private void loop(){
        UI.loopMessage();
        String callback = UserInput.getUserInput();
        if (Validator.validateTaskType(callback)){
            taskTypeMap.get(Integer.parseInt(callback)).execute();
        } else {
            loop();
        }
    }
    private void firstOption(){ //temporary name
        logger.debug("First option chosen");
        System.out.println(text);
    }

    private void secondOption(){ //temporary name
        logger.debug("Second option chosen");
        Text textCopy = text.copy();
        Collections.sort(textCopy.getAsSentences());
        System.out.println(textCopy);
    }

    private void thirdOption(){ //temporary name
        logger.debug("Third option chosen");
        final String[] result = new String[1];

        text.getFirstSentence().getAsParts().forEach(sentencePart -> {
            if (sentencePart instanceof Word){
                AtomicBoolean flag = new AtomicBoolean(true);
                for(int i = 1; i < text.getAsSentences().size(); i++){
                    text.getAsSentences().get(i).getAsParts().forEach(innerSentencePart -> {
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
        if (result[0] != null){
            UI.printMessage(result[0]);
        } else {
            UI.printMessage("There is no such word");
        }
    }

    private void fourthOption(){ //temporary name
        logger.debug("Fourth option chosen");
        Text textCopy = text.copy();
        textCopy.getAsSentences().forEach(sentence -> {
            Word buffer = sentence.getFirstWord();
            sentence.getAsParts().set(sentence.getAsParts().indexOf(sentence.getFirstWord()), sentence.getLastWord());
            sentence.getAsParts().set(sentence.getAsParts().lastIndexOf(sentence.getLastWord()), buffer);
        });
        UI.printMessage(textCopy.toString());
    }

    private void fifthOption(){ //temporary name

    }
}
