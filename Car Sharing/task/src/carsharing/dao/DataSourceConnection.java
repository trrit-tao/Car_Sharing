package carsharing.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSourceConnection {
    private static DataSourceConnection INSTANCE;

    static final String JDBC_DRIVER = "org.h2.Driver";
    public static String DB_URL;
    static Connection conn;

    public static DataSourceConnection getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataSourceConnection();
        }
        return INSTANCE;
    }

    public DataSourceConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println(DB_URL);
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);
        } catch (Exception ignored) {
        }
        try (Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE COMPANY " +
                    "(ID INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                    " NAME VARCHAR(255) UNIQUE NOT NULL);";
            stmt.executeUpdate(sql);
        } catch (Exception se) {
            se.printStackTrace();
        }
        try (Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE CAR (\n" +
                    "    ID INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    NAME VARCHAR(60) NOT NULL UNIQUE, \n" +
                    "    COMPANY_ID INT NOT NULL,\n" +
                    "    AVAILABILITY BOOLEAN DEFAULT TRUE,\n" +
                    "    CONSTRAINT fk_COMPANY FOREIGN KEY (COMPANY_ID)\n" +
                    "    REFERENCES COMPANY(ID));";
            stmt.executeUpdate(sql);
        } catch (Exception se) {
            se.printStackTrace();
        }
        try (Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE CUSTOMER  (\n" +
                    "    ID INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    NAME VARCHAR(60) NOT NULL UNIQUE, \n" +
                    "    RENTED_CAR_ID INT,\n" +
                    "    CONSTRAINT fk_CAR FOREIGN KEY (RENTED_CAR_ID)\n" +
                    "    REFERENCES CAR(ID));";
            stmt.executeUpdate(sql);
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public List<Car> selectCar(Integer companyId) {
        List<Car> entityList = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(String.format(
                    "SELECT * FROM CAR WHERE COMPANY_ID = %d", companyId));
            while (resultSet.next()) {
                entityList.add(new Car(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getBoolean(4)));
            }
        } catch (Exception se) {
            se.printStackTrace();
        }
        return entityList;
    }

    public List<Car> selectCarTrue(Integer companyId) {
        List<Car> entityList = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(String.format(
                    "SELECT * FROM CAR WHERE COMPANY_ID = %d and AVAILABILITY = TRUE;", companyId));
            while (resultSet.next()) {
                entityList.add(
                        new Car(resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getInt(3),
                                resultSet.getBoolean(4))
                );
            }
        } catch (Exception se) {
            se.printStackTrace();
        }
        return entityList;
    }

    public List<Company> selectCompany() {
        List<Company> entityList = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM COMPANY");
            while (resultSet.next()) {
                entityList.add(new Company(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (Exception se) {
            se.printStackTrace();
        }
        return entityList;
    }

    public List<Customer> selectCustomer() {
        List<Customer> entityList = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM CUSTOMER");
            while (resultSet.next()) {
                entityList.add(new Customer(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (Exception se) {
            se.printStackTrace();
        }
        return entityList;
    }

    public Customer selectNewCustomer(Customer customer) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM CUSTOMER " +
                    "WHERE NAME = '" + customer.getName() + "'");
            customer = new Customer(resultSet.getInt(1), resultSet.getString(2));
        } catch (Exception se) {
            se.printStackTrace();
        }
        return customer;
    }

    public List<Company> selectCustomerCompany(Customer customer) {
        List<Company> entityList = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(String.format(
                    "SELECT NAME\n" +
                            "FROM COMPANY\n" +
                            "WHERE ID =(\n" +
                            "SELECT COMPANY_ID \n" +
                            "FROM CAR\n" +
                            "WHERE ID = (\n" +
                            "SELECT RENTED_CAR_ID\n" +
                            " FROM CUSTOMER \n" +
                            " WHERE NAME = '%s'));", customer.getName()));
            while (resultSet.next()) {
                entityList.add(new Company(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (Exception se) {
            se.printStackTrace();
        }
        return entityList;
    }

    public List<Car> selectCustomerCar(Customer customer) {
        List<Car> entityList = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(String.format(
                    "SELECT NAME \n" +
                            "FROM CAR\n" +
                            "WHERE ID = (\n" +
                            "SELECT RENTED_CAR_ID\n" +
                            "FROM CUSTOMER \n" +
                            "WHERE NAME = '%s');", customer.getName()));
            while (resultSet.next()) {
                entityList.add(new Car(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
            }
        } catch (Exception se) {
            se.printStackTrace();
        }
        return entityList;
    }

    public void insertCompany(Company company) {
        try (PreparedStatement stmt = conn.prepareStatement(String.format(
                        "INSERT INTO COMPANY (NAME) \n" +
                                "VALUES ('%s');", company.getName()),
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            generatedKeys.next();
            company.setId(generatedKeys.getInt(1));
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public void insertCar(Car car) {
        try (PreparedStatement stmt = conn.prepareStatement(String.format(
                        "INSERT INTO CAR (NAME, COMPANY_ID) \n" +
                                "VALUES ('%s',%d);", car.getName(), car.getCompanyId()),
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            generatedKeys.next();
            car.setId(generatedKeys.getInt(1));
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public void insertCustomer(Customer customer) {
        try (PreparedStatement stmt = conn.prepareStatement(String.format(
                        "INSERT INTO CUSTOMER (NAME) \n" +
                                "VALUES ('%s');", customer.getName()),
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            generatedKeys.next();
            customer.setId(generatedKeys.getInt(1));
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public void updateCustomerNull(Customer customer) {
        try (PreparedStatement stmt = conn.prepareStatement(String.format(
                        "UPDATE CUSTOMER\n" +
                                "SET RENTED_CAR_ID = NULL" +
                                "WHERE NAME = '%s';", customer.getName()),
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.executeUpdate();
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public void updateCustomerRENTED_CAR_ID(Customer customer, Car car) {
        try (PreparedStatement stmt = conn.prepareStatement(String.format(
                        "UPDATE CUSTOMER\n" +
                                "SET RENTED_CAR_ID = '%d'" +
                                "WHERE NAME = '%s';", car.getId(), customer.getName()),
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.executeUpdate();
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public void updateCarTrue(Customer customer) {
        try (PreparedStatement stmt = conn.prepareStatement(String.format(
                        "UPDATE CAR\n" +
                                "SET AVAILABILITY = TRUE" +
                                "WHERE ID = '%s';", customer.getRENTED_CAR_ID()),
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.executeUpdate();
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public void updateCarFalse(Car car) {
        try (PreparedStatement stmt = conn.prepareStatement(String.format(
                        "UPDATE CAR\n" +
                                "SET AVAILABILITY = FALSE" +
                                "WHERE ID = '%s';", car.getId()),
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.executeUpdate();
        } catch (Exception se) {
            se.printStackTrace();
        }
    }
}
