package com.hse.bank;

import com.hse.bank.command.CreateAccountCommand;
import com.hse.bank.command.CreateCategoryCommand;
import com.hse.bank.command.CreateOperationCommand;
import com.hse.bank.command.TimeMeasuredCommandDecorator;
import com.hse.bank.domain.Category;
import com.hse.bank.domain.Operation;
import com.hse.bank.facade.BankAccountFacade;
import com.hse.bank.facade.CategoryFacade;
import com.hse.bank.facade.OperationFacade;
import com.hse.bank.facade.FinanceFacade;
import com.hse.bank.util.InputUtil;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        BankAccountFacade accountFacade = new BankAccountFacade();
        CategoryFacade categoryFacade = new CategoryFacade();
        OperationFacade operationFacade = new OperationFacade();
        FinanceFacade financeFacade = new FinanceFacade(accountFacade, categoryFacade, operationFacade);
        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Создать счет");
            System.out.println("2. Создать категорию");
            System.out.println("3. Создать операцию");
            System.out.println("4. Аналитика: разница доходов и расходов");
            System.out.println("5. Группировка операций по категории");
            System.out.println("0. Выход");
            int choice = InputUtil.readInt("Выберите опцию: ");

            if (choice == 0) {
                break;
            }

            switch (choice) {
                case 1: {
                    String name = InputUtil.readString("Введите название счета: ");
                    double balance = InputUtil.readDouble("Введите начальный баланс: ");
                    TimeMeasuredCommandDecorator cmd = new TimeMeasuredCommandDecorator(
                            new CreateAccountCommand(accountFacade, name, balance));
                    cmd.execute();
                    break;
                }
                case 2: {
                    System.out.println("Доступные типы: 1 - INCOME, 2 - EXPENSE");
                    int typeChoice = InputUtil.readInt("Выберите тип категории: ");
                    Category.CategoryType type = (typeChoice == 1) ? Category.CategoryType.INCOME
                            : Category.CategoryType.EXPENSE;
                    String name = InputUtil.readString("Введите название категории: ");
                    TimeMeasuredCommandDecorator cmd = new TimeMeasuredCommandDecorator(
                            new CreateCategoryCommand(categoryFacade, type, name));
                    cmd.execute();
                    break;
                }
                case 3: {
                    System.out.println("Доступные типы: 1 - INCOME, 2 - EXPENSE");
                    int typeChoice = InputUtil.readInt("Выберите тип операции: ");
                    Operation.OperationType type = (typeChoice == 1) ? Operation.OperationType.INCOME
                            : Operation.OperationType.EXPENSE;
                    String accountId = InputUtil.readString("Введите id счета: ");
                    double amount = InputUtil.readDouble("Введите сумму операции: ");
                    String dateInput = InputUtil.readString("Введите дату (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(dateInput);
                    String description = InputUtil.readString("Введите описание (можно оставить пустым): ");
                    String categoryId = InputUtil.readString("Введите id категории: ");
                    TimeMeasuredCommandDecorator cmd = new TimeMeasuredCommandDecorator(new CreateOperationCommand(
                            operationFacade, type, accountId, amount, date, description, categoryId));
                    cmd.execute();
                    break;
                }
                case 4: {
                    String startInput = InputUtil.readString("Введите дату начала (YYYY-MM-DD): ");
                    String endInput = InputUtil.readString("Введите дату окончания (YYYY-MM-DD): ");
                    LocalDate start = LocalDate.parse(startInput);
                    LocalDate end = LocalDate.parse(endInput);
                    double diff = financeFacade.getIncomeExpenseDifference(start, end);
                    System.out.println("Разница доходов и расходов: " + diff);
                    break;
                }
                case 5: {
                    System.out.println("Доступные типы: 1 - INCOME, 2 - EXPENSE");
                    int typeChoice = InputUtil.readInt("Выберите тип операции: ");
                    Operation.OperationType type = (typeChoice == 1) ? Operation.OperationType.INCOME
                            : Operation.OperationType.EXPENSE;
                    var grouping = financeFacade.groupOperationsByCategory(type);
                    grouping.forEach((cat, sum) -> System.out.println("Категория id: " + cat + " -> Сумма: " + sum));
                    break;
                }
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
        System.out.println("Выход из приложения.");
    }
}