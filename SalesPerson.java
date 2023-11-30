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
        String kword = scanner.nextLine();
        
        System.out.println("Choose ordering");
        System.out.println("1. By price, ascending order");
        System.out.println("2. By price, descending order");
        System.out.print("Choose the search criterion: ");
        int order = getValidChoice(1, 2);

        switch (choice) {
            case 1:
                switch (order) {
                case 1:
                    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM manufactuer AS M, part AS P, category AS C WHERE P.mID =  M.mID AND P.cID = C.cID AND pName = ? ORDER BY pPrice");
                    break;
                case 2:
                    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM manufactuer AS M, part AS P, category AS C WHERE P.mID =  M.mID AND P.cID = C.cID AND pName = ? ORDER BY pPrice DESC");
                    break;
                }
                break;
            case 2:
                switch (order) {
                case 1:
                    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM manufactuer AS M, part AS P, category AS C WHERE P.mID =  M.mID AND P.cID = C.cID AND mName = ? ORDER BY pPrice");
                    break;
                case 2:
                    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM manufactuer AS M, part AS P, category AS C WHERE P.mID =  M.mID AND P.cID = C.cID AND mName = ? ORDER BY pPrice DESC");
                    break;
                }
                break;
        }
        stmt.setString(1, "%" + kword + "%");
        ResultSet rs = stmt.executeQuery();
        System.out.println("| ID | Name | Manufacturer | Category | Quantity | Warranty | Price |");
        while (rs.next()) {
            System.out.println(String.format("| %d | %s | %s | %s | %d | %d | %d |", rs.getInt("pID"), rs.getString("pName"), rs.getString("mName"), rs.getString("cName"), rs.getInt("pQuantity"), rs.getInt("pWarranty"), rs.getInt("pPrice")));
        }
        return;
    }

    public void PerformTransaction() throws SQLException {
        System.out.print("Enter The Part ID: ");
        PreparedStatement stmtP = connection.prepareStatement("SELECT * FROM PART WHERE pID = ?");
        PreparedStatement stmtS = connection.prepareStatement("SELECT * FROM SALESPERSON WHERE sID = ?");
        while(true){
            int partID = scanner.nextInt();
            scanner.nextLine();
            System.out.println("");
            stmtP.setString(1, partID);
            ResultSet rs = stmtP.executeQuery();
            if (rs.getInt(pQuantity) > 0){
                int quantity = rs.getInt(pQuantity);
                System.out.print("Enter The Salesperson ID: ");
                while(true){
                    int salesID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("");
                    stmtS.setString(1, salesID);
                    rs = stmtS.executeQuery();
                    if (rs.next()){

                    }
                    else
                        System.out.print("Not such salesperson, please enter again: ");
                }
            }
            else if (rs.getInt(pQuantity) == 0)
                System.out.print("The part cannot be sold.");
                return;
            else
                System.out.print("Not such product, please enter again: ");
        }
         
            


        
        /* "SELECT pQuantity from part WHERE pID = " + partID */
        System.out.println("The part cannot be sold."); // if quanitity = 0
        System.out.print("Enter The Salesperson ID: ");
        return;
    }
}
