package com.hse.bank.domain;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class OperationTest {
    @Test
    public void testOperationCreation() {
        LocalDate today = LocalDate.now();
        Operation op = new Operation(Operation.OperationType.INCOME, "account-id", 200.0, today, "Salary",
                "category-id");
        assertNotNull(op.getId());
        assertEquals(Operation.OperationType.INCOME, op.getType());
        assertEquals("account-id", op.getBankAccountId());
        assertEquals(200.0, op.getAmount());
        assertEquals(today, op.getDate());
        assertEquals("Salary", op.getDescription());
        assertEquals("category-id", op.getCategoryId());
    }

    @Test
    public void testNegativeAmountThrowsException() {
        LocalDate today = LocalDate.now();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Operation(Operation.OperationType.EXPENSE, "account-id", -100.0, today, "Negative", "category-id");
        });
        assertTrue(exception.getMessage().contains("отрицательной"));
    }
}