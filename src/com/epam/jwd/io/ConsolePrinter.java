package com.epam.jwd.io;

public class ConsolePrinter {

    private static final String START_MESSAGE = "Text Parser: ";
    private static final String OPTION_1 = "1 - Print original text";
    private static final String OPTION_2 = "2 - Print all sentences by words amount in ascending order";
    private static final String OPTION_3 = "3 - Find a word in the first sentence that is not in any of any others sentences (Register sensitive)";
    private static final String OPTION_4 = "4 - Swap first and last word in all sentences";
    private static final String OPTION_5 = "5 - Exit";
    private static final String DIVIDER = "==========================";


    public static void startMessage(){
        System.out.println(START_MESSAGE);
    }

    public static void loopMessage(){
        System.out.println(DIVIDER);
        System.out.println(OPTION_1);
        System.out.println(OPTION_2);
        System.out.println(OPTION_3);
        System.out.println(OPTION_4);
        System.out.println(OPTION_5);
    }

    public static void printMessage(String message){
        System.out.println(DIVIDER);
        System.out.println(message);
    }
}
