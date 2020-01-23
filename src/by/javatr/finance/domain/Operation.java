package by.javatr.finance.domain;

import java.io.Serializable;

public abstract class Operation implements Serializable {
    protected int id;
    protected int sum;
    protected String destination;
    protected String title;
    protected Enum<?> type;

    protected static int operationCount;

    public Operation() {
    }

    protected int createGlobalId(int id) {
        if (id > operationCount) {
            operationCount = id;
            return id;
        }
        return id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Enum<?> getType() {
        return type;
    }

    public void setType(Enum<?> type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", destination=" + destination +
                ", sum=" + sum +
                ", type=" + type +
                '}';
    }


}
