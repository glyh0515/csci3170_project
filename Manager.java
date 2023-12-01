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
                    try {
                        ListSalespersons();
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    break;
                case 2:
                    try {
                        CountTransaction();
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    break;
                case 3:
                    try {
                        ListManufacturers();
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
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
        int order = scanner.nextInt();
        System.out.println("| ID | Name | Mobile Phone | Year of Experience |");
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(
                String.format("SELECT sID, sName, sPhoneNum, sExperience FROM salesperson ORDER BY sExperience %s",
                        (order == 1 ? "ASC;" : "DESC;")));
        while (rs.next()) {
            System.out.println(String.format("| %d | %s | %d | %d |", rs.getInt("sID"), rs.getString("sName"),
                    rs.getInt("sPhoneNum"), rs.getInt("sExperience")));
        }
    }

    public void CountTransaction() throws SQLException {
        System.out.print("Type in the lower bound for years of experience: ");
        int lowerBound = scanner.nextInt();
        System.out.print("Type in the upper bound for years of experience: ");
        int upperBound = scanner.nextInt();
        System.out.println("| ID | Name | Years of Experience | Number of Transaction |");
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(String.format(
            "SELECT S.sID, S.sName, S.sExperience, T.SALE_TRANSACTION FROM (SELECT sID, sName, sExperience from salesperson) S, (SELECT sID, count(*) AS SALE_TRANSACTION from transaction GROUP by sID) T WHERE S.sID = T.sID AND S.sExperience >= %d AND S.sExperience <= %d GROUP BY S.sID, S.sName, S.sExperience ORDER BY S.sID DESC;"     
        , lowerBound, upperBound));
        while (rs.next()) {
            System.out.println(String.format("| %d | %s | %d | %d", rs.getInt("S.sID"), rs.getString("S.sName"),
                    rs.getInt("S.sExperience"), rs.getInt("SALE_TRANSACTION")));
        }
        return;
    }

    public void ListManufacturers() throws SQLException {
        System.out.println("| Manufacturer ID | Manufacturer Name | Total Sales Value |");
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(String.format(
                "SELECT M.mID, M.mName, SUM(T.PART_SALE_COUNT * P.pPrice) AS TOTAL_SALES_VALUE\r\n" + //
                        "FROM (SELECT mID, mName from manufacturer) M, \r\n" + //
                        "(SELECT pID, pPrice, mID from part) P,\r\n" + //
                        "(SELECT pID, COUNT(*) AS PART_SALE_COUNT from transaction GROUP BY pID) T\r\n" + //
                        "WHERE M.mID = P.mID AND P.pID = T.pID\r\n" + //
                        "GROUP BY M.mID, M.mName\r\n" + //
                        "ORDER BY TOTAL_SALES_VALUE DESC;\r\n" + //
                        ""));
        while (rs.next()) {
            System.out.println(String.format("| %d | %s | %d |", rs.getInt("M.mID"), rs.getString("M.mName"),
                    rs.getInt("TOTAL_SALES_VALUE")));
        }
        return;
    }

    public void ShowNParts() throws SQLException {
        System.out.print("Type in the number of parts: ");
        int N = scanner.nextInt();
        System.out.println("| Part ID | Part Name | No. of Transaction |");
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(String.format(
                "SELECT P.pID, P.pName, SUM(T.PART_TRANSACTION) AS TOTAL_TRANSACTION FROM (SELECT pID, pName FROM part) P, (SELECT pID, COUNT(*) AS PART_TRANSACTION FROM transaction GROUP BY pID) T WHERE P.pID = T.pID GROUP BY P.pID, pName HAVING SUM(T.PART_TRANSACTION) > 0 ORDER BY TOTAL_TRANSACTION DESC LIMIT %d;",
                N));
        while (rs.next()) {
            System.out.println(String.format("| %d | %s | %d |", rs.getInt("P.pId"), rs.getString("P.pName"),
                    rs.getInt("TOTAL_TRANSACTION")));
        }
        return;
    }
}
