package service;


/**
 * PubHouse representation
 */
public class PubHouse {
    private long phID;
    private String name;
    private long total_sold;
    public PubHouse(){}

    public PubHouse(long phID, String name, long total_sold) {
        this.phID = phID;
        this.name = name;
        this.total_sold = total_sold;
    }

    public long getPhID() {
        return phID;
    }

    public void setPhID(long phID) {
        this.phID = phID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotal_sold() {
        return total_sold;
    }

    public void setTotal_sold(long total_sold) {
        this.total_sold = total_sold;
    }
}
