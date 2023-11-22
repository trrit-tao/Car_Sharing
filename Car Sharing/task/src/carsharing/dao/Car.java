package carsharing.dao;

public class Car {
    private int Id;
    private String Name;
    private int CompanyId;
    private boolean availability;

    public Car(String name, int companyId) {
        Name = name;
        CompanyId = companyId;
    }

    public Car(int id, String name, int companyId) {
        Id = id;
        Name = name;
        CompanyId = companyId;
    }

    public Car(int id, String name, int companyId, boolean availability) {
        Id = id;
        Name = name;
        CompanyId = companyId;
        this.availability = availability;
    }

    public int getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(int companyId) {
        CompanyId = companyId;
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

    @Override
    public String toString() {
        return Name;
    }
}
