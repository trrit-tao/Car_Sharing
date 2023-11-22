package carsharing.menu.manager;

import carsharing.dao.Company;
import carsharing.dao.DataSourceConnection;
import carsharing.menu.Menu;
import carsharing.menu.Scannerr;

import java.util.Scanner;

public class CreateCompany implements Menu {
    private DataSourceConnection dao = DataSourceConnection.getInstance();
    private final Scanner scanner = Scannerr.getSCANNER();
    private final String menu2 = "2. Create a company";


    @Override
    public void start() {
        System.out.println("Enter the company name:");
        dao.insertCompany(new Company(scanner.nextLine()));
        System.out.println("The company was created!\n");
    }

    @Override
    public void print() {
        System.out.println(menu2);
    }
}
