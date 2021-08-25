package com.epam.jwd.io;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import com.epam.jwd.exception.MyIOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInput {

    private static final String PATH = "res/text.txt";
    private static final String STRING_FILE_NOT_FOUND_EXCEPTION = "there is no such file";
    private static final String STRING_OTHER_IO_EXCEPTION = "other IO exception";

    public static String getFileAsString(){
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(PATH);
        try(InputStream inputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){

            String bufferLine;

            while ((bufferLine = bufferedReader.readLine()) != null){
                stringBuilder.append(bufferLine).append("\n");
            }
        } catch (FileNotFoundException e){
            throw new MyIOException(STRING_FILE_NOT_FOUND_EXCEPTION);
        } catch (IOException e){
            throw new MyIOException(STRING_OTHER_IO_EXCEPTION);
        }
        return stringBuilder.toString();
    }
}
