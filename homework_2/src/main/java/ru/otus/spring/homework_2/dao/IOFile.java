package ru.otus.spring.homework_2.dao;

import java.io.IOException;
import java.util.List;

public interface IOFile {

    public List<String> getListStringsFromFile() throws IOException;

}
