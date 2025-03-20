package com.hse.bank;

import com.hse.bank.util.InputUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void testFullApplicationFlow() {
        String simulatedInput = "1\n" + // создать счет
                "My Account\n" +
                "1000\n" +
                "2\n" + // создать категорию
                "1\n" + // тип INCOME
                "Salary\n" +
                "3\n" + // создать операцию
                "1\n" + // тип INCOME
                "dummyAccId\n" + // id счета (допустим, вводимый пользователем)
                "500\n" +
                "2023-10-10\n" +
                "Test Operation\n" +
                "dummyCatId\n" +
                "4\n" + // аналитика
                "2023-01-01\n" +
                "2023-12-31\n" +
                "5\n" + // группировка
                "1\n" + // тип INCOME
                "0\n"; // выход

        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputUtil.setScanner(new Scanner(System.in));

        App.main(new String[0]);

        String output = outContent.toString();
        assertTrue(output.contains("Создан"));
        assertTrue(output.contains("Создана категория"));
        assertTrue(output.contains("Создана операция"));
        assertTrue(output.contains("Разница доходов и расходов"));
        assertTrue(output.contains("Категория id:"));
        assertTrue(output.contains("Выход из приложения."));
    }
}