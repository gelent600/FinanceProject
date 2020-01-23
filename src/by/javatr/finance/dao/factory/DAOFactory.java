package by.javatr.finance.dao.factory;

import by.javatr.finance.dao.impl.OperationDAOImpl;


public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final OperationDAOImpl fileOperationImpl = new OperationDAOImpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public OperationDAOImpl getOperationDAO() {
        return fileOperationImpl;
    }
}
