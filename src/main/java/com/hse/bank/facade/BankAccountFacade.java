package com.hse.bank.facade;

import com.hse.bank.domain.BankAccount;
import com.hse.bank.factory.DomainFactory;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BankAccountFacade {
    // in-memory хранение для демонстрации
    private final Map<String, BankAccount> accounts = new HashMap<>();

    // Создание нового счета
    public BankAccount createAccount(String name, double initialBalance) {
        BankAccount account = DomainFactory.createBankAccount(name, initialBalance);
        accounts.put(account.getId(), account);
        return account;
    }

    // Получение счета по id
    public BankAccount getAccount(String id) {
        return accounts.get(id);
    }

    // Редактирование счета (пример: изменение имени)
    public void editAccount(String id, String newName) {
        BankAccount account = accounts.get(id);
        if (account != null) {
            account.setName(newName);
        }
    }

    // Удаление счета
    public void deleteAccount(String id) {
        accounts.remove(id);
    }

    // Получение всех счетов
    public Collection<BankAccount> getAllAccounts() {
        return accounts.values();
    }
}