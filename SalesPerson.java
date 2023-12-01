import java.sql.*;
import java.util.Scanner;

public class SalesPerson extends BaseModel {

    public SalesPerson(Connection connection, Scanner scanner) {
        super(connection, scanner);
    }

    public void showSalesPersonPanel() {
        while (true) {

            System.out.println("----Operations for salesperson menu----");
            System.out.println("What kinds of operation would you like to perform?");
            System.out.println("1. Search for parts");
            System.out.println("2. Sell a part");
            System.out.println("3. Return to the main menu");
            System.out.print("Enter your choice: ");
            int choice = getValidChoice(1, 3);

            switch (choice) {
                case 1:
                    try {
                        SearchPart();
                    } catch (SQLException e) {
                        System.out.println("");
                        System.out.println(e);
                    }
                    break;
                case 2:
                    try {
                        PerformTransaction();
                    } catch (SQLException e) {
                        System.out.println("");
                        System.out.println(e);
                    }
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

        String sql = null;
        System.out.println("| ID | Name | Manufacturer | Category | Quantity | Warranty | Price |");
        switch (choice) {
            case 1:
                switch (order) {
                case 1:
                    sql ="SELECT * FROM manufacturer AS M, part AS P, category AS C WHERE P.mID =  M.mID AND P.cID = C.cID AND pName LIKE ? ORDER BY pPrice";
                    break;
                case 2:
                    sql = "SELECT * FROM manufacturer AS M, part AS P, category AS C WHERE P.mID =  M.mID AND P.cID = C.cID AND pName LIKE ? ORDER BY pPrice DESC";
                    break;
                }
                break;
            case 2:
                switch (order) {
                case 1:
                    sql = "SELECT * FROM manufacturer AS M, part AS P, category AS C WHERE P.mID =  M.mID AND P.cID = C.cID AND mName LIKE ? ORDER BY pPrice";
                    break;
                case 2:
                    sql = "SELECT * FROM manufacturer AS M, part AS P, category AS C WHERE P.mID =  M.mID AND P.cID = C.cID AND mName LIKE ? ORDER BY pPrice DESC";
                    break;
                }
                break;
        }
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, "%" + kword + "%");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.println(String.format("| " + rs.getInt("pID") + " | " +  rs.getString("pName") + " | " + rs.getString("mName") + " | " + rs.getString("cName") +" | " + rs.getInt("pQuantity") + " | " + rs.getInt("pWarranty") + " | " + rs.getInt("pPrice") + " |"));
        }
        return;
    }

    public void PerformTransaction() throws SQLException {
        ResultSet rs;
        PreparedStatement stmt;
        int partID, salesID, quantity = 0, tid = 0;
        boolean idExists = false;
        String prodName = null;
        do{
            System.out.print("Enter The Part ID: ");
            partID = scanner.nextInt();
            scanner.nextLine();
            System.out.println("");
            stmt = connection.prepareStatement("SELECT * FROM part WHERE pID = ?");
            stmt.setInt(1, partID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                idExists = true;
                quantity = rs.getInt("pQuantity");
                prodName = rs.getString("pName");
            } else {
                System.out.println("The ID you entered does not exist in the table. Please try again.");
            }
        }while(!idExists);

        idExists = false;

        do{
            System.out.print("Enter The Salesperson ID: ");
            salesID = scanner.nextInt();
            scanner.nextLine();
            System.out.println("");
            stmt = connection.prepareStatement("SELECT * FROM salesperson WHERE sID = ?");
            stmt.setInt(1, salesID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                idExists = true;
            } else {
                System.out.println("The ID you entered does not exist in the table. Please try again.");
            }
        }while(!idExists);

        if (quantity > 0){
            stmt = connection.prepareStatement("UPDATE part SET pQuantity = (pQuantity - 1) WHERE pID = ?");
            stmt.setInt(1, partID);
            stmt.executeUpdate();
            System.out.println("Product: " + prodName + "(id: " + partID + ") Remaining Quantity: " + quantity);

            stmt = connection.prepareStatement("SELECT MAX(tID) AS MAX FROM transaction");
            rs = stmt.executeQuery();
            if (rs.next())
                tid = rs.getInt("MAX") + 1;

            stmt = connection.prepareStatement("INSERT INTO transaction VALUES (?, ?, ?, CURDATE())");
            stmt.setInt(1, tid);
            stmt.setInt(2, partID);
            stmt.setInt(3, salesID);
            stmt.executeUpdate();
        }
        else{
            System.out.print("The part cannot be sold.");
        }
        return;
    }
}
