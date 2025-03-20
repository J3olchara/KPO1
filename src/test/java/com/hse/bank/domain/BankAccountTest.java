package com.hse.bank.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {
    @Test
    public void testDepositAndWithdraw() {
        BankAccount account = new BankAccount("Test Account", 100.0);
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance());
        account.withdraw(30.0);
        assertEquals(120.0, account.getBalance());
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        BankAccount account = new BankAccount("Test Account", 100.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(150.0);
        });
        assertTrue(exception.getMessage().contains("Недостаточно средств"));
    }

    @Test
    public void testNegativeDeposit() {
        BankAccount account = new BankAccount("Test Account", 100.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-10.0);
        });
        assertTrue(exception.getMessage().contains("положительной"));
    }
}