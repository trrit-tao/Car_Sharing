package carsharing.menu.companyList;

import carsharing.dao.Company;
import carsharing.dao.DataSourceConnection;
import carsharing.menu.Menu;
import carsharing.menu.Scannerr;
import carsharing.menu.company.NameCompany;

import java.util.List;
import java.util.Scanner;

public class ChooseCompany implements Menu {
    private DataSourceConnection dao = DataSourceConnection.getInstance();
    private final BackMenuManager backMenuManager = new BackMenuManager();
    private final Scanner scanner = Scannerr.getSCANNER();
    private final String menu = "Choose a company:";


    @Override
    public void start() {
        while (true) {
            List<Company> listCompany = dao.selectCompany();
            if (listCompany.isEmpty()) {
                System.out.println("The company list is empty!\n");
                break;
            } else {
                print();
                String input = scanner.nextLine();
                if (input.equals("0")) {
                    break;
                } else {
                    new NameCompany(listCompany.get(Integer.parseInt(input) - 1)).start();
                }
            }
        }
    }

    @Override
    public void print() {
        System.out.println(menu);
        List<Company> listCompany = dao.selectCompany();
        listCompany.forEach(a -> System.out.println(a.toString()));
        backMenuManager.print();
    }
}
