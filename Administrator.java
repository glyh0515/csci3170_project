import java.sql.*;

public class Administrator {

        Connection connection = null;

        public Administrator(Connection connection) {
                this.connection = connection;
        }

        public void createAll() throws SQLException {
                Statement stmt = connection.createStatement();
                stmt.executeUpdate(
                                "CREATE TABLE Category(CID INT(1) UNSIGNED NOT NULL, CNAME CHAR(20) NOT NULL, PRIMARY KEY(CID))");
                stmt.executeUpdate(
                                "CREATE TABLE Manufacturer(MID INT(2) UNSIGNED NOT NULL, MNAME CHAR(20) NOT NULL, MADDRESS CHAR(50) NOT NULL, MPHONENUM INT(8) UNSIGNED NOT NULL, PRIMARY KEY (MID))");
                stmt.executeUpdate(
                                "CREATE TABLE Part(PID INT(3) UNSIGNED NOT NULL, PNAME CHAR(20) NOT NULL, PPRICE INT(5) UNSIGNED NOT NULL, MID INT(2) UNSIGNED NOT NULL, CID INT(1) UNSIGNED NOT NULL, PWAR INT(2) UNSIGNED NOT NULL, PQTY INT(2) UNSIGNED NOT NULL, PRIMARY KEY (PID), FOREIGN KEY (MID) REFERENCES Manufacturer(MID), FOREIGN KEY (CID) REFERENCES Category(CID))");
                stmt.executeUpdate(
                                "CREATE TABLE Salesperson(SID INT(2) UNSIGNED NOT NULL, SNAME CHAR(20) NOT NULL, SADDRESS CHAR(50) NOT NULL, SPHONENUM INT(8) UNSIGNED NOT NULL, SEXPER INT(1) UNSIGNED NOT NULL, PRIMARY KEY (SID))");
                stmt.executeUpdate(
                                "CREATE TABLE Transaction(TID INT(4) UNSIGNED NOT NULL, PID INT(3) UNSIGNED NOT NULL, SID INT(2) UNSIGNED NOT NULL, TDATE DATE, PRIMARY KEY (TID), FOREIGN KEY (PID) REFERENCES Part(PID), FOREIGN KEY (SID) REFERENCES Salesperson(SID))");
        }

        public void deleteAll() throws SQLException {
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("DROP TABLE Category");
                stmt.executeUpdate("DROP TABLE Manufacturer");
                stmt.executeUpdate("DROP TABLE Part");
                stmt.executeUpdate("DROP TABLE Salesperson");
                stmt.executeUpdate("DROP TABLE Transaction");
        }
}
