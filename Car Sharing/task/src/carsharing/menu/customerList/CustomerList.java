package carsharing.menu.customerList;

import carsharing.dao.Customer;
import carsharing.dao.DataSourceConnection;
import carsharing.menu.Menu;
import carsharing.menu.Scannerr;
import carsharing.menu.costumer.NameCustomer;

import java.util.List;
import java.util.Scanner;

public class CustomerList implements Menu {
    private DataSourceConnection dao = DataSourceConnection.getInstance();
    private final Scanner scanner = Scannerr.getSCANNER();


    @Override
    public void start() {
        boolean flag = true;
        while (flag) {
            List<Customer> listCustomer = dao.selectCustomer();
            if (listCustomer.isEmpty()) {
                System.out.println("\nThe customer list is empty!\n");
                flag = false;
            } else {
                System.out.println("Choose a customer:");
                for (int i = 0; i < listCustomer.size(); i++) {
                    System.out.println(i + 1 + ". " + listCustomer.get(i).toString());
                }
                System.out.println("0. Back\n");
                String input = scanner.nextLine();
                if (input.equals("0")) {
                    flag = false;
                } else {
                    new NameCustomer(listCustomer.get(Integer.parseInt(input) - 1)).start();
                }
            }
        }
    }

    @Override
    public void print() {

    }
}
