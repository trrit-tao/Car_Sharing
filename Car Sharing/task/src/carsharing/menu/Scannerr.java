package carsharing.menu;

import java.util.Scanner;


public class Scannerr {
    private static Scanner SCANNER;


    public static Scanner getSCANNER() {
        if (SCANNER == null) {
            SCANNER = new Scanner(System.in);
        }
        return SCANNER;
    }
}
