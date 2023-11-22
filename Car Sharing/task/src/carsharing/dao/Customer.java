package carsharing.dao;

public class Customer {
    private int Id;
    private String Name;
    private int RENTED_CAR_ID;


    public Customer(String name) {
        Name = name;
    }

    public Customer(int ID ,String name) {
        Id = ID;
        Name = name;
    }

    public Customer(int ID ,String name, int rentId) {
        Id = ID;
        Name = name;
        RENTED_CAR_ID = rentId;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getRENTED_CAR_ID() {
        return RENTED_CAR_ID;
    }

    public void setRENTED_CAR_ID(int RENTED_CAR_ID) {
        this.RENTED_CAR_ID = RENTED_CAR_ID;
    }

    @Override
    public String toString() {
        return Name;
    }
}
