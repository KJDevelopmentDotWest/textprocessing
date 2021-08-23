package com.epam.jwd.io;

import java.util.Scanner;

public class UserInput {
    public static String getUserInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
