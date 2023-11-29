import java.sql.*;
import java.util.Scanner;

public class Main {

    public static String dbAddress = "jdbc:mysql://projgw.cse.cuhk.edu.hk:2633/db11?autoReconnect=true&useSSL=false";
    public static String dbUsername = "Group11";
    public static String dbPassword = "CSCI3170";

    public static void main(String[] args) {
        Connection connection = connectToMySQL();

        System.out.println("Welcome to sales system!\n");

        Administrator admin = new Administrator(connection);
        Manager manager = new Manager(connection);
        SalesPerson salesPerson = new SalesPerson(connection);

        boolean end = false;
        while (!end) {
            int choice = getValidChoiceFromMainMenu();

            switch (choice) {
                case 1:

                    break;

                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    end = true;
                    break;
                default:
                    break;
            }
        }

        // try {
        // admin.createAll();
        // } catch (SQLException e) {
        // System.out.println(e);
        // }

        System.out.println("System ends! Thank you.");
        disconnectFromMySQL(connection);

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

        Scanner scanner = new Scanner(System.in);
        while (true) {
            int choice = 0;
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
                System.out.println("");

                if (choice >= 1 && choice <= 4) {
                    scanner.close();
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
