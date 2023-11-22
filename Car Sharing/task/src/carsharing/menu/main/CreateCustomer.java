package carsharing.menu.main;

import carsharing.dao.Customer;
import carsharing.dao.DataSourceConnection;
import carsharing.menu.Menu;
import carsharing.menu.Scannerr;

import java.util.Scanner;

public class CreateCustomer implements Menu {
    private final DataSourceConnection dao = DataSourceConnection.getInstance();
    private final Scanner scanner = Scannerr.getSCANNER();
    private final String menu3 = "3. Create a customer";


    @Override
    public void start() {
        System.out.println("\nEnter the customer name:");
        dao.insertCustomer(new Customer(scanner.nextLine()));
        System.out.println("The customer was added!\n");

    }

    @Override
    public void print() {
        System.out.println(menu3);
    }
}
