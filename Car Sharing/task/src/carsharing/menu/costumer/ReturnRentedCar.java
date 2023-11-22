package carsharing.menu.costumer;

import carsharing.dao.Car;
import carsharing.dao.Customer;
import carsharing.dao.DataSourceConnection;
import carsharing.menu.Menu;

import java.util.List;

public class ReturnRentedCar implements Menu {
    private DataSourceConnection dao = DataSourceConnection.getInstance();
    private final String menu2 = "2. Return a rented car";
    private final Customer customer;

    public ReturnRentedCar(Customer customer) {
        this.customer = customer;
    }


    @Override
    public void start() {
        List<Car> carList = dao.selectCustomerCar(customer);
        if (carList.isEmpty()){
            System.out.println("You didn't rent a car!\n");
        }else {
            dao.updateCustomerNull(customer);
            dao.updateCarTrue(customer);
            System.out.println("You've returned a rented car!\n");
        }
    }

    @Override
    public void print() {
        System.out.println(menu2);
    }
}
