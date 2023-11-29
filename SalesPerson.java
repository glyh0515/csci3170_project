import java.sql.*;
import java.util.Scanner;

public class SalesPerson {

    Connection connection;
    Scanner scanner;

    public SalesPerson(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

}