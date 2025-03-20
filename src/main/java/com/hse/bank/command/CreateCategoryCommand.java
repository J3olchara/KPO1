package com.hse.bank.command;

import com.hse.bank.facade.CategoryFacade;
import com.hse.bank.domain.Category;

public class CreateCategoryCommand implements Command {
    private final CategoryFacade categoryFacade;
    private final Category.CategoryType type;
    private final String name;

    public CreateCategoryCommand(CategoryFacade categoryFacade, Category.CategoryType type, String name) {
        this.categoryFacade = categoryFacade;
        this.type = type;
        this.name = name;
    }

    @Override
    public void execute() {
        Category category = categoryFacade.createCategory(type, name);
        System.out.println("Создана категория: " + category);
    }
}