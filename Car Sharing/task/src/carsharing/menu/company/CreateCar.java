package carsharing.menu.company;

import carsharing.dao.Car;
import carsharing.dao.Company;
import carsharing.dao.DataSourceConnection;
import carsharing.menu.Menu;
import carsharing.menu.Scannerr;

import java.util.Scanner;

public class CreateCar implements Menu {
    private final DataSourceConnection dao = DataSourceConnection.getInstance();
    private final Scanner scanner = Scannerr.getSCANNER();
    private final String menu2 = "2. Create a car";
    private final Company company;


    public CreateCar(Company company) {
        this.company = company;
    }


    @Override
    public void start() {
        System.out.println("Enter the car name:");
        dao.insertCar(new Car(scanner.nextLine(), company.getId()));
        System.out.println("The car was added!");
    }

    @Override
    public void print() {
        System.out.println(menu2);
    }
}
