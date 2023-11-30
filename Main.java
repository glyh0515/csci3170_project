import java.sql.*;
import java.util.Scanner;

public class Main {

    public static String dbAddress = "jdbc:mysql://projgw.cse.cuhk.edu.hk:2633/db11?autoReconnect=true&useSSL=false";
    public static String dbUsername = "Group11";
    public static String dbPassword = "CSCI3170";
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Connection connection = connectToMySQL();

        System.out.println("Welcome to sales system!\n");

        Administrator admin = new Administrator(connection, scanner);
        Manager manager = new Manager(connection, scanner);
        SalesPerson salesPerson = new SalesPerson(connection, scanner);

        while (true) {
            int choice = getValidChoiceFromMainMenu();

            switch (choice) {
                case 1:
                    admin.showAdminstratorPanel();
                    break;

                case 2:
                    salesPerson.showSalesPersonPanel();
                    break;
                case 3:
                    manager.showManagerPanel();
                    break;
                case 4:
                    System.out.println("System ends! Thank you.");
                    disconnectFromMySQL(connection);
                    scanner.close();
                    return;
                default:
                    break;
            }
        }

    }

    public static Connection connectToMySQL() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
        } catch (ClassNotFoundException e) {
            System.out.println("[Error]: Java MySQL DB Driver not found!!");
            System.exit(0);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return con;
    }

    public static void disconnectFromMySQL(Connection con) {
        try {
            con.close();
        } catch (Exception e) {
        }
    }

    /*
     * @return choice in Integer
     */
    public static int getValidChoiceFromMainMenu() {

        System.out.println("----Main Menu----");
        System.out.println("What kinds of operation would you like to perform?");
        System.out.println("1. Operations for administrator");
        System.out.println("2. Operations for salesperson");
        System.out.println("3. Operations for manager");
        System.out.println("4. Exit this program");

        while (true) {
            int choice = 0;
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println("");

                if (choice >= 1 && choice <= 4) {
                    return choice;
                } else {
                    System.out.println("Incorrect Input. Please enter a number from 1 - 4.");
                }

            } catch (Exception e) {
                System.out.println("Incorrect Input. Please enter a number from 1 - 4.");
                scanner.nextLine();
            }
        }
    }

    public void Load(Connection con) throws SQLException {

    }

    public void ShowTable(Connection con) throws SQLException {

    }

    public void SearchPart(Connection con) throws SQLException {

    }

    public void PerformTransaction(Connection con) throws SQLException {

    }

    public void ListSalespersons(Connection con) throws SQLException {

    }

    public void CountTransaction(Connection con) throws SQLException {

    }

    public void ListManufacturers(Connection con) throws SQLException {

    }

    public void ShowNParts(Connection con) throws SQLException {

    }

}
