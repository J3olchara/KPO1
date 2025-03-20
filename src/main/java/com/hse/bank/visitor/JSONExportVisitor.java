package com.hse.bank.visitor;

import com.hse.bank.domain.BankAccount;
import com.hse.bank.domain.Category;
import com.hse.bank.domain.Operation;

public class JSONExportVisitor implements ExportVisitor {
    @Override
    public String exportBankAccount(BankAccount account) {
        return "{" +
                "\"id\":\"" + account.getId() + "\"," +
                "\"name\":\"" + account.getName() + "\"," +
                "\"balance\":" + account.getBalance() +
                "}";
    }

    @Override
    public String exportCategory(Category category) {
        return "{\"id\":\"" + category.getId() + "\", \"type\":\"" + category.getType() + "\", \"name\":\""
                + category.getName() + "\"}";
    }

    @Override
    public String exportOperation(Operation operation) {
        return "{\"id\":\"" + operation.getId() + "\", \"type\":\"" + operation.getType() + "\", \"bankAccountId\":\""
                + operation.getBankAccountId() + "\"," +
                " \"amount\":" + operation.getAmount() + ", \"date\":\"" + operation.getDate()
                + "\", \"description\":\"" + operation.getDescription() + "\"," +
                " \"categoryId\":\"" + operation.getCategoryId() + "\"}";
    }
}