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
            System.out.print("Enter your choice: ");
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

    public void SearchPart() throws SQLException {
        System.out.println("Choose the Search Criterion: ");
        System.out.println("1. Part Name ");
        System.out.println("2. Manufacturer Name ");
        System.out.print("Choose the Search Criterion: ");
        int choice = getValidChoice(1, 2);
        System.out.print("Type in the Search Keyword ");
        System.out.println("Choose ordering");
        System.out.println("1. By price, ascending order");
        System.out.println("2. By price, descending order");
        System.out.print("Choose the search criterion: ");
        int order = getValidChoice(1, 2);
        /*
         * "SELECT * FROM manufactuer M, part P WHERE " 
         * + (searchCriterion == 1 ? "P.pName = " : "P.mID =  M.mID AND M.mName = ") 
         * + keyword + " ORDER BY P.pPrice" 
         * + (order == 1 ? "ASC" : "DESC") + ";"
         */
        return;
    }

    public void PerformTransaction() throws SQLException {
        System.out.print("Enter The Part ID: ");
        int partID;
        int quantity;
        /* "SELECT pQuantity from part WHERE pID = " + partID */
        System.out.println("The part cannot be sold."); // if quanitity = 0
        System.out.print("Enter The Salesperson ID: ");
        return;
    }
}
