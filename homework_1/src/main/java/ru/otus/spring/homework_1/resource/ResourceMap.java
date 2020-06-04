package ru.otus.spring.homework_1.resource;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ResourceMap implements Resources {

    private String csvFile = null;

    ResourceMap(String pathCsvFile) {
        csvFile = pathCsvFile;
    }

    @Override
    public Map<Map<String, Integer>,Map<Integer, String >> readerResource() throws IOException {

        String line = "";
        String cvsSplitBy = ",";
        String question = "";
        String answer = "";

        final int indexRow = 1;
        final int indexQuestion = 0;

        int indexStringReader = 0;
        int indexCountQuestion = 0;

        BufferedReader bufferedReader = null;
        String[] stringQuestionAnswer = null;

        Map<Integer, String> mapQuestion = new HashMap<Integer, String>();
        Map<String, Integer> mapAnswer = new HashMap<String, Integer>();

        Map<Map<String, Integer>,Map<Integer, String >> mapQuestionMapAnswer =
                new HashMap<Map<String, Integer>, Map<Integer, String >>();

        Set<String> setQuestion = new HashSet<String>();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("testing1.csv");

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = bufferedReader.readLine()) != null) {
                stringQuestionAnswer = line.split(cvsSplitBy);

                answer = stringQuestionAnswer[indexRow];
                question = stringQuestionAnswer[indexQuestion];

                if (setQuestion.add(question)) {
                    mapQuestion.put(indexStringReader, question);
                    mapAnswer.put(answer, indexStringReader);
                    indexCountQuestion = indexStringReader;
                    indexStringReader++;

                }else{
                    mapAnswer.put(answer, indexCountQuestion);
                }
            }

            mapQuestionMapAnswer.put(mapAnswer, mapQuestion);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return mapQuestionMapAnswer;
    }
}
