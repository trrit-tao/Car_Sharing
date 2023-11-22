package carsharing.menu.main;

import carsharing.menu.Menu;

public class Exit implements Menu {
    private final String menu0 = "0. Exit";


    @Override
    public void start() {
        System.exit(0);
    }

    @Override
    public void print() {
        System.out.println(menu0);
    }
}
