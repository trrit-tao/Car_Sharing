package carsharing.menu.manager;

import carsharing.menu.Menu;
import carsharing.menu.main.MenuMain;

public class BackMainMenu implements Menu {
    private final String menu0 = "0. Back";


    @Override
    public void start() {
        new MenuMain().start();
    }

    @Override
    public void print() {
        System.out.println(menu0);
    }
}
