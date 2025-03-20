package com.hse.bank.proxy;

import com.hse.bank.domain.BankAccount;
import com.hse.bank.domain.Category;
import com.hse.bank.domain.Operation;
import com.hse.bank.domain.Category.CategoryType;
import com.hse.bank.domain.Operation.OperationType;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FinanceDataProxyTest {
    @Test
    public void testLoadAndGetData() {
        BankAccount account = new BankAccount("Proxy Acc", 800.0);
        Category category = new Category(CategoryType.EXPENSE, "Proxy Cat");
        Operation op = new Operation(OperationType.EXPENSE, account.getId(), 200.0, LocalDate.now(), "Proxy Op",
                category.getId());

        FinanceDataProxy proxy = new FinanceDataProxy();
        proxy.loadData(List.of(account), List.of(category), List.of(op));
        assertFalse(proxy.getAccounts().isEmpty());
        assertFalse(proxy.getCategories().isEmpty());
        assertFalse(proxy.getOperations().isEmpty());
    }

    @Test
    public void testUpdateData() {
        BankAccount account = new BankAccount("Initial Acc", 900.0);
        FinanceDataProxy proxy = new FinanceDataProxy();
        proxy.loadData(List.of(account), List.of(), List.of());
        account.deposit(100.0);
        proxy.updateAccount(account);
        BankAccount updated = proxy.getAccounts().stream().filter(a -> a.getId().equals(account.getId())).findFirst()
                .orElse(null);
        assertNotNull(updated);
        assertEquals(1000.0, updated.getBalance());
    }
}