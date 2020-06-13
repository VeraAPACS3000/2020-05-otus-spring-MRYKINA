package ru.otus.spring.homework_2.Utils.Fake;

import ru.otus.spring.homework_2.Utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UtilIOFileImplFake implements Utils {

    private final String nameFile;

    public UtilIOFileImplFake(String inNameFile) {
        this.nameFile = inNameFile;
    }

    public List<String> getListStringsFromFile() throws IOException {
        List<String> listString = new ArrayList<String>();
        listString.add(0, "Question");
        listString.add(1, "How much will it be 2 plus 2?");
        listString.add(2, "Answer");
        listString.add(3, "4");
        listString.add(4, "Answer");
        listString.add(5, "2");
        listString.add(6, "Answer");
        listString.add(7, "22");
        listString.add(8, "TrueAnswer");
        listString.add(9, "1");
        listString.add(10, "END");;
        return listString;
    }

}
