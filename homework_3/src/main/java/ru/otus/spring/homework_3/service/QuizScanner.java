package ru.otus.spring.homework_3.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class QuizScanner {

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

    public void runScannerFioPerson(String question) {
        System.out.print(question + "\n");
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextInt()) {
            System.out.println("String FIO, please!");
            scan.nextLine();
        }
    }

    private void dispalayListAnswers(List<String> listVariantAnswers) {
        for (int i = 0; i < listVariantAnswers.size(); i++) {
            int numberAnswer = i + 1;
            System.out.print(numberAnswer + ")" + " " + listVariantAnswers.get(i) + "\n");
        }
    }

}
