package com.epam.jwd.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyIOException extends RuntimeException{

    private static final Logger logger = LogManager.getLogger(MyIOException.class);
    public static final String STRING_UNKNOWN_IO_EXCEPTION = "unknown io exception";

    public MyIOException(){
        logger.error(STRING_UNKNOWN_IO_EXCEPTION);
    }

    public MyIOException(String message){
        logger.error(message);
    }
}
