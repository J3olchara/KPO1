package com.hse.bank.command;

import com.hse.bank.domain.BankAccount;
import com.hse.bank.domain.Category;
import com.hse.bank.domain.Operation;
import com.hse.bank.facade.BankAccountFacade;
import com.hse.bank.facade.CategoryFacade;
import com.hse.bank.facade.OperationFacade;
import com.hse.bank.domain.Category.CategoryType;
import com.hse.bank.domain.Operation.OperationType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testCreateAccountCommandAndDecorator() {
        BankAccountFacade accountFacade = new BankAccountFacade();
        String accName = "Command Account";
        double initBalance = 1000.0;
        CreateAccountCommand cmd = new CreateAccountCommand(accountFacade, accName, initBalance);
        // без декоратора
        cmd.execute();
        String output = outContent.toString();
        assertTrue(output.contains("Создан"));
        // Проверим состояние фасада
        BankAccount account = accountFacade.getAllAccounts().stream().findFirst().orElse(null);
        assertNotNull(account);
        assertEquals(accName, account.getName());
        // Очистим поток вывода
        outContent.reset();
        // Проверка декоратора (в выводе должно появиться слово "Время исполнения:")
        TimeMeasuredCommandDecorator decoratedCmd = new TimeMeasuredCommandDecorator(cmd);
        decoratedCmd.execute();
        output = outContent.toString();
        assertTrue(output.contains("Время исполнения:"));
    }

    @Test
    public void testCreateCategoryCommandAndDecorator() {
        CategoryFacade categoryFacade = new CategoryFacade();
        String catName = "Command Category";
        CreateCategoryCommand cmd = new CreateCategoryCommand(categoryFacade, CategoryType.EXPENSE, catName);
        cmd.execute();
        String output = outContent.toString();
        assertTrue(output.contains("Создана категория"));
        Category category = categoryFacade.getAllCategories().stream().findFirst().orElse(null);
        assertNotNull(category);
        assertEquals(catName, category.getName());
        outContent.reset();
        TimeMeasuredCommandDecorator decoratedCmd = new TimeMeasuredCommandDecorator(cmd);
        decoratedCmd.execute();
        output = outContent.toString();
        assertTrue(output.contains("Время исполнения:"));
    }

    @Test
    public void testCreateOperationCommandAndDecorator() {
        OperationFacade operationFacade = new OperationFacade();
        String bankAccId = "acc-for-op";
        String catId = "cat-for-op";
        LocalDate date = LocalDate.now();
        double amount = 500.0;
        String description = "Command Operation";
        CreateOperationCommand cmd = new CreateOperationCommand(operationFacade, OperationType.INCOME, bankAccId,
                amount, date, description, catId);
        cmd.execute();
        String output = outContent.toString();
        assertTrue(output.contains("Создана операция"));
        Operation op = operationFacade.getAllOperations().stream().findFirst().orElse(null);
        assertNotNull(op);
        assertEquals(description, op.getDescription());
        outContent.reset();
        TimeMeasuredCommandDecorator decoratedCmd = new TimeMeasuredCommandDecorator(cmd);
        decoratedCmd.execute();
        output = outContent.toString();
        assertTrue(output.contains("Время исполнения:"));
    }
}