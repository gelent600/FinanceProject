package by.javatr.finance.service.factory;


import by.javatr.finance.dao.impl.OperationDAOImpl;
import by.javatr.finance.service.impl.OperationLogImpl;

public final class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final OperationDAOImpl operationLog = new OperationDAOImpl();

    private ServiceFactory() {

    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public OperationLogImpl getOperationLog() {
        return operationLog;
    }
}

