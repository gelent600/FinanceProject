package by.javatr.finance.dao;


import by.javatr.finance.dao.exception.DAOException;
import by.javatr.finance.domain.Operation;

public interface OperationDAO {

    void add(Operation operation) throws DAOException;

    void delete(int id) throws DAOException;

    void update(int id, Operation newOperation) throws DAOException;

    Operation[] getAll() throws DAOException;

    Operation get(int id) throws DAOException;
}
