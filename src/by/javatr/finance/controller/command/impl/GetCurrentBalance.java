package by.javatr.finance.controller.command.impl;



import by.javatr.finance.controller.command.Command;
import by.javatr.finance.controller.responcevalue.StringProperty;
import by.javatr.finance.domain.Operation;
import by.javatr.finance.service.exception.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;
import by.javatr.finance.service.impl.OperationLogImpl;

import java.util.Arrays;

public class GetCurrentBalance implements Command {

    @Override
    public String execute(String request) {
        String response;
        String[] requestData = request.split(delimiter);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OperationLogImpl transactionLog = serviceFactory.getOperationLog();

        try {
            response = transactionLog.getCurrentBalance().toString();
        } catch (ServiceException e) {
            response = StringProperty.getStringValue("failedToGetCurrentBalance") + e.getMessage();
        }
        return response;
    }
}
