import java.sql.*;
import java.util.Scanner;

public class Manager {
    Connection connection;
    Scanner scanner;

    public Manager(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }
}
