package carsharing.menu.costumer;

import carsharing.dao.Car;
import carsharing.dao.Company;
import carsharing.dao.Customer;
import carsharing.dao.DataSourceConnection;
import carsharing.menu.Menu;
import carsharing.menu.Scannerr;

import java.util.List;
import java.util.Scanner;

public class RentCar implements Menu {
    private DataSourceConnection dao = DataSourceConnection.getInstance();
    private final Scanner scanner = Scannerr.getSCANNER();
    private final String menu1 = "1. Rent a car";
    private final Customer customer;

    public RentCar(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void start() {
        while (true) {
            List<Car> carList = dao.selectCustomerCar(customer);
            if (!carList.isEmpty()) {
                System.out.println("You've already rented a car!\n");
                break;
            }
            List<Company> listCompany = dao.selectCompany();
            if (listCompany.isEmpty()) {
                System.out.println("The company list is empty!\n");
                break;
            }
            listCompany.forEach(a -> System.out.println(a.toString()));
            System.out.println("0. Back");
            String choosingCompany = scanner.nextLine();

            Company company = listCompany.get(Integer.parseInt(choosingCompany) - 1);
            List<Car> cars = dao.selectCarTrue(company.getId());

            if (cars.isEmpty()) {
                System.out.println("\nNo available cars in the "
                        + company.getName()
                        + " company\n");
            } else {
                System.out.println("Choose a car:");
                for (int i = 0; i < cars.size(); i++) {
                    System.out.println(i + 1 + ". " + cars.get(i).toString());
                }
                System.out.println("0. Back");
                String choosingCar = scanner.nextLine();
                Car car = cars.get(Integer.parseInt(choosingCar) - 1);
                dao.updateCarFalse(car);
                dao.updateCustomerRENTED_CAR_ID(customer,car);
                System.out.println("You rented '"+ car.getName()+"'");
                break;
            }
        }
    }

    @Override
    public void print() {
        System.out.println(menu1);
    }
}
