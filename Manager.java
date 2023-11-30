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
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                try {
                    ShowNParts();
                } catch (SQLException e) {
                    System.out.println(e);
                }
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
        /* "SELECT sID, sPhone, sName, sExperience FROM SALESPERSON ORDER BY sExperience"
         * + (order == 1 ? "ASC" : "DESC") + ";"
        */
    }

    public void CountTransaction() throws SQLException {
        System.out.print("Type in the lower bound for years of experience: ");
        System.out.print("Type in the upper bound for years of experience: ");
        /* "SELECT S.sID, S.sName, S.sExperience, COUNT(T.sID) 
        FROM SALESPERSON S, TRANSATION T
        WHERE S.sExpeirience >= " + lowerBound + " AND S.sExperience <= " + upperBound +
        "GROUP BY T.sID ORDER BY s.SID DESC"
         * 
         */
        return;
    }

    public void ListManufacturers() throws SQLException {
        return;
    }

    public void ShowNParts() throws SQLException {
        System.out.print("Type in the number of parts: ");
        int N = scanner.nextInt();
        System.out.println("| Part ID | Part Name | No. of Transaction |");
        Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(String.format("SELECT P.pID, P.pName, SUM(T.PART_TRANSACTION) AS TOTAL_TRANSACTION FROM (SELECT pID, pName FROM part) P, (SELECT pID, COUNT(*) AS PART_TRANSACTION FROM transaction GROUP BY pID) T WHERE P.pID = T.pID GROUP BY P.pID, pName HAVING SUM(T.PART_TRANSACTION) > 0 ORDER BY TOTAL_TRANSACTION DESC LIMIT %d", N));
        while (rs.next()) {
            System.out.println(String.format("| %d | %s | %d |", rs.getInt("P.pId"), rs.getString("P.pName"), rs.getInt("TOTAL_TRANSACTION")));
        }
        return;
    }
}
