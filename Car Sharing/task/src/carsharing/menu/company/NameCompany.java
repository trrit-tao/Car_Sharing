package carsharing.menu.company;

import carsharing.dao.Company;
import carsharing.menu.Menu;
import carsharing.menu.Scannerr;
import carsharing.menu.manager.MenuManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NameCompany implements Menu {
    private final Company company;
    private final List<Menu> menuList = new ArrayList<>();
    private final Scanner scanner = Scannerr.getSCANNER();


    public NameCompany(Company company) {
        this.company = company;
        menuList.add(new CarList(company));
        menuList.add(new CreateCar(company));
        menuList.add(new BackCompanyList());
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
                case "0" -> new MenuManager().start();
            }
        }
    }

    @Override
    public void print() {
        System.out.println("'" + company.getName() + "'" + " company:");
        menuList.forEach(Menu::print);
    }
}
