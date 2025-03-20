package com.hse.bank.visitor;

import com.hse.bank.domain.BankAccount;
import com.hse.bank.domain.Category;
import com.hse.bank.domain.Operation;

public interface ExportVisitor {
    String exportBankAccount(BankAccount account);

    String exportCategory(Category category);

    String exportOperation(Operation operation);
}