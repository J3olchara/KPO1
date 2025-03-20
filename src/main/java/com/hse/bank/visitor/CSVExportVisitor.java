package com.hse.bank.visitor;

import com.hse.bank.domain.BankAccount;
import com.hse.bank.domain.Category;
import com.hse.bank.domain.Operation;

public class CSVExportVisitor implements ExportVisitor {
    @Override
    public String exportBankAccount(BankAccount account) {
        return account.getId() + "," + account.getName() + "," + account.getBalance();
    }

    @Override
    public String exportCategory(Category category) {
        return category.getId() + "," + category.getType() + "," + category.getName();
    }

    @Override
    public String exportOperation(Operation operation) {
        return operation.getId() + "," + operation.getType() + "," + operation.getBankAccountId() + "," +
                operation.getAmount() + "," + operation.getDate() + "," + operation.getDescription() + "," +
                operation.getCategoryId();
    }
}