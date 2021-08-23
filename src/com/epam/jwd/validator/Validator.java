package com.epam.jwd.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Validator {

    private static final Logger logger = LogManager.getLogger(Validator.class);

    public static boolean validateTaskType(String value){
        int temp;
        boolean result;
        try {
            temp = Integer.parseInt(value);
            result = temp > 0 && temp < 17;
        } catch (NumberFormatException e){
            logger.error("Validation error: string instead of number found");
            result = false;
        }
        return result;
    }
}
