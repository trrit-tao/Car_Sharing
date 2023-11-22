package carsharing.dao;

public class Company {
    private int Id;
    private String Name;


    public Company(String NAME) {
        this.Name = NAME;
    }

    public Company(int id, String name) {
        Id = id;
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public void setName(String NAME) {
        this.Name = NAME;
    }

    @Override
    public String toString() {
        return Id + ". " + Name;
    }
}
