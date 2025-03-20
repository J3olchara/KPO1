package com.hse.bank.visitor;

import com.hse.bank.domain.BankAccount;
import com.hse.bank.domain.Category;
import com.hse.bank.domain.Operation;

public class YamlExportVisitor implements ExportVisitor {
    @Override
    public String exportBankAccount(BankAccount account) {
        return "id: " + account.getId() + "\nname: " + account.getName() + "\nbalance: " + account.getBalance();
    }

    @Override
    public String exportCategory(Category category) {
        return "id: " + category.getId() + "\ntype: " + category.getType() + "\nname: " + category.getName();
    }

    @Override
    public String exportOperation(Operation operation) {
        return "id: " + operation.getId() +
                "\ntype: " + operation.getType() +
                "\nbankAccountId: " + operation.getBankAccountId() +
                "\namount: " + operation.getAmount() +
                "\ndate: " + operation.getDate() +
                "\ndescription: " + operation.getDescription() +
                "\ncategoryId: " + operation.getCategoryId();
    }
}