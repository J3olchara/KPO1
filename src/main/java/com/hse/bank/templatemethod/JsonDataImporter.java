package com.hse.bank.templatemethod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Arrays;

public class JsonDataImporter extends DataImporter {
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
        String trimmed = content.trim();
        if (trimmed.startsWith("[") && trimmed.endsWith("]")) {
            trimmed = trimmed.substring(1, trimmed.length() - 1);
        }
        return Arrays.asList(trimmed.split(","));
    }
}