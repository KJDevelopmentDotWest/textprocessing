package com.epam.jwd.logic;

import com.epam.jwd.io.FileInput;
import com.epam.jwd.io.ConsolePrinter;
import com.epam.jwd.io.UserInput;
import com.epam.jwd.text.Text;
import com.epam.jwd.util.Performer;
import com.epam.jwd.validator.UserInputValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Logic {

    private static final Logger logger = LogManager.getLogger(Logic.class);
    private static final String STRING_TEXT_PRINT_OPTION_CHOSEN = "text print option chosen";
    private static final String STRING_SORT_BY_WORDS_AMOUNT_OPTION_CHOSEN = "sort by words amount option chosen";
    private static final String STRING_FIND_UNIQUE_WORD_OPTION_CHOSEN = "find unique word option chosen";
    private static final String STRING_THERE_IS_NO_SUCH_WORD = "There is no such word";
    private static final String STRING_SWAP_OPTION_CHOSEN = "swap option chosen";
    private static final String STRING_PARSER_TERMINATED = "Parser Terminated";

    private final Text text;
    private final Map<Integer, Performer> taskTypeToPerformerMap;

    public Logic(){
        text = new Text(FileInput.getFileAsString());
        taskTypeToPerformerMap = new HashMap<>();
        taskTypeToPerformerMap.put(1, this::printOriginalTextOption);
        taskTypeToPerformerMap.put(2, this::sortByWordsAmountOption);
        taskTypeToPerformerMap.put(3, this::findUniqueWordOption);
        taskTypeToPerformerMap.put(4, this::swapOption);
        taskTypeToPerformerMap.put(5, this::exitOption);
    }

    public void start(){
        ConsolePrinter.startMessage();
        loop();
    }

    private void loop(){
        String callback;
        do{
            ConsolePrinter.loopMessage();
            callback = UserInput.getUserInput();
        } while (!UserInputValidator.validateTaskType(callback));
        taskTypeToPerformerMap.get(Integer.parseInt(callback)).execute();
    }

    private void printOriginalTextOption(){
        logger.debug(STRING_TEXT_PRINT_OPTION_CHOSEN);
        System.out.println(text);
        loop();
    }

    private void sortByWordsAmountOption(){
        logger.debug(STRING_SORT_BY_WORDS_AMOUNT_OPTION_CHOSEN);
        Text textCopy = text.copy();
        TextProcessor.sortByWordsAmount(textCopy);
        ConsolePrinter.printMessage(textCopy.toString());
        loop();
    }

    private void findUniqueWordOption(){
        logger.debug(STRING_FIND_UNIQUE_WORD_OPTION_CHOSEN);
        ConsolePrinter.printMessage(
                Objects.requireNonNullElse(
                        TextProcessor.findUniqueWord(text), STRING_THERE_IS_NO_SUCH_WORD
                )
        );
        loop();
    }

    private void swapOption(){
        logger.debug(STRING_SWAP_OPTION_CHOSEN);
        Text textCopy = text.copy();
        TextProcessor.swapFirstAndLast(textCopy);
        ConsolePrinter.printMessage(textCopy.toString());
        loop();
    }

    private void exitOption(){
        logger.debug(STRING_PARSER_TERMINATED);
        ConsolePrinter.printMessage(STRING_PARSER_TERMINATED);
    }
}
