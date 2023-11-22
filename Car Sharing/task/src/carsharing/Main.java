package carsharing;

import carsharing.dao.DataSourceConnection;
import carsharing.menu.main.MenuMain;

public class Main {

    public static void main(String[] args) {
        iter(args);
        MenuMain menuMain = new MenuMain();
        menuMain.start();
    }

    public static void iter(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
            if (args[i].equals("-databaseFileName")) {
                DataSourceConnection.DB_URL = "jdbc:h2:file:../task/src/carsharing/db/" + args[i + 1];
                DataSourceConnection.getInstance();
                return;
            }
        }
        DataSourceConnection.DB_URL = "jdbc:h2:file:../task/src/carsharing/db/" + "test";
        DataSourceConnection.getInstance();
    }
}