import java.sql.*;
import java.util.Scanner;

public class Manager extends BaseModel {

    public Manager(Connection connection, Scanner scanner) {
        super(connection, scanner);
    }

    public void showManagerPanel() {
        while (true) {

            System.out.println("----Operations for adminstrator menu----");
            System.out.println("What kinds of operation would you like to perform?");
            System.out.println("1. List all salespersons");
            System.out.println(
                    "2. Count the no. of sales record of each salesperson under a specific range on years of experience");
            System.out.println("3. Show the total sales value of each manufacturer");
            System.out.println("4. Show the N most popular part");
            System.out.println("5. Return to the main menu");
            System.out.print("Enter your choice: ");
            int choice = getValidChoice(1, 5);

            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    return;
                default:
                    break;
            }
        }
    }

    public void ListSalespersons() throws SQLException {
        System.out.println("Choose ordering:");
        System.out.println("1. By ascending order");
        System.out.println("2. By descending order");
        System.out.print("Choose the list ordering: ");
    }

    public void CountTransaction() throws SQLException {
        System.out.print("Type in the lower bound for years of experience: ");
        System.out.print("Type in the upper bound for years of experience: ");
    }

    public void ListManufacturers() throws SQLException {

    }

    public void ShowNParts() throws SQLException {
        System.out.print("Type in the number of parts: ");
    }
}
