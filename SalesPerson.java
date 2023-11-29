import java.sql.*;
import java.util.Scanner;

public class SalesPerson extends BaseModel {

    public SalesPerson(Connection connection, Scanner scanner) {
        super(connection, scanner);
    }

    public void showSalesPersonPanel() {
        while (true) {

            System.out.println("----Operations for adminstrator menu----");
            System.out.println("What kinds of operation would you like to perform?");
            System.out.println("1. Search for parts");
            System.out.println("2. Sell a part");
            System.out.println("3. Return to the main menu");
            int choice = getValidChoice(1, 3);

            switch (choice) {
                case 1:

                    break;
                case 2:
                    break;
                case 3:
                    return;

                default:
                    break;
            }
        }
    }
}