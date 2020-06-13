package ru.otus.spring.homework_2.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//@Component
public class UtilIOFileImpl implements Utils{
    private final String nameFile;

    public UtilIOFileImpl(String inNameFile) {
        this.nameFile = inNameFile;
    }

    private File loadFileFromResources() {
        return new File(getClass().getClassLoader().getResource(nameFile).getFile());
    }

    private List<String> readFromFile(File file) throws IOException {
        List<String> listResultStrings = new ArrayList<String>();
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    listResultStrings.add(scanner.nextLine());
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        return listResultStrings;
    }

    public List<String> getListStringsFromFile() throws IOException {
        List<String> listResult = readFromFile(loadFileFromResources());
        return listResult;
    }
}
