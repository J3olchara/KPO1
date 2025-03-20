# Учет финансов 
> для красоты попросил мой текст сделать красивым у гптшки :)
## Цель проекта
Реализовать модуль «Учет финансов» будущего банковского приложения, позволяющий отслеживать доходы и расходы пользователей, управлять счетами, категориями и операциями, а также предоставлять базовую аналитику.

## Функциональность
- Создание, редактирование и удаление счетов, категорий и операций.
- Анализ разницы между доходами и расходами за выбранный период.
- Группировка операций по категориям.
- Импорт данных из файлов CSV, JSON, YAML (реализован через шаблонный метод).
- Экспорт данных в CSV, JSON, YAML (реализован через паттерн Посетитель).
- Возможность измерения времени выполнения пользовательских сценариев (паттерн Команда с декоратором).
- Использован паттерн Фабрика для создания доменных объектов.
- Реализован паттерн Фасад для объединения операций над сущностями.
- Реализован паттерн Прокси для in-memory кэширования данных.

## Использованные паттерны
- **Фабрика**: класс DomainFactory гарантирует валидное создание объектов.
- **Фасад**: классы BankAccountFacade, CategoryFacade, OperationFacade и FinanceFacade упрощают интерфейс взаимодействия с доменной моделью.
- **Команда + Декоратор**: классы CreateAccountCommand, CreateCategoryCommand, CreateOperationCommand и TimeMeasuredCommandDecorator позволяют применять декоратор для измерения времени выполнения сценария.
- **Шаблонный метод**: абстрактный класс DataImporter и его реализации (CsvDataImporter, JsonDataImporter, YamlDataImporter) для импорта данных из различных форматов.
- **Посетитель**: реализации ExportVisitor (CSVExportVisitor, JSONExportVisitor, YamlExportVisitor) для экспорта данных.
- **Прокси**: класс FinanceDataProxy для кэширования доменных объектов.

## Принципы SOLID и GRASP
- **Single Responsibility Principle**: каждый класс отвечает за строго определенную задачу (например, фасады для работы с конкретными доменными объектами).
- **Open/Closed Principle**: архитектура легко расширяется за счет использования шаблонного метода, команд и паттернов Фабрика/Фасад.
- **Liskov Substitution Principle**: реализации интерфейсов (например, ExportVisitor) могут быть взаимозаменяемыми.
- **Interface Segregation Principle**: классы реализуют только необходимые интерфейсы.
- **Dependency Inversion Principle**: взаимодействие модулей происходит через абстракции (интерфейсы команд и посетителя).

Принципы GRASP (High Cohesion / Low Coupling) соблюдаются через грамотное разделение ответственности между классами и использованием паттернов.

## Структура проекта
```
com.hse.bank
├── App.java
├── domain
│   ├── BankAccount.java
│   ├── Category.java
│   └── Operation.java
├── factory
│   └── DomainFactory.java
├── facade
│   ├── BankAccountFacade.java
│   ├── CategoryFacade.java
│   ├── OperationFacade.java
│   └── FinanceFacade.java
├── command
│   ├── Command.java
│   ├── CreateAccountCommand.java
│   ├── CreateCategoryCommand.java
│   ├── CreateOperationCommand.java
│   └── TimeMeasuredCommandDecorator.java
├── templatemethod
│   ├── DataImporter.java
│   ├── CsvDataImporter.java
│   ├── JsonDataImporter.java
│   └── YamlDataImporter.java
├── visitor
│   ├── ExportVisitor.java
│   ├── CSVExportVisitor.java
│   ├── JSONExportVisitor.java
│   └── YamlExportVisitor.java
├── proxy
│   └── FinanceDataProxy.java
└── util
    └── InputUtil.java
```

## Инструкция по запуску
1. Убедитесь, что у вас установлена JDK 8 или выше.
2. Склонируйте проект или загрузите исходный код.
3. В корне проекта, где находится папка `src`, выполните сборку с помощью Maven или вашей предпочитаемой сборочной системы.
   Пример сборки с использованием Maven:
   • Перейдите в каталог проекта.
   • Выполните команду: `mvn clean compile`
4. Для запуска приложения выполните:
   • `mvn exec:java -Dexec.mainClass="com.hse.bank.App"`
5. Следуйте инструкциям в консоли для работы с приложением.

## Заключение
Данный проект демонстрирует применение принципов SOLID, GRASP и паттернов проектирования (GoF) для создания модульного, расширяемого и поддерживаемого приложения для учета финансов.