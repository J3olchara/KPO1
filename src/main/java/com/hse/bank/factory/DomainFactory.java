package com.hse.bank.factory;

import com.hse.bank.domain.BankAccount;
import com.hse.bank.domain.Category;
import com.hse.bank.domain.Operation;
import com.hse.bank.domain.Category.CategoryType;
import com.hse.bank.domain.Operation.OperationType;
import java.time.LocalDate;

public class DomainFactory {
    // Фабричный метод для создания счетов
    public static BankAccount createBankAccount(String name, double initialBalance) {
        return new BankAccount(name, initialBalance);
    }

    // Фабричный метод для создания категории
    public static Category createCategory(CategoryType type, String name) {
        return new Category(type, name);
    }

    // Фабричный метод для создания операции с валидацией
    public static Operation createOperation(OperationType type, String bankAccountId, double amount, LocalDate date,
            String description, String categoryId) {
        return new Operation(type, bankAccountId, amount, date, description, categoryId);
    }
}
