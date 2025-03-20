package com.hse.bank.command;

public class TimeMeasuredCommandDecorator implements Command {
    private final Command command;

    public TimeMeasuredCommandDecorator(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {
        long start = System.currentTimeMillis();
        command.execute();
        long end = System.currentTimeMillis();
        System.out.println("Время исполнения: " + (end - start) + " милисекунд");
    }
}