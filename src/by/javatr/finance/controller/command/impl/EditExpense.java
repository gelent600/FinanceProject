package by.javatr.finance.controller.command.impl;

import by.javatr.finance.controller.command.Command;
import by.javatr.finance.controller.responcevalue.StringProperty;
import by.javatr.finance.domain.Operation;
import by.javatr.finance.service.exception.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;
import by.javatr.finance.service.impl.OperationLogImpl;


import java.math.BigDecimal;

public class EditExpense implements Command {

    @Override
    public String execute(String request) {
        String response;
        String[] requestData = request.split(delimiter);

        ExpenseTransaction expenseTransaction;
        int id;
        if (requestData.length > 1) {
            id = Integer.parseInt(requestData[0]);
            BigDecimal amount = new BigDecimal(Double.parseDouble(requestData[1]));
            ExpenseType type = ExpenseType.valueOf(requestData[2].toUpperCase());
            String title;
            if (requestData.length < 3) {
                title = "";
            } else {
                title = requestData[3];
            }
            expenseTransaction = new ExpenseTransaction(title, amount, type);
        } else {
            response = StringProperty.getStringValue("wrongRequestResponse");
            return response;
        }

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OperationLogImpl transactionLog = serviceFactory.getOperationLog();

        try {
            transactionLog.updateTransaction(id, expenseTransaction);
            response = StringProperty.getStringValue("expenseEdited");
        } catch (ServiceException e) {
            response = StringProperty.getStringValue("failedToEditExpense") + e.getMessage();
        }
        return response;
    }
}
