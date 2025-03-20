package com.hse.bank.proxy;

import com.hse.bank.domain.BankAccount;
import com.hse.bank.domain.Category;
import com.hse.bank.domain.Operation;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FinanceDataProxy {
    private Map<String, BankAccount> cachedAccounts = new HashMap<>();
    private Map<String, Category> cachedCategories = new HashMap<>();
    private Map<String, Operation> cachedOperations = new HashMap<>();

    public void loadData(Collection<BankAccount> accounts, Collection<Category> categories,
            Collection<Operation> operations) {
        for (BankAccount account : accounts) {
            cachedAccounts.put(account.getId(), account);
        }
        for (Category category : categories) {
            cachedCategories.put(category.getId(), category);
        }
        for (Operation op : operations) {
            cachedOperations.put(op.getId(), op);
        }
    }

    public Collection<BankAccount> getAccounts() {
        return cachedAccounts.values();
    }

    public Collection<Category> getCategories() {
        return cachedCategories.values();
    }

    public Collection<Operation> getOperations() {
        return cachedOperations.values();
    }

    public void updateAccount(BankAccount account) {
        cachedAccounts.put(account.getId(), account);
    }

    public void updateCategory(Category category) {
        cachedCategories.put(category.getId(), category);
    }

    public void updateOperation(Operation operation) {
        cachedOperations.put(operation.getId(), operation);
    }
}