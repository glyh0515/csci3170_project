import java.sql.*;
import java.util.Scanner;

public class Administrator {

	Connection connection = null;
	Scanner scanner;

	public Administrator(Connection connection, Scanner scanner) {
		this.connection = connection;
		this.scanner = scanner;
	}

	public void showAdminstratorPanel() {
		while (true) {
			int choice = getValidChoiceFromAdministrator();

			switch (choice) {
				case 1:
					try {
						System.out.print("Processing...");
						createAll();
						System.out.println("Done! Database is intialized");
					} catch (SQLException e) {
						System.out.println("");
						System.out.println(e);
					}
					break;
				case 2:
					try {
						System.out.println("Processing...");
						deleteAll();
						System.out.println("Done! Database is removed");
					} catch (Exception e) {
						System.out.println(e);
					}
					break;
				case 3:

					break;
				case 4:
					try {
						showContentOfTable();
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

	private int getValidChoiceFromAdministrator() {
		System.out.println("----Operations for adminstrator menu----");
		System.out.println("What kinds of operation would you like to perform?");
		System.out.println("1. Create all tables");
		System.out.println("2. Delete all tables");
		System.out.println("3. Load from datafile");
		System.out.println("4. Show content of a table");
		System.out.println("5. Return to the main menu");

		while (true) {
			int choice = 0;
			System.out.print("Enter your choice: ");

			try {
				choice = scanner.nextInt();
				scanner.nextLine();
				System.out.println("");

				if (choice >= 1 && choice <= 5) {
					return choice;
				} else {
					System.out.println("Incorrect Input. Please enter a number from 1 - 5.");
				}

			} catch (Exception e) {
				System.out.println("Incorrect Input. Please enter a number from 1 - 5.");
				scanner.nextLine();
			}
		}
	}

	private void createAll() throws SQLException {
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

	private void deleteAll() throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DROP TABLE Category");
		stmt.executeUpdate("DROP TABLE Manufacturer");
		stmt.executeUpdate("DROP TABLE Part");
		stmt.executeUpdate("DROP TABLE Salesperson");
		stmt.executeUpdate("DROP TABLE Transaction");
	}

	private void showContentOfTable() throws SQLException {
		System.out.print("Which table would you like to show: ");
		String tableName = scanner.nextLine();
		System.out.println("Content of table " + tableName);
		Statement stmt = connection.createStatement();
		stmt.executeQuery(String.format("SELECT * FROM %s", tableName));
	}
}
