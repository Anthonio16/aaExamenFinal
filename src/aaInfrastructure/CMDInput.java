package aaInfrastructure;
//Refactorización

import java.util.Scanner;

public class CMDInput {
    private static final Scanner sc = new Scanner(System.in);

    public static String readLine(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }
}