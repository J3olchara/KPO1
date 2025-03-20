package com.hse.bank.domain;

import java.util.UUID;

public class BankAccount {
    private final String id;
    private String name;
    private double balance;

    public BankAccount(String name, double initialBalance) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.balance = initialBalance;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной");
        }
        if (balance < amount) {
            throw new IllegalArgumentException("Недостаточно средств");
        }
        balance -= amount;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}