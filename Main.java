import java.sql.*;

public class Main {

    public static String dbAddress = "jdbc:mysql://projgw.cse.cuhk.edu.hk:2633/db11?autoReconnect=true&useSSL=false";
    public static String dbUsername = "Group11";
    public static String dbPassword = "CSCI3170";

    public static void main(String[] args) {
        Connection connection = connectToMySQL();

        System.out.println("Welcome to sales system!");

        Administrator admin = new Administrator(connection);
        try {
            admin.createAll();
        } catch (SQLException e) {
            System.out.println(e);
        }

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
