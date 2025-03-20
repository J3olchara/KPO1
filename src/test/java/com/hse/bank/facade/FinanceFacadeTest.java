package com.hse.bank.facade;

import com.hse.bank.domain.BankAccount;
import com.hse.bank.domain.Category;
import com.hse.bank.domain.Category.CategoryType;
import com.hse.bank.domain.Operation.OperationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class FinanceFacadeTest {
    private BankAccountFacade accountFacade;
    private CategoryFacade categoryFacade;
    private OperationFacade operationFacade;
    private FinanceFacade financeFacade;

    @BeforeEach
    public void init() {
        accountFacade = new BankAccountFacade();
        categoryFacade = new CategoryFacade();
        operationFacade = new OperationFacade();
        financeFacade = new FinanceFacade(accountFacade, categoryFacade, operationFacade);
        BankAccount acc = accountFacade.createAccount("Main Account", 1000.0);
        Category incomeCat = categoryFacade.createCategory(CategoryType.INCOME, "Salary");
        Category expenseCat = categoryFacade.createCategory(CategoryType.EXPENSE, "Food");
        LocalDate today = LocalDate.now();
        operationFacade.createOperation(OperationType.INCOME, acc.getId(), 500.0, today, "Monthly salary",
                incomeCat.getId());
        operationFacade.createOperation(OperationType.EXPENSE, acc.getId(), 150.0, today, "Dinner", expenseCat.getId());
    }

    @Test
    public void testIncomeExpenseDifference() {
        LocalDate start = LocalDate.now().minusDays(1);
        LocalDate end = LocalDate.now().plusDays(1);
        double diff = financeFacade.getIncomeExpenseDifference(start, end);
        assertEquals(350.0, diff);
    }

    @Test
    public void testGroupOperationsByCategory() {
        Map<String, Double> incomeGroup = financeFacade.groupOperationsByCategory(OperationType.INCOME);
        Map<String, Double> expenseGroup = financeFacade.groupOperationsByCategory(OperationType.EXPENSE);
        assertEquals(1, incomeGroup.size());
        assertEquals(500.0, incomeGroup.values().stream().findFirst().get());
        assertEquals(1, expenseGroup.size());
        assertEquals(150.0, expenseGroup.values().stream().findFirst().get());
    }
}