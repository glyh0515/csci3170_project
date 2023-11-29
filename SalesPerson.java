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
            int choice = 0;
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
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

    public void SearchPart(Connection con) throws SQLException{
        System.out.println("Choose the Search Criterion: ");
        System.out.println("1. Part Name ");
        System.out.println("2. Manufacturer Name ");
        System.out.print("Choose the Search Criterion: ");
        System.out.print("Type in the Search Keyword ");
        System.out.println("Choose ordering");
        System.out.println("1. By price, ascending order");
        System.out.println("2. By price, descending order");
        System.out.print("Choose the search criterion: ");
       /* "SELECT * FROM Manufactuer M, Part P WHERE " + (searchCriterion == 1 ? "P.PNAME = " : "P.MID = M.MID AND M.NAME = ") + keyword + "ORDERING BY " + (order == 1 ? "ASC" : "DESC") + ";" */
    }
    
    public void PerformTransaction(Connection con) throws SQLException{
        System.out.print("Enter The Part ID: ");
        System.out.print("Enter The Salesperson ID: ");
    }
}
