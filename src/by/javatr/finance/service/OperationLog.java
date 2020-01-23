package by.javatr.finance.service;



import by.javatr.finance.domain.Operation;
import by.javatr.finance.service.exception.ServiceException;

import java.math.BigDecimal;

public interface OperationLog {

    boolean addTransaction(Operation operation) throws ServiceException;

    void updateTransaction(int id, Operation operation) throws ServiceException;

    Operation getById(int id) throws ServiceException;

    Operation[] getAllOperation() throws ServiceException;

    int getCurrentBalance() throws ServiceException;

    void deleteById(int id) throws ServiceException;

}
