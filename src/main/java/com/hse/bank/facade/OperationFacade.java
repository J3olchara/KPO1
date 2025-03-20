package com.hse.bank.facade;

import com.hse.bank.domain.Operation;
import com.hse.bank.factory.DomainFactory;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class OperationFacade {
    private final Map<String, Operation> operations = new HashMap<>();

    public Operation createOperation(Operation.OperationType type, String bankAccountId, double amount, LocalDate date,
            String description, String categoryId) {
        Operation operation = DomainFactory.createOperation(type, bankAccountId, amount, date, description, categoryId);
        operations.put(operation.getId(), operation);
        return operation;
    }

    public Operation getOperation(String id) {
        return operations.get(id);
    }

    public void deleteOperation(String id) {
        operations.remove(id);
    }

    public Collection<Operation> getAllOperations() {
        return operations.values();
    }
}