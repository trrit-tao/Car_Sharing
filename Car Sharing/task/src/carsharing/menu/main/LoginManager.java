package carsharing.menu.main;

import carsharing.menu.Menu;
import carsharing.menu.manager.MenuManager;

public class LoginManager implements Menu {
    private final String menu1 = "1. Log in as a manager";


    @Override
    public void start() {
        new MenuManager().start();
    }

    @Override
    public void print() {
        System.out.println(menu1);
    }
}
