package com.hse.bank.facade;

import com.hse.bank.domain.Operation;
import com.hse.bank.domain.Operation.OperationType;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class OperationFacadeTest {
    @Test
    public void testCreateAndGetOperation() {
        OperationFacade facade = new OperationFacade();
        LocalDate date = LocalDate.now();
        Operation op = facade.createOperation(OperationType.INCOME, "acc1", 250.0, date, "Test op", "cat1");
        assertNotNull(op);
        Operation fetched = facade.getOperation(op.getId());
        assertEquals(op.getId(), fetched.getId());
    }

    @Test
    public void testDeleteOperation() {
        OperationFacade facade = new OperationFacade();
        LocalDate date = LocalDate.now();
        Operation op = facade.createOperation(OperationType.EXPENSE, "acc2", 80.0, date, "Test delete", "cat2");
        facade.deleteOperation(op.getId());
        assertNull(facade.getOperation(op.getId()));
    }
}