package by.javatr.finance.domain;



public class Consumption extends Operation {

    public Consumption() {
        this.id = ++operationCount;
        this.title = "";
        this.sum=0;
        this.destination="";
        this.type = ConsumptionType.FOOD;
    }

    public Consumption(String title, String destination, int sum, ConsumptionType type) {
        this.id = ++operationCount;
        this.title = title;
        this.sum=sum;
        this.destination=destination;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation that = (Operation) o;
        return id == that.id &&
                this.sum==that.sum &&
                this.title.equals(that.title) &&
                this.type.equals(that.type);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int hash = 1;
        hash = prime * hash + id;
        hash = prime * hash + sum;
        hash = prime * hash + title.hashCode();
        hash = prime * hash + type.hashCode();

        return hash;
    }

    @Override
    public String toString() {
        return "ExpenseTransaction{" +
                "id=" + id +
                ", title=" + title +
                ", sum=" + sum +
                ", type=" + type +
                '}';
    }
}
