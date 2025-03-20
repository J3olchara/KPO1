package com.hse.bank.templatemethod;

import java.util.List;

public abstract class DataImporter {
    public final List<String> importData(String filePath) {
        String content = readFile(filePath);
        return parseData(content);
    }

    protected abstract String readFile(String filePath);

    protected abstract List<String> parseData(String content);
}