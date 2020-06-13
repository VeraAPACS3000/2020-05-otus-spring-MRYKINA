package ru.otus.spring.homework_2.service;

import ru.otus.spring.homework_2.Utils.TestScanner;

public class ManagerAuthenticationUserImpl implements ManagerAuthenticationUser {

    public void showQuestionPersonalData() {
        TestScanner.runScannerFioPerson("What's your last name?");
        TestScanner.runScannerFioPerson("What's your first name?");
        TestScanner.runScannerFioPerson("What's your middle name?");
    }
}
