package by.javatr.finance.controller.command.impl;

import by.javatr.finance.controller.command.Command;
import by.javatr.finance.controller.responcevalue.StringProperty;


public class WrongRequest implements Command {
    @Override
    public String execute(String request) {
        return StringProperty.getStringValue("wrongRequestResponse");
    }
}
