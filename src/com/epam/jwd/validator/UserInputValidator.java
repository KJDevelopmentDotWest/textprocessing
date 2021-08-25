package com.epam.jwd.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserInputValidator {

    private static final Logger logger = LogManager.getLogger(UserInputValidator.class);
    private static final String STRING_VALIDATION_ERROR = "Validation error: string instead of number found";

    public static boolean validateTaskType(String value){
        int temp;
        boolean result;
        try {
            temp = Integer.parseInt(value);
            result = temp > 0 && temp < 6;
        } catch (NumberFormatException e){
            logger.error(STRING_VALIDATION_ERROR);
            result = false;
        }
        return result;
    }
}
