package com.hse.bank.command;

import com.hse.bank.facade.BankAccountFacade;
import com.hse.bank.domain.BankAccount;

public class CreateAccountCommand implements Command {
    private final BankAccountFacade accountFacade;
    private final String name;
    private final double initialBalance;

    public CreateAccountCommand(BankAccountFacade accountFacade, String name, double initialBalance) {
        this.accountFacade = accountFacade;
        this.name = name;
        this.initialBalance = initialBalance;
    }

    @Override
    public void execute() {
        BankAccount account = accountFacade.createAccount(name, initialBalance);
        System.out.println("Создан счет: " + account);
    }
}