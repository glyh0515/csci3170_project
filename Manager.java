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
            int choice = getValidChoice(1, 5);

            switch (choice) {
                case 1:

                    break;
                case 5:
                    return;
                default:
                    break;
            }
        }
    }
`
    public void ListSalespersons(Connection con) throws SQLException{
    
    }
    
    public void CountTransaction(Connection con) throws SQLException{
    
    }
    
    public void ListManufacturers(Connection con) throws SQLException{
    
    }
    
    public void ShowNParts(Connection con) throws SQLException{
    
    }
}
