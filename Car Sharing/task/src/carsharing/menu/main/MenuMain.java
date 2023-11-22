package carsharing.menu.main;

import carsharing.menu.Menu;
import carsharing.menu.Scannerr;

import java.util.List;
import java.util.Scanner;

public class MenuMain implements Menu {
    private final List<Menu> menuList = List.of(
            new LoginManager(),
            new LoginCustomer(),
            new CreateCustomer(),
            new Exit()
    );
    private final Scanner scanner = Scannerr.getSCANNER();


    @Override
    public void start() {
        while (true) {
            print();
            switch (scanner.nextLine()) {
                case ("1") -> menuList.get(0).start();
                case ("2") -> menuList.get(1).start();
                case ("3") -> menuList.get(2).start();
                case ("0") -> menuList.get(menuList.size() - 1).start();
            }
        }
    }

    @Override
    public void print() {
        menuList.forEach(Menu::print);
    }
}
