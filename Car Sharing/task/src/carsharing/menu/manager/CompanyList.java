package carsharing.menu.manager;

import carsharing.menu.Menu;
import carsharing.menu.companyList.ChooseCompany;


public class CompanyList implements Menu {
    private final String menu1 = "1. Company list";


    @Override
    public void start() {
        new ChooseCompany().start();
    }

    @Override
    public void print() {
        System.out.println(menu1);
    }
}
