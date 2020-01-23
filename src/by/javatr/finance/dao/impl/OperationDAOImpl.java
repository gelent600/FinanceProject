package by.javatr.finance.dao.impl;



import by.javatr.finance.dao.OperationDAO;
import by.javatr.finance.dao.exception.DAOException;
import by.javatr.finance.domain.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OperationDAOImpl implements OperationDAO {
    private static final String FULL_FILE_PATH = "/resource/transaction.txt";
    private static final String EMPTY_STRING = "";


    @Override
    public void add(Operation operation) throws DAOException {
        File file = checkFile();
        write(operation, file);
    }

    @Override
    public Operation[] getAll() throws DAOException {
        File file = checkFile();
        return readAll(file);
    }

    @Override
    public void update(int id, Operation newOperation) throws DAOException {
        File file = checkFile();
        Operation[] data = readAll(file);
        clear(file);
        for (Operation element : data) {
            if (element.getId() == id) {
                write(newOperation, file);
            } else {
                write(element, file);
            }
        }
    }

    @Override
    public Operation get(int id) throws DAOException {
        File file = checkFile();
        Operation[] operations = readAll(file);
        for (Operation operation : operations) {
            if (operation.getId() == id) {
                return operation;
            }
        }
        throw new DAOException(String.format("Element with id %d not found in source", id));
    }

    @Override
    public void delete(int id) throws DAOException {
        File file = checkFile();
        Operation[] transactions = readAll(file);
        clear(file);
        for (Operation transaction : transactions) {
            if (transaction.getId() != id) {
                write(transaction, file);
            }
        }
    }

    private void clear(File file) throws DAOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            writer.write(EMPTY_STRING);
        } catch (IOException e) {
            throw new DAOException("Data source problems");
        }
    }

    private File checkFile() throws DAOException {
        File file = new File(FULL_FILE_PATH);
        if (!file.exists()) {
            throw new DAOException("Data source do not exist in given directory.");
        }
        return file;
    }

    private Operation[] readAll(File file) throws DAOException {
        List<Operation> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arrayString = line.split(" ");
                if (arrayString[0] == "Income") {
                    list.add(new Income(arrayString[0], arrayString[1], Integer.parseInt(arrayString[2]), IncomeType.valueOf(arrayString[3])));
                } else {
                    list.add(new Consumption(arrayString[0], arrayString[1], Integer.parseInt(arrayString[2]), ConsumptionType.valueOf(arrayString[3])));
                }

            }
        } catch (IOException e) {
            throw new DAOException("Data source problems", e);
        }
        return list.toArray(new Operation[0]);
    }

    private void write(Operation operation, File file) throws DAOException {
        try (BufferedWriter stream = new BufferedWriter(new FileWriter(file, true))) {
            if (file.length() != 0) {
                stream.newLine();
            }
            stream.write(operation.getTitle()+" "+operation.getDestination()+" "+operation.getSum()+" "+operation.getType());
        } catch (IOException e) {
            throw new DAOException("Data source problems.", e);
        }
    }
}
