package by.javatr.finance.controller.command.impl;


import by.javatr.finance.controller.command.Command;
import by.javatr.finance.controller.responcevalue.StringProperty;
import by.javatr.finance.service.exception.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;
import by.javatr.finance.service.impl.OperationLogImpl;

public class DeleteIncome implements Command {

    @Override
    public String execute(String request) {
        String response;
        String[] requestData = request.split(delimiter);

        int id;
        if (requestData.length > 0) {
            id = Integer.parseInt(requestData[0]);
        } else {
            response = StringProperty.getStringValue("wrongRequestResponse");
            return response;
        }

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OperationLogImpl transactionLog = serviceFactory.getOperationLog();

        try {
            transactionLog.deleteById(id);
            response = StringProperty.getStringValue("incomeDeleted");
        } catch (ServiceException e) {
            response = StringProperty.getStringValue("failedToDeleteIncome") + e.getMessage();
        }
        return response;
    }
}
