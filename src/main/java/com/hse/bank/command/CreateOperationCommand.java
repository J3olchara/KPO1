package com.hse.bank.command;

import com.hse.bank.facade.OperationFacade;
import com.hse.bank.domain.Operation;
import java.time.LocalDate;

public class CreateOperationCommand implements Command {
    private final OperationFacade operationFacade;
    private final Operation.OperationType type;
    private final String bankAccountId;
    private final double amount;
    private final LocalDate date;
    private final String description;
    private final String categoryId;

    public CreateOperationCommand(OperationFacade operationFacade, Operation.OperationType type, String bankAccountId,
            double amount, LocalDate date, String description, String categoryId) {
        this.operationFacade = operationFacade;
        this.type = type;
        this.bankAccountId = bankAccountId;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.categoryId = categoryId;
    }

    @Override
    public void execute() {
        Operation op = operationFacade.createOperation(type, bankAccountId, amount, date, description, categoryId);
        System.out.println("Создана операция: " + op);
    }
}