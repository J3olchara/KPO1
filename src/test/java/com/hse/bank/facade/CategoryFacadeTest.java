package com.hse.bank.facade;

import com.hse.bank.domain.Category;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryFacadeTest {
    @Test
    public void testCreateAndGetCategory() {
        CategoryFacade facade = new CategoryFacade();
        Category category = facade.createCategory(Category.CategoryType.EXPENSE, "Entertainment");
        assertNotNull(category);
        Category fetched = facade.getCategory(category.getId());
        assertEquals(category.getId(), fetched.getId());
    }

    @Test
    public void testEditCategory() {
        CategoryFacade facade = new CategoryFacade();
        Category category = facade.createCategory(Category.CategoryType.INCOME, "Bonus");
        facade.editCategory(category.getId(), "Yearly Bonus");
        Category updated = facade.getCategory(category.getId());
        assertEquals("Yearly Bonus", updated.getName());
    }

    @Test
    public void testDeleteCategory() {
        CategoryFacade facade = new CategoryFacade();
        Category category = facade.createCategory(Category.CategoryType.EXPENSE, "Food");
        facade.deleteCategory(category.getId());
        assertNull(facade.getCategory(category.getId()));
    }
}