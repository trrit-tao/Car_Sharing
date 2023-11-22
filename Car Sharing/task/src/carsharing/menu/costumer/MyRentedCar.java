package carsharing.menu.costumer;

import carsharing.dao.Car;
import carsharing.dao.Company;
import carsharing.dao.Customer;
import carsharing.dao.DataSourceConnection;
import carsharing.menu.Menu;

import java.util.List;

public class MyRentedCar implements Menu {
    private DataSourceConnection dao = DataSourceConnection.getInstance();
    private final String menu3 = "3. My rented car";
    private final Customer customer;

    public MyRentedCar(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void start() {
        Customer cust = dao.selectNewCustomer(this.customer);
        List<Car>carList = dao.selectCustomerCar(cust);
        List<Company>companyList = dao.selectCustomerCompany(cust);
        if (carList.isEmpty()){
            System.out.println("You didn't rent a car!\n");
        }else {
            System.out.println("Your rented car:");
            carList.forEach(a-> System.out.println("'"+a.getName()+"'"));
            System.out.println("Company:");
            companyList.forEach(a-> System.out.println("'"+a.getName()+"'"));

        }

    }

    @Override
    public void print() {
        System.out.println(menu3);
    }
}
