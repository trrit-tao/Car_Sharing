package carsharing.menu.costumer;

import carsharing.dao.Customer;
import carsharing.dao.DataSourceConnection;
import carsharing.menu.Menu;
import carsharing.menu.Scannerr;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NameCustomer implements Menu {
    private final List<Menu> menuList = new ArrayList<>();
    private final Scanner scanner = Scannerr.getSCANNER();


    public NameCustomer(Customer customer) {
        menuList.add(new RentCar(customer));
        menuList.add(new ReturnRentedCar(customer));
        menuList.add(new MyRentedCar(customer));
        menuList.add(new BackMainMenu());
    }

    @Override
    public void start() {
        boolean flag = true;
        while (flag) {
            print();
            String input = scanner.nextLine();
            switch (input) {
                case "1" -> menuList.get(0).start();
                case "2" -> menuList.get(1).start();
                case "3" -> menuList.get(2).start();
                case "0" -> menuList.get(menuList.size()-1).start();
            }
        }
    }

    @Override
    public void print() {
        menuList.forEach(Menu::print);
    }
}
