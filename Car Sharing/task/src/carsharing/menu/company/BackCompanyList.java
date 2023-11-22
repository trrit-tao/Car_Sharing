package carsharing.menu.company;

import carsharing.menu.Menu;
import carsharing.menu.companyList.ChooseCompany;

public class BackCompanyList implements Menu {
    private final String menu0 = "0. Back";

    @Override
    public void start() {
        new ChooseCompany().start();
    }

    @Override
    public void print() {
        System.out.println(menu0);
    }
}
