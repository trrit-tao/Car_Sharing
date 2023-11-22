package carsharing.menu.company;

import carsharing.dao.Car;
import carsharing.dao.Company;
import carsharing.dao.DataSourceConnection;
import carsharing.menu.Menu;

import java.util.List;

public class CarList implements Menu {
    private final DataSourceConnection dao = DataSourceConnection.getInstance();
    private final String menu1 = "1. Car list";
    private final Company company;


    public CarList(Company company) {
        this.company = company;
    }


    @Override
    public void start() {
        List<Car> cars = dao.selectCar(company.getId());
        if (cars.isEmpty()) {
            System.out.println("\nThe car list is empty!\n");
        } else {
            System.out.println(company.getName() + " cars:");
            for (int i = 0; i < cars.size(); i++) {
                System.out.println(i + 1 + ". " + cars.get(i).toString());
            }
            System.out.println();
        }
    }

    @Override
    public void print() {
        System.out.println(menu1);
    }
}
