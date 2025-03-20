package com.hse.bank.templatemethod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Arrays;

public class YamlDataImporter extends DataImporter {
    @Override
    protected String readFile(String filePath) {
        try {
            return Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected List<String> parseData(String content) {
        String[] lines = content.split("\n");
        return Arrays.asList(lines);
    }
}