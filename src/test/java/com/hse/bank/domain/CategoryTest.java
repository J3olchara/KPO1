package com.hse.bank.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {
    @Test
    public void testCategoryCreationAndEdit() {
        Category category = new Category(Category.CategoryType.EXPENSE, "Food");
        assertNotNull(category.getId());
        assertEquals("Food", category.getName());
        assertEquals(Category.CategoryType.EXPENSE, category.getType());
        category.setName("Groceries");
        assertEquals("Groceries", category.getName());
    }
}