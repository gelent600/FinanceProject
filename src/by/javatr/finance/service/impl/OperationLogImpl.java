package by.javatr.finance.service.impl;



import by.javatr.finance.dao.OperationDAO;
import by.javatr.finance.dao.exception.DAOException;
import by.javatr.finance.dao.factory.DAOFactory;
import by.javatr.finance.domain.IncomeType;
import by.javatr.finance.domain.Operation;
import by.javatr.finance.service.OperationLog;
import by.javatr.finance.service.exception.ServiceException;
import by.javatr.finance.service.validation.Validator;


public class OperationLogImpl implements OperationLog {

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private OperationDAO operationDAO = daoFactory.getOperationDAO();

    @Override
    public boolean addTransaction(Operation operation) throws ServiceException {
        Validator.validateTransaction(operation);

        try {
            operationDAO.add(operation);
        } catch (DAOException ex) {
            throw new ServiceException("Addition has been interrupted", ex);
        }
        return true;
    }

    @Override
    public void updateTransaction(int id, Operation operation) throws ServiceException {
        Validator.validateTransaction(operation);
        try {
            operationDAO.update(id, operation);
        } catch (DAOException ex) {
            throw new ServiceException("Updating has been interrupted", ex);
        }
    }

    @Override
    public Operation getById(int id) throws ServiceException {
        Validator.validateId(id);
        try {
            return operationDAO.get(id);
        } catch (DAOException ex) {
            throw new ServiceException("Getting has been interrupted", ex);
        }
    }


    @Override
    public Operation[] getAllOperation() throws ServiceException {
        try {
            return operationDAO.getAll();
        } catch (DAOException ex) {
            throw new ServiceException("Getting has been interrupted", ex);
        }
    }

    @Override
    public int getCurrentBalance() throws ServiceException {
        Operation[] transactions;
        try {
            transactions = operationDAO.getAll();
        } catch (DAOException ex) {
            throw new ServiceException("Getting balance has been interrupted", ex);
        }
        int balance = 0;
        for (int i = 0; i < transactions.length; i++) {
            Validator.validateTransaction(transactions[i]);
            if (transactions[i].getType().getClass() == IncomeType.class) {
                balance = balance+transactions[i].getSum();
            } else {
                balance = balance-transactions[i].getSum();
            }
        }
        return balance;
    }

    @Override
    public void deleteById(int id) throws ServiceException {
        Validator.validateId(id);
        try {
            operationDAO.delete(id);
        } catch (DAOException ex) {
            throw new ServiceException("Deletion has been interrupted", ex);
        }
    }
}
