package ru.otus.spring.homework_2.service;

import ru.otus.spring.homework_2.Utils.TestScanner;

public class ManagerAuthenticationUserImpl implements ManagerAuthenticationUser {

    public void showQuestionPersonalData() {
        TestScanner.runScannerFioPerson("Ваша фамилия?");
        TestScanner.runScannerFioPerson("Ваше имя?");
        TestScanner.runScannerFioPerson("Ваше отчество?");
    }
}
