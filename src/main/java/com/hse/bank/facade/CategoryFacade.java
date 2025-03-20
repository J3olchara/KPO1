package com.hse.bank.facade;

import com.hse.bank.domain.Category;
import com.hse.bank.factory.DomainFactory;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CategoryFacade {
    private final Map<String, Category> categories = new HashMap<>();

    public Category createCategory(Category.CategoryType type, String name) {
        Category category = DomainFactory.createCategory(type, name);
        categories.put(category.getId(), category);
        return category;
    }

    public Category getCategory(String id) {
        return categories.get(id);
    }

    public void editCategory(String id, String newName) {
        Category category = categories.get(id);
        if (category != null) {
            category.setName(newName);
        }
    }

    public void deleteCategory(String id) {
        categories.remove(id);
    }

    public Collection<Category> getAllCategories() {
        return categories.values();
    }
}