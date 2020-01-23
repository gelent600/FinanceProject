package by.javatr.finance.controller.command.impl;



import by.javatr.finance.controller.command.Command;
import by.javatr.finance.controller.responcevalue.StringProperty;
import by.javatr.finance.domain.IncomeType;
import by.javatr.finance.domain.Operation;
import by.javatr.finance.service.exception.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;
import by.javatr.finance.service.impl.OperationLogImpl;

import java.math.BigDecimal;

public class EditIncome implements Command {
    @Override
    public String execute(String request) {
        String response;
        String[] requestData = request.split(delimiter);

        IncomeTransaction incomeTransaction;
        int id;
        if (requestData.length > 1) {
            id = Integer.parseInt(requestData[0]);
            BigDecimal amount = new BigDecimal(Double.parseDouble(requestData[1]));
            IncomeType type = IncomeType.valueOf(requestData[2].toUpperCase());
            String title;
            if (requestData.length < 3) {
                title = "";
            } else {
                title = requestData[3];
            }
            incomeTransaction = new IncomeTransaction(title, amount, type);
        } else {
            response = StringProperty.getStringValue("wrongRequestResponse");
            return response;
        }

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OperationLogImpl transactionLog = serviceFactory.getOperationLog();

        try {
            transactionLog.addTransaction(incomeTransaction);
            response = StringProperty.getStringValue("incomeEdited");
        } catch (ServiceException e) {
            response = StringProperty.getStringValue("failedToEditIncome") + e.getMessage();
        }
        return response;
    }
}
