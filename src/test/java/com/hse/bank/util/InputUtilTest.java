package com.hse.bank.util;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class InputUtilTest {
    @Test
    public void testReadString() {
        String simulatedInput = "Hello World\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputUtil.setScanner(new Scanner(System.in));
        String result = InputUtil.readString("Введите строку: ");
        assertEquals("Hello World", result);
    }

    @Test
    public void testReadInt() {
        String simulatedInput = "123\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputUtil.setScanner(new Scanner(System.in));
        int result = InputUtil.readInt("Введите число: ");
        assertEquals(123, result);
    }

    @Test
    public void testReadDouble() {
        String simulatedInput = "3.1415\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputUtil.setScanner(new Scanner(System.in));
        double result = InputUtil.readDouble("Введите дробное число: ");
        assertEquals(3.1415, result, 0.00001);
    }
}