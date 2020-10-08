package ru.otus.spring.homework10.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {
    public static String toJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
