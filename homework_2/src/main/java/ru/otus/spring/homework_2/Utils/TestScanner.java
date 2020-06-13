package ru.otus.spring.homework_2.Utils;

import java.util.List;
import java.util.Scanner;

public class TestScanner {

    public static int runScannerAnswerPerson(String question, List<String> listVariantAnswers) {
        System.out.print(question + "\n");
        dispalayListAnswers(listVariantAnswers);
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        return number;
    }

    public static void dispalayListAnswers(List<String> listVariantAnswers) {
        for (int i = 0; i < listVariantAnswers.size(); i++) {
            int numberAnswer = i + 1;
            System.out.print(numberAnswer + ")" + " " + listVariantAnswers.get(i) + "\n");
        }
    }

    public static void runScannerFioPerson(String question) {
        System.out.print(question + "\n");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
    }
}
