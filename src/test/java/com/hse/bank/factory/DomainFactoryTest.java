package com.hse.bank.factory;

import com.hse.bank.domain.BankAccount;
import com.hse.bank.domain.Category;
import com.hse.bank.domain.Operation;
import com.hse.bank.domain.Category.CategoryType;
import com.hse.bank.domain.Operation.OperationType;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class DomainFactoryTest {
    @Test
    public void testCreateBankAccount() {
        BankAccount account = DomainFactory.createBankAccount("Factory Account", 500.0);
        assertNotNull(account.getId());
        assertEquals("Factory Account", account.getName());
        assertEquals(500.0, account.getBalance());
    }

    @Test
    public void testCreateCategory() {
        Category category = DomainFactory.createCategory(CategoryType.INCOME, "Salary");
        assertNotNull(category.getId());
        assertEquals(CategoryType.INCOME, category.getType());
        assertEquals("Salary", category.getName());
    }

    @Test
    public void testCreateOperation() {
        LocalDate date = LocalDate.now();
        Operation op = DomainFactory.createOperation(OperationType.EXPENSE, "acc-id", 100.0, date, "Groceries",
                "cat-id");
        assertNotNull(op.getId());
        assertEquals(OperationType.EXPENSE, op.getType());
        assertEquals("acc-id", op.getBankAccountId());
        assertEquals(100.0, op.getAmount());
        assertEquals(date, op.getDate());
        assertEquals("Groceries", op.getDescription());
        assertEquals("cat-id", op.getCategoryId());
    }
}