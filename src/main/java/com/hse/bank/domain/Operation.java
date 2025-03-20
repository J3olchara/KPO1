package com.hse.bank.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Operation {
    public enum OperationType {
        INCOME, EXPENSE
    }

    private final String id;
    private final OperationType type;
    private final String bankAccountId;
    private final double amount;
    private final LocalDate date;
    private final String description;
    private final String categoryId;

    public Operation(OperationType type, String bankAccountId, double amount, LocalDate date, String description,
            String categoryId) {
        // Предотвращение создания операции с отрицательной суммой
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма операции не может быть отрицательной");
        }
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.bankAccountId = bankAccountId;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public OperationType getType() {
        return type;
    }

    public String getBankAccountId() {
        return bankAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", bankAccountId='" + bankAccountId + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", categoryId='" + categoryId + '\'' +
                '}';
    }
}