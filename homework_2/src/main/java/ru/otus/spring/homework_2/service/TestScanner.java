package ru.otus.spring.homework_2.service;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class TestScanner {

    public int runScannerAnswerPerson(String question, List<String> listVariantAnswers) {
        System.out.print(question + "\n");
        dispalayListAnswers(listVariantAnswers);
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt()) {
            System.out.println("Number answer, please!");
            scan.nextLine();
        }
        int number = scan.nextInt();
        return number;
    }

    private void dispalayListAnswers(List<String> listVariantAnswers) {
        for (int i = 0; i < listVariantAnswers.size(); i++) {
            int numberAnswer = i + 1;
            System.out.print(numberAnswer + ")" + " " + listVariantAnswers.get(i) + "\n");
        }
    }

    public void runScannerFioPerson(String question) {
        System.out.print(question + "\n");
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextInt()) {
            System.out.println("String english FIO, please!");
            scan.nextLine();
        }
    }
}
