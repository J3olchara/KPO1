package com.hse.bank;

import com.hse.bank.util.InputUtil;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @Test
    public void testAppExitImmediately() {
        String input = "0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputUtil.setScanner(new Scanner(System.in));
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
        App.main(new String[0]);
        String output = outStream.toString();
        assertTrue(output.contains("Выход из приложения."));
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void testAppCreateAccountFlow() {
        String input = "1\nTest Account\n1000\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputUtil.setScanner(new Scanner(System.in));
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
        App.main(new String[0]);
        String output = outStream.toString();
        assertTrue(output.contains("Создан"));
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void testAppInvalidOption() {
        String input = "99\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputUtil.setScanner(new Scanner(System.in));
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
        App.main(new String[0]);
        String output = outStream.toString();
        assertTrue(output.contains("Неверный выбор"));
        System.setIn(originalIn);
        System.setOut(originalOut);
    }
}