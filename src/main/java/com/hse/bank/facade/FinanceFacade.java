package com.hse.bank.facade;

import com.hse.bank.domain.Operation;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FinanceFacade {
    private final BankAccountFacade accountFacade;
    private final CategoryFacade categoryFacade;
    private final OperationFacade operationFacade;

    public FinanceFacade(BankAccountFacade accountFacade, CategoryFacade categoryFacade,
            OperationFacade operationFacade) {
        this.accountFacade = accountFacade;
        this.categoryFacade = categoryFacade;
        this.operationFacade = operationFacade;
    }

    public double getIncomeExpenseDifference(LocalDate start, LocalDate end) {
        double income = 0.0;
        double expense = 0.0;
        Collection<Operation> operations = operationFacade.getAllOperations();
        for (Operation op : operations) {
            if (!op.getDate().isBefore(start) && !op.getDate().isAfter(end)) {
                if (op.getType() == Operation.OperationType.INCOME) {
                    income += op.getAmount();
                } else {
                    expense += op.getAmount();
                }
            }
        }
        return income - expense;
    }

    public Map<String, Double> groupOperationsByCategory(Operation.OperationType type) {
        Map<String, Double> result = new HashMap<>();
        for (Operation op : operationFacade.getAllOperations()) {
            if (op.getType() == type) {
                String categoryId = op.getCategoryId();
                result.put(categoryId, result.getOrDefault(categoryId, 0.0) + op.getAmount());
            }
        }
        return result;
    }
}