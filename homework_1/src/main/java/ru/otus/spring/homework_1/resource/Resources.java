package ru.otus.spring.homework_1.resource;

import java.io.IOException;
import java.util.Map;

public interface Resources {

    Map<Map<String, Integer>,Map<Integer, String >> readerResource() throws IOException;
}
