package carsharing.menu.manager;

import carsharing.menu.Menu;
import carsharing.menu.Scannerr;

import java.util.List;
import java.util.Scanner;

public class MenuManager implements Menu {
    private final List<Menu> menuList = List.of(new CompanyList(), new CreateCompany(), new BackMainMenu());
    private final Scanner scanner = Scannerr.getSCANNER();


    @Override
    public void start() {
        boolean flag = true;
        while (flag) {
            print();
            switch (scanner.nextLine()) {
                case ("1") -> menuList.get(0).start();
                case ("2") -> menuList.get(1).start();
                case ("0") -> menuList.get(menuList.size()-1).start();
            }
        }
    }

    @Override
    public void print() {
        menuList.forEach(Menu::print);
    }
}
