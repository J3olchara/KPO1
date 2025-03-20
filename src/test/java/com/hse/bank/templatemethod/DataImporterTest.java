package com.hse.bank.templatemethod;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;

public class DataImporterTest {
    @Test
    public void testCsvDataImporter() throws IOException {
        Path tempFile = Files.createTempFile("test", ".csv");
        Files.writeString(tempFile, "line1\nline2\nline3");
        CsvDataImporter importer = new CsvDataImporter();
        List<String> data = importer.importData(tempFile.toAbsolutePath().toString());
        assertEquals(3, data.size());
        Files.deleteIfExists(tempFile);
    }

    @Test
    public void testJsonDataImporter() throws IOException {
        Path tempFile = Files.createTempFile("test", ".json");
        Files.writeString(tempFile, "[\"item1\",\"item2\"]");
        JsonDataImporter importer = new JsonDataImporter();
        List<String> data = importer.importData(tempFile.toAbsolutePath().toString());
        assertTrue(data.size() >= 1);
        Files.deleteIfExists(tempFile);
    }

    @Test
    public void testYamlDataImporter() throws IOException {
        Path tempFile = Files.createTempFile("test", ".yaml");
        Files.writeString(tempFile, "key1: value1\nkey2: value2");
        YamlDataImporter importer = new YamlDataImporter();
        List<String> data = importer.importData(tempFile.toAbsolutePath().toString());
        assertEquals(2, data.size());
        Files.deleteIfExists(tempFile);
    }
}