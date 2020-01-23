package by.javatr.finance.controller.command.impl;

import by.javatr.finance.controller.command.Command;
import by.javatr.finance.controller.responcevalue.StringProperty;
import by.javatr.finance.domain.IncomeType;
import by.javatr.finance.service.exception.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;
import by.javatr.finance.service.impl.OperationLogImpl;


import java.math.BigDecimal;

public class AddIncome implements Command {

    @Override
    public String execute(String request) {
        String response;
        String[] requestData = request.split(delimiter);

        IncomeTransaction incomeTransaction;
        if (requestData.length > 1) {
            BigDecimal amount = new BigDecimal(Double.parseDouble(requestData[0]));
            IncomeType type = IncomeType.valueOf(requestData[1].toUpperCase());
            String title;
            if (requestData.length < 2) {
                title = "";
            } else {
                title = requestData[2];
            }
            incomeTransaction = new IncomeTransaction(title, amount, type);
        } else {
            response = StringProperty.getStringValue("wrongRequestResponse");
            return response;
        }

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OperationLogImpl transactionLog = serviceFactory.getOperationLog();

        try {
            transactionLog.addTransaction(incomeOperation);
            response = StringProperty.getStringValue("incomeAdded");
        } catch (ServiceException e) {
            response = StringProperty.getStringValue("failedToAddIncome") + e.getMessage();
        }
        return response;
    }
}

