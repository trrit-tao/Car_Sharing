package carsharing.menu.companyList;

import carsharing.menu.Menu;
import carsharing.menu.manager.MenuManager;

public class BackMenuManager implements Menu {
    private final String menu0 = "0. Back";


    @Override
    public void start() {
        new MenuManager().start();
    }

    @Override
    public void print() {
        System.out.println(menu0);
    }
}
