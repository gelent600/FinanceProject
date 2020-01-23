package by.javatr.finance.controller;

import by.javatr.finance.controller.command.Command;
import by.javatr.finance.controller.command.CommandName;
import by.javatr.finance.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

final class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider() {
        repository.put(CommandName.ADD_EXPENSE, new AddExpense());
        repository.put(CommandName.EDIT_EXPENSE, new EditExpense());
        repository.put(CommandName.DELETE_EXPENSE, (Command) new DeleteExpense());

        repository.put(CommandName.ADD_INCOME, new AddIncome());
        repository.put(CommandName.EDIT_INCOME, new EditIncome());
        repository.put(CommandName.DELETE_INCOME, new DeleteIncome());


        repository.put(CommandName.GET_CURRENT_BALANCE, new GetCurrentBalance());

        repository.put(CommandName.GET_TRANSACTIONS_HISTORY, new GetTransactionsHistory());
//
//        repository.put(CommandName.GET_TRANSACTION_BY_ID, new GetTransactionById());

        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    Command getCommand(String name) {
        Command command;
        CommandName commandName;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}
