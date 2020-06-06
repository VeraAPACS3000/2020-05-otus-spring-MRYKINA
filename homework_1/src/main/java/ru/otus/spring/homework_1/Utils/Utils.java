package ru.otus.spring.homework_1.Utils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Utils {
    public static Map<String, Integer> getMapAnswerFromInput(Map<Map<String, Integer>, Map<Integer, String>> inMapQuestionAnswer, Collection<Map<Integer, String>> mapQuestionCollection) {

        Set<Map.Entry<Map<String, Integer>, Map<Integer, String>>> entrySetAnswer = inMapQuestionAnswer.entrySet();

        Map<String, Integer> mapAnswer = null;

        for (Map.Entry<Map<String, Integer>, Map<Integer, String>> pair : entrySetAnswer) {
            if (mapQuestionCollection.contains(pair.getValue())) {
                mapAnswer = pair.getKey();
            }
        }

        return mapAnswer;
    }
}
