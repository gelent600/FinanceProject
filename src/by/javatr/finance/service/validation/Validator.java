package by.javatr.finance.service.validation;



import by.javatr.finance.domain.Operation;
import by.javatr.finance.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.Date;

public class Validator {

    private static final double EPS = 0.0001;

    public static void validateTransaction(Operation operation) throws ServiceException {
        checkNull(operation);
        validateId(operation.getId());
        validateSum(operation.getSum());
        validateTitle(operation.getTitle());
        validateType(operation.getType());
    }

    private static void checkNull(Operation operation) throws ServiceException {
        if (operation == null) {
            throw new ServiceException("Operation is null");
        }
    }

    public static void validateSum(int sum) throws ServiceException {
        if (sum < 0) {
            throw new ServiceException("Operation sum is invalid");
        }
    }


    private static void validateTitle(String title) throws ServiceException {
        if (title == null) {
            throw new ServiceException("Invalid operation title");
        }
    }

    private static void validateType(Enum<?> type) throws ServiceException {
        if (type == null) {
            throw new ServiceException("Operation type is null");
        }
    }

    public static void validateId(int id) throws ServiceException {
        if (id < 0) {
            throw new ServiceException("Operation id is invalid");
        }
    }
}
