package by.javatr.finance.controller.command;

public interface Command {

    String delimiter = " ";
    public String execute(String request);
}
