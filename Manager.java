import java.sql.*;

public class Manager {
    Connection connection;

    public Manager(Connection connection) {
        this.connection = connection;
    }
}
