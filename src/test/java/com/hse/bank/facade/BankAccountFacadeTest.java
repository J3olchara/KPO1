package com.hse.bank.facade;

import com.hse.bank.domain.BankAccount;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountFacadeTest {
    @Test
    public void testCreateAndGetAccount() {
        BankAccountFacade facade = new BankAccountFacade();
        BankAccount account = facade.createAccount("Test Acc", 200.0);
        assertNotNull(account);
        BankAccount fetched = facade.getAccount(account.getId());
        assertEquals(account.getId(), fetched.getId());
    }

    @Test
    public void testEditAccount() {
        BankAccountFacade facade = new BankAccountFacade();
        BankAccount account = facade.createAccount("Old Name", 300.0);
        facade.editAccount(account.getId(), "New Name");
        BankAccount updated = facade.getAccount(account.getId());
        assertEquals("New Name", updated.getName());
    }

    @Test
    public void testDeleteAccount() {
        BankAccountFacade facade = new BankAccountFacade();
        BankAccount account = facade.createAccount("Acc", 100.0);
        facade.deleteAccount(account.getId());
        assertNull(facade.getAccount(account.getId()));
    }
}