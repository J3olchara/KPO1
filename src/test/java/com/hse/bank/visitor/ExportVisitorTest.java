package com.hse.bank.visitor;

import com.hse.bank.domain.BankAccount;
import com.hse.bank.domain.Category;
import com.hse.bank.domain.Operation;
import com.hse.bank.domain.Category.CategoryType;
import com.hse.bank.domain.Operation.OperationType;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ExportVisitorTest {
    @Test
    public void testCSVExportVisitor() {
        CSVExportVisitor visitor = new CSVExportVisitor();
        BankAccount account = new BankAccount("CSV Account", 500.0);
        Category category = new Category(CategoryType.INCOME, "CSV Category");
        Operation op = new Operation(OperationType.INCOME, account.getId(), 300.0, LocalDate.now(), "CSV Op",
                category.getId());
        String accStr = visitor.exportBankAccount(account);
        assertTrue(accStr.contains(account.getId()));
        String catStr = visitor.exportCategory(category);
        assertTrue(catStr.contains(category.getName()));
        String opStr = visitor.exportOperation(op);
        assertTrue(opStr.contains("CSV Op"));
    }

    @Test
    public void testJSONExportVisitor() {
        JSONExportVisitor visitor = new JSONExportVisitor();
        BankAccount account = new BankAccount("JSON Account", 600.0);
        Category category = new Category(CategoryType.EXPENSE, "JSON Category");
        Operation op = new Operation(OperationType.EXPENSE, account.getId(), 150.0, LocalDate.now(), "JSON Op",
                category.getId());
        String accStr = visitor.exportBankAccount(account);
        assertTrue(accStr.contains("\"id\":\"" + account.getId() + "\""));
        String catStr = visitor.exportCategory(category);
        assertTrue(catStr.contains("\"name\":\"" + category.getName() + "\""));
        String opStr = visitor.exportOperation(op);
        assertTrue(opStr.contains("\"description\":\"JSON Op\""));
    }

    @Test
    public void testYamlExportVisitor() {
        YamlExportVisitor visitor = new YamlExportVisitor();
        BankAccount account = new BankAccount("YAML Account", 700.0);
        Category category = new Category(CategoryType.INCOME, "YAML Category");
        Operation op = new Operation(OperationType.INCOME, account.getId(), 400.0, LocalDate.now(), "YAML Op",
                category.getId());
        String accStr = visitor.exportBankAccount(account);
        assertTrue(accStr.contains("name: " + account.getName()));
        String catStr = visitor.exportCategory(category);
        assertTrue(catStr.contains("type: " + category.getType()));
        String opStr = visitor.exportOperation(op);
        assertTrue(opStr.contains("description: " + op.getDescription()));
    }
}