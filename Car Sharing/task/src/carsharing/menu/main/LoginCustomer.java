package carsharing.menu.main;

import carsharing.menu.Menu;
import carsharing.menu.customerList.CustomerList;

public class LoginCustomer implements Menu {
    private final String menu2 = "2. Log in as a customer";


    @Override
    public void start() {
        new CustomerList().start();
    }

    @Override
    public void print() {
        System.out.println(menu2);
    }
}
